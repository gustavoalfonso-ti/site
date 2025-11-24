#!/usr/bin/env python3
"""
LinkedIn Agent Scheduler - Agendador do Agente do LinkedIn

Este script permite agendar e automatizar publicações no LinkedIn a partir de um arquivo JSON.
This script allows scheduling and automating LinkedIn posts from a JSON file.
"""

import json
import time
import logging
from typing import List, Dict
from datetime import datetime
from linkedin_agent import LinkedInAgent


class LinkedInScheduler:
    """
    Agendador para publicações automáticas no LinkedIn.
    Scheduler for automatic LinkedIn posts.
    """
    
    def __init__(self, config_file: str = "example_posts.json", access_token: str = None):
        """
        Inicializa o agendador.
        Initialize the scheduler.
        
        Args:
            config_file: Arquivo JSON com as publicações / JSON file with posts
            access_token: Token de acesso do LinkedIn / LinkedIn access token
        """
        self.config_file = config_file
        self.agent = LinkedInAgent(access_token=access_token)
        self.logger = logging.getLogger(__name__)
    
    def load_posts(self) -> List[Dict]:
        """
        Carrega publicações do arquivo de configuração.
        Load posts from configuration file.
        
        Returns:
            Lista de publicações / List of posts
        """
        try:
            with open(self.config_file, 'r', encoding='utf-8') as f:
                config = json.load(f)
                return config.get('posts', [])
        except FileNotFoundError:
            self.logger.error(f"Arquivo não encontrado: {self.config_file}")
            return []
        except json.JSONDecodeError as e:
            self.logger.error(f"Erro ao ler JSON: {e}")
            return []
    
    def post_single(self, post_config: Dict) -> bool:
        """
        Publica uma única atualização.
        Post a single update.
        
        Args:
            post_config: Configuração da publicação / Post configuration
        
        Returns:
            True se sucesso / True if successful
        """
        try:
            text = post_config.get('text', '')
            visibility = post_config.get('visibility', 'PUBLIC')
            
            if not text:
                self.logger.warning("Texto vazio, pulando publicação")
                return False
            
            # Check if it's a link post
            if 'link_url' in post_config:
                self.agent.create_post_with_link(
                    text=text,
                    link_url=post_config['link_url'],
                    link_title=post_config.get('link_title', ''),
                    link_description=post_config.get('link_description', ''),
                    visibility=visibility
                )
            else:
                self.agent.create_text_post(
                    text=text,
                    visibility=visibility
                )
            
            return True
            
        except Exception as e:
            self.logger.error(f"Erro ao publicar: {e}")
            return False
    
    def run_batch(self, delay_seconds: int = 60):
        """
        Executa todas as publicações em lote com intervalo.
        Execute all posts in batch with interval.
        
        Args:
            delay_seconds: Intervalo entre publicações em segundos /
                          Interval between posts in seconds
        """
        posts = self.load_posts()
        
        if not posts:
            self.logger.warning("Nenhuma publicação encontrada")
            print("⚠ Nenhuma publicação encontrada / No posts found")
            return
        
        print(f"\n📅 Agendador iniciado com {len(posts)} publicações")
        print(f"📅 Scheduler started with {len(posts)} posts\n")
        
        for i, post in enumerate(posts, 1):
            print(f"[{i}/{len(posts)}] Publicando...")
            print(f"[{i}/{len(posts)}] Posting...")
            
            success = self.post_single(post)
            
            if success:
                print(f"✓ Publicação {i} criada com sucesso!")
                print(f"✓ Post {i} created successfully!\n")
            else:
                print(f"✗ Erro na publicação {i}")
                print(f"✗ Error in post {i}\n")
            
            # Wait before next post (except for last one)
            if i < len(posts):
                print(f"⏳ Aguardando {delay_seconds} segundos...")
                print(f"⏳ Waiting {delay_seconds} seconds...\n")
                time.sleep(delay_seconds)
        
        print("\n✓ Lote completo!")
        print("✓ Batch complete!")


def main():
    """
    Função principal / Main function
    """
    import argparse
    
    parser = argparse.ArgumentParser(
        description='LinkedIn Scheduler - Agendador do LinkedIn'
    )
    parser.add_argument(
        '--config',
        type=str,
        default='example_posts.json',
        help='Arquivo JSON com publicações / JSON file with posts'
    )
    parser.add_argument(
        '--delay',
        type=int,
        default=60,
        help='Intervalo entre publicações em segundos / Interval between posts in seconds'
    )
    parser.add_argument(
        '--token',
        type=str,
        help='LinkedIn access token (ou use LINKEDIN_ACCESS_TOKEN env var)'
    )
    
    args = parser.parse_args()
    
    try:
        scheduler = LinkedInScheduler(
            config_file=args.config,
            access_token=args.token
        )
        scheduler.run_batch(delay_seconds=args.delay)
        
    except Exception as e:
        print(f"\n✗ Erro: {e}")
        print(f"✗ Error: {e}")
        return 1
    
    return 0


if __name__ == "__main__":
    exit(main())
