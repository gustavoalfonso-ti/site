#!/usr/bin/env python3
"""
LinkedIn Update Agent - Agente de Atualização do LinkedIn

Este agente permite automatizar a publicação de atualizações no LinkedIn.
This agent allows automating the publication of updates on LinkedIn.
"""

import os
import json
import logging
from typing import Dict, Optional
import requests


class LinkedInAgent:
    """
    Agente para publicar atualizações no LinkedIn usando a API oficial.
    Agent to post updates on LinkedIn using the official API.
    """
    
    API_BASE_URL = "https://api.linkedin.com/v2"
    
    def __init__(self, access_token: Optional[str] = None):
        """
        Inicializa o agente do LinkedIn.
        Initialize the LinkedIn agent.
        
        Args:
            access_token: Token de acesso OAuth 2.0 do LinkedIn
                         LinkedIn OAuth 2.0 access token
        """
        self.access_token = access_token or os.getenv("LINKEDIN_ACCESS_TOKEN")
        if not self.access_token:
            raise ValueError(
                "Access token não fornecido. "
                "Forneça via parâmetro ou variável de ambiente LINKEDIN_ACCESS_TOKEN.\n"
                "Access token not provided. "
                "Provide via parameter or LINKEDIN_ACCESS_TOKEN environment variable."
            )
        
        self.headers = {
            "Authorization": f"Bearer {self.access_token}",
            "Content-Type": "application/json",
            "X-Restli-Protocol-Version": "2.0.0"
        }
        
        # Configure logging
        logging.basicConfig(
            level=logging.INFO,
            format='%(asctime)s - %(name)s - %(levelname)s - %(message)s'
        )
        self.logger = logging.getLogger(__name__)
    
    def get_user_info(self) -> Dict:
        """
        Obtém informações do usuário autenticado.
        Get authenticated user information.
        
        Returns:
            Dict com informações do usuário / Dict with user information
        """
        url = f"{self.API_BASE_URL}/me"
        
        try:
            response = requests.get(url, headers=self.headers)
            response.raise_for_status()
            user_info = response.json()
            self.logger.info(f"Usuário autenticado: {user_info.get('localizedFirstName', 'Unknown')}")
            return user_info
        except requests.exceptions.RequestException as e:
            self.logger.error(f"Erro ao obter informações do usuário: {e}")
            raise
    
    def create_text_post(self, text: str, visibility: str = "PUBLIC") -> Dict:
        """
        Cria uma publicação de texto no LinkedIn.
        Create a text post on LinkedIn.
        
        Args:
            text: Texto da publicação / Post text
            visibility: Visibilidade ('PUBLIC' ou 'CONNECTIONS') / 
                       Visibility ('PUBLIC' or 'CONNECTIONS')
        
        Returns:
            Dict com resposta da API / Dict with API response
        """
        # Get user URN
        user_info = self.get_user_info()
        author_urn = f"urn:li:person:{user_info['id']}"
        
        # Prepare post data
        post_data = {
            "author": author_urn,
            "lifecycleState": "PUBLISHED",
            "specificContent": {
                "com.linkedin.ugc.ShareContent": {
                    "shareCommentary": {
                        "text": text
                    },
                    "shareMediaCategory": "NONE"
                }
            },
            "visibility": {
                "com.linkedin.ugc.MemberNetworkVisibility": visibility
            }
        }
        
        url = f"{self.API_BASE_URL}/ugcPosts"
        
        try:
            response = requests.post(url, headers=self.headers, json=post_data)
            response.raise_for_status()
            result = response.json()
            self.logger.info(f"Publicação criada com sucesso: {result.get('id', 'Unknown ID')}")
            return result
        except requests.exceptions.RequestException as e:
            self.logger.error(f"Erro ao criar publicação: {e}")
            if hasattr(e, 'response') and e.response and hasattr(e.response, 'text'):
                self.logger.error(f"Detalhes do erro: {e.response.text}")
            raise
    
    def create_post_with_link(self, text: str, link_url: str, 
                             link_title: str = "", 
                             link_description: str = "",
                             visibility: str = "PUBLIC") -> Dict:
        """
        Cria uma publicação com link no LinkedIn.
        Create a post with a link on LinkedIn.
        
        Args:
            text: Texto da publicação / Post text
            link_url: URL do link / Link URL
            link_title: Título do link / Link title
            link_description: Descrição do link / Link description
            visibility: Visibilidade / Visibility
        
        Returns:
            Dict com resposta da API / Dict with API response
        """
        # Get user URN
        user_info = self.get_user_info()
        author_urn = f"urn:li:person:{user_info['id']}"
        
        # Prepare post data with link
        post_data = {
            "author": author_urn,
            "lifecycleState": "PUBLISHED",
            "specificContent": {
                "com.linkedin.ugc.ShareContent": {
                    "shareCommentary": {
                        "text": text
                    },
                    "shareMediaCategory": "ARTICLE",
                    "media": [
                        {
                            "status": "READY",
                            "description": {
                                "text": link_description
                            },
                            "originalUrl": link_url,
                            "title": {
                                "text": link_title
                            }
                        }
                    ]
                }
            },
            "visibility": {
                "com.linkedin.ugc.MemberNetworkVisibility": visibility
            }
        }
        
        url = f"{self.API_BASE_URL}/ugcPosts"
        
        try:
            response = requests.post(url, headers=self.headers, json=post_data)
            response.raise_for_status()
            result = response.json()
            self.logger.info(f"Publicação com link criada: {result.get('id', 'Unknown ID')}")
            return result
        except requests.exceptions.RequestException as e:
            self.logger.error(f"Erro ao criar publicação com link: {e}")
            if hasattr(e, 'response') and e.response and hasattr(e.response, 'text'):
                self.logger.error(f"Detalhes do erro: {e.response.text}")
            raise


def main():
    """
    Função principal para demonstração / Main function for demonstration
    """
    import argparse
    
    parser = argparse.ArgumentParser(
        description='LinkedIn Update Agent - Agente de Atualização do LinkedIn'
    )
    parser.add_argument(
        '--text',
        type=str,
        required=True,
        help='Texto da publicação / Post text'
    )
    parser.add_argument(
        '--link',
        type=str,
        help='URL do link (opcional) / Link URL (optional)'
    )
    parser.add_argument(
        '--link-title',
        type=str,
        default='',
        help='Título do link / Link title'
    )
    parser.add_argument(
        '--link-description',
        type=str,
        default='',
        help='Descrição do link / Link description'
    )
    parser.add_argument(
        '--visibility',
        type=str,
        choices=['PUBLIC', 'CONNECTIONS'],
        default='PUBLIC',
        help='Visibilidade da publicação / Post visibility'
    )
    parser.add_argument(
        '--token',
        type=str,
        help='LinkedIn access token (ou use LINKEDIN_ACCESS_TOKEN env var)'
    )
    
    args = parser.parse_args()
    
    try:
        # Initialize agent
        agent = LinkedInAgent(access_token=args.token)
        
        # Create post
        if args.link:
            result = agent.create_post_with_link(
                text=args.text,
                link_url=args.link,
                link_title=args.link_title,
                link_description=args.link_description,
                visibility=args.visibility
            )
        else:
            result = agent.create_text_post(
                text=args.text,
                visibility=args.visibility
            )
        
        print(f"\n✓ Publicação criada com sucesso!")
        print(f"✓ Post created successfully!")
        print(f"ID: {result.get('id', 'Unknown')}")
        
    except Exception as e:
        print(f"\n✗ Erro: {e}")
        print(f"✗ Error: {e}")
        return 1
    
    return 0


if __name__ == "__main__":
    exit(main())
