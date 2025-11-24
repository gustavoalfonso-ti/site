#!/usr/bin/env python3
"""
Test suite for LinkedIn Update Agent
Suite de testes para o Agente de Atualização do LinkedIn

Run with: python3 test_linkedin_agent.py
"""

import unittest
import json
import os
from unittest.mock import Mock, patch, MagicMock
from linkedin_agent import LinkedInAgent
from linkedin_scheduler import LinkedInScheduler


class TestLinkedInAgent(unittest.TestCase):
    """Test cases for LinkedInAgent class"""
    
    def setUp(self):
        """Set up test fixtures"""
        self.test_token = "test_access_token_123"
        self.mock_user_info = {
            "id": "test_user_id",
            "localizedFirstName": "Test",
            "localizedLastName": "User"
        }
    
    def test_init_with_token(self):
        """Test initialization with token parameter"""
        agent = LinkedInAgent(access_token=self.test_token)
        self.assertEqual(agent.access_token, self.test_token)
        self.assertIn("Authorization", agent.headers)
        self.assertEqual(agent.headers["Authorization"], f"Bearer {self.test_token}")
    
    def test_init_without_token_raises_error(self):
        """Test initialization without token raises ValueError"""
        # Ensure no environment variable is set
        if "LINKEDIN_ACCESS_TOKEN" in os.environ:
            del os.environ["LINKEDIN_ACCESS_TOKEN"]
        
        with self.assertRaises(ValueError) as context:
            LinkedInAgent()
        
        self.assertIn("Access token", str(context.exception))
    
    def test_init_with_env_variable(self):
        """Test initialization with environment variable"""
        os.environ["LINKEDIN_ACCESS_TOKEN"] = self.test_token
        agent = LinkedInAgent()
        self.assertEqual(agent.access_token, self.test_token)
        # Clean up
        del os.environ["LINKEDIN_ACCESS_TOKEN"]
    
    @patch('linkedin_agent.requests.get')
    def test_get_user_info_success(self, mock_get):
        """Test successful user info retrieval"""
        mock_response = Mock()
        mock_response.json.return_value = self.mock_user_info
        mock_response.raise_for_status = Mock()
        mock_get.return_value = mock_response
        
        agent = LinkedInAgent(access_token=self.test_token)
        user_info = agent.get_user_info()
        
        self.assertEqual(user_info, self.mock_user_info)
        mock_get.assert_called_once()
    
    @patch('linkedin_agent.requests.get')
    @patch('linkedin_agent.requests.post')
    def test_create_text_post_success(self, mock_post, mock_get):
        """Test successful text post creation"""
        # Mock get_user_info
        mock_get_response = Mock()
        mock_get_response.json.return_value = self.mock_user_info
        mock_get_response.raise_for_status = Mock()
        mock_get.return_value = mock_get_response
        
        # Mock post creation
        mock_post_response = Mock()
        mock_post_response.json.return_value = {"id": "test_post_id"}
        mock_post_response.raise_for_status = Mock()
        mock_post.return_value = mock_post_response
        
        agent = LinkedInAgent(access_token=self.test_token)
        result = agent.create_text_post("Test post")
        
        self.assertEqual(result["id"], "test_post_id")
        mock_post.assert_called_once()
        
        # Verify post data structure
        call_args = mock_post.call_args
        post_data = call_args[1]["json"]
        self.assertEqual(post_data["author"], f"urn:li:person:{self.mock_user_info['id']}")
        self.assertEqual(
            post_data["specificContent"]["com.linkedin.ugc.ShareContent"]["shareCommentary"]["text"],
            "Test post"
        )
    
    @patch('linkedin_agent.requests.get')
    @patch('linkedin_agent.requests.post')
    def test_create_post_with_link_success(self, mock_post, mock_get):
        """Test successful post with link creation"""
        # Mock get_user_info
        mock_get_response = Mock()
        mock_get_response.json.return_value = self.mock_user_info
        mock_get_response.raise_for_status = Mock()
        mock_get.return_value = mock_get_response
        
        # Mock post creation
        mock_post_response = Mock()
        mock_post_response.json.return_value = {"id": "test_post_with_link_id"}
        mock_post_response.raise_for_status = Mock()
        mock_post.return_value = mock_post_response
        
        agent = LinkedInAgent(access_token=self.test_token)
        result = agent.create_post_with_link(
            text="Check this out",
            link_url="https://example.com",
            link_title="Example",
            link_description="Example description"
        )
        
        self.assertEqual(result["id"], "test_post_with_link_id")
        
        # Verify post data structure
        call_args = mock_post.call_args
        post_data = call_args[1]["json"]
        media = post_data["specificContent"]["com.linkedin.ugc.ShareContent"]["media"][0]
        self.assertEqual(media["originalUrl"], "https://example.com")
        self.assertEqual(media["title"]["text"], "Example")


class TestLinkedInScheduler(unittest.TestCase):
    """Test cases for LinkedInScheduler class"""
    
    def setUp(self):
        """Set up test fixtures"""
        self.test_token = "test_access_token_123"
        self.test_posts = {
            "posts": [
                {"text": "Post 1", "visibility": "PUBLIC"},
                {"text": "Post 2", "link_url": "https://example.com", "visibility": "CONNECTIONS"}
            ]
        }
        self.test_config_file = "/tmp/test_posts.json"
        
        # Create test config file
        with open(self.test_config_file, 'w', encoding='utf-8') as f:
            json.dump(self.test_posts, f)
    
    def tearDown(self):
        """Clean up test files"""
        if os.path.exists(self.test_config_file):
            os.remove(self.test_config_file)
    
    def test_load_posts_success(self):
        """Test successful loading of posts from JSON"""
        scheduler = LinkedInScheduler(
            config_file=self.test_config_file,
            access_token=self.test_token
        )
        posts = scheduler.load_posts()
        
        self.assertEqual(len(posts), 2)
        self.assertEqual(posts[0]["text"], "Post 1")
        self.assertEqual(posts[1]["text"], "Post 2")
    
    def test_load_posts_file_not_found(self):
        """Test loading posts from non-existent file"""
        scheduler = LinkedInScheduler(
            config_file="/tmp/nonexistent.json",
            access_token=self.test_token
        )
        posts = scheduler.load_posts()
        
        self.assertEqual(posts, [])
    
    @patch('linkedin_scheduler.LinkedInAgent.create_text_post')
    @patch('linkedin_scheduler.LinkedInAgent.get_user_info')
    def test_post_single_text(self, mock_get_user, mock_create_post):
        """Test posting a single text post"""
        mock_get_user.return_value = {"id": "test_user"}
        mock_create_post.return_value = {"id": "post_id"}
        
        scheduler = LinkedInScheduler(
            config_file=self.test_config_file,
            access_token=self.test_token
        )
        
        post_config = {"text": "Test post", "visibility": "PUBLIC"}
        result = scheduler.post_single(post_config)
        
        self.assertTrue(result)
        mock_create_post.assert_called_once_with(
            text="Test post",
            visibility="PUBLIC"
        )
    
    @patch('linkedin_scheduler.LinkedInAgent.create_post_with_link')
    @patch('linkedin_scheduler.LinkedInAgent.get_user_info')
    def test_post_single_with_link(self, mock_get_user, mock_create_link_post):
        """Test posting a single post with link"""
        mock_get_user.return_value = {"id": "test_user"}
        mock_create_link_post.return_value = {"id": "post_id"}
        
        scheduler = LinkedInScheduler(
            config_file=self.test_config_file,
            access_token=self.test_token
        )
        
        post_config = {
            "text": "Check this",
            "link_url": "https://example.com",
            "link_title": "Title",
            "link_description": "Description",
            "visibility": "PUBLIC"
        }
        result = scheduler.post_single(post_config)
        
        self.assertTrue(result)
        mock_create_link_post.assert_called_once()


class TestJSONConfiguration(unittest.TestCase):
    """Test JSON configuration files"""
    
    def test_example_posts_json_valid(self):
        """Test that example_posts.json is valid"""
        file_path = "example_posts.json"
        
        self.assertTrue(os.path.exists(file_path), f"{file_path} should exist")
        
        with open(file_path, 'r', encoding='utf-8') as f:
            data = json.load(f)
        
        self.assertIn("posts", data)
        self.assertIsInstance(data["posts"], list)
        self.assertGreater(len(data["posts"]), 0)
        
        for post in data["posts"]:
            self.assertIn("text", post)
            self.assertIn("visibility", post)
            self.assertIsInstance(post["text"], str)
            self.assertIn(post["visibility"], ["PUBLIC", "CONNECTIONS"])


class TestConfiguration(unittest.TestCase):
    """Test configuration files"""
    
    def test_requirements_txt_exists(self):
        """Test that requirements.txt exists"""
        self.assertTrue(os.path.exists("requirements.txt"))
        
        with open("requirements.txt", 'r') as f:
            content = f.read()
            self.assertIn("requests", content)
    
    def test_env_example_exists(self):
        """Test that .env.example exists"""
        self.assertTrue(os.path.exists(".env.example"))
        
        with open(".env.example", 'r') as f:
            content = f.read()
            self.assertIn("LINKEDIN_ACCESS_TOKEN", content)
    
    def test_gitignore_protects_secrets(self):
        """Test that .gitignore protects sensitive files"""
        self.assertTrue(os.path.exists(".gitignore"))
        
        with open(".gitignore", 'r') as f:
            content = f.read()
            self.assertIn(".env", content)
            self.assertNotIn(".env.example", content.replace("!.env.example", ""))


def run_tests():
    """Run all tests and print results"""
    print("\n" + "="*70)
    print("LinkedIn Update Agent - Test Suite")
    print("Suite de Testes do Agente de Atualização do LinkedIn")
    print("="*70 + "\n")
    
    # Create test suite
    loader = unittest.TestLoader()
    suite = unittest.TestSuite()
    
    # Add all test classes
    suite.addTests(loader.loadTestsFromTestCase(TestLinkedInAgent))
    suite.addTests(loader.loadTestsFromTestCase(TestLinkedInScheduler))
    suite.addTests(loader.loadTestsFromTestCase(TestJSONConfiguration))
    suite.addTests(loader.loadTestsFromTestCase(TestConfiguration))
    
    # Run tests with verbose output
    runner = unittest.TextTestRunner(verbosity=2)
    result = runner.run(suite)
    
    # Print summary
    print("\n" + "="*70)
    print("Test Summary / Resumo dos Testes")
    print("="*70)
    print(f"Tests run / Testes executados: {result.testsRun}")
    print(f"Successes / Sucessos: {result.testsRun - len(result.failures) - len(result.errors)}")
    print(f"Failures / Falhas: {len(result.failures)}")
    print(f"Errors / Erros: {len(result.errors)}")
    
    if result.wasSuccessful():
        print("\n✓ All tests passed! / Todos os testes passaram!")
        print("="*70 + "\n")
        return 0
    else:
        print("\n✗ Some tests failed / Alguns testes falharam")
        print("="*70 + "\n")
        return 1


if __name__ == "__main__":
    exit(run_tests())
