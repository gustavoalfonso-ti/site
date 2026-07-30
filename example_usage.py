#!/usr/bin/env python3
"""
Exemplo de uso do LinkedIn Agent
Example usage of LinkedIn Agent

Este script demonstra como usar o agente programaticamente.
This script demonstrates how to use the agent programmatically.
"""

from linkedin_agent import LinkedInAgent
import os


def exemplo_basico():
    """
    Exemplo básico de publicação / Basic posting example
    """
    print("=== Exemplo Básico / Basic Example ===\n")
    
    # Inicializar o agente (certifique-se de ter LINKEDIN_ACCESS_TOKEN configurado)
    # Initialize the agent (make sure you have LINKEDIN_ACCESS_TOKEN configured)
    try:
        agent = LinkedInAgent()
        
        # Obter informações do usuário
        # Get user information
        print("Obtendo informações do usuário...")
        print("Getting user information...")
        user_info = agent.get_user_info()
        print(f"✓ Conectado como: {user_info.get('localizedFirstName', 'Unknown')}\n")
        
        # Criar uma publicação de texto simples
        # Create a simple text post
        print("Criando publicação de texto...")
        print("Creating text post...")
        result = agent.create_text_post(
            text="🚀 Este é um post de teste do LinkedIn Update Agent! #automation #linkedin",
            visibility="CONNECTIONS"  # Visibilidade apenas para conexões / Connections only
        )
        print(f"✓ Publicação criada: {result.get('id', 'Unknown ID')}\n")
        
    except ValueError as e:
        print(f"⚠ Erro de configuração: {e}")
        print("⚠ Configuration error: Set LINKEDIN_ACCESS_TOKEN environment variable")
        return False
    except Exception as e:
        print(f"✗ Erro: {e}")
        return False
    
    return True


def exemplo_com_link():
    """
    Exemplo de publicação com link / Example of post with link
    """
    print("=== Exemplo com Link / Link Example ===\n")
    
    try:
        agent = LinkedInAgent()
        
        # Criar publicação com link
        # Create post with link
        print("Criando publicação com link...")
        print("Creating post with link...")
        result = agent.create_post_with_link(
            text="📚 Confira este recurso incrível sobre automação do LinkedIn!",
            link_url="https://github.com/gustavoalfonso-ti/site",
            link_title="LinkedIn Update Agent",
            link_description="Agente automatizado para publicações no LinkedIn",
            visibility="PUBLIC"
        )
        print(f"✓ Publicação com link criada: {result.get('id', 'Unknown ID')}\n")
        
    except Exception as e:
        print(f"✗ Erro: {e}")
        return False
    
    return True


def verificar_configuracao():
    """
    Verifica se o ambiente está configurado corretamente
    Check if environment is configured correctly
    """
    print("=== Verificação de Configuração / Configuration Check ===\n")
    
    token = os.getenv("LINKEDIN_ACCESS_TOKEN")
    
    if not token:
        print("✗ LINKEDIN_ACCESS_TOKEN não configurado")
        print("✗ LINKEDIN_ACCESS_TOKEN not configured")
        print("\nConfigure-o usando:")
        print("Configure it using:")
        print("  export LINKEDIN_ACCESS_TOKEN='seu_token'")
        print("  ou / or")
        print("  Crie um arquivo .env com: LINKEDIN_ACCESS_TOKEN=seu_token")
        return False
    
    print("✓ LINKEDIN_ACCESS_TOKEN configurado")
    print(f"✓ Token length: {len(token)} caracteres")
    return True


def main():
    """
    Função principal / Main function
    """
    print("\n" + "="*60)
    print("LinkedIn Update Agent - Exemplos de Uso")
    print("LinkedIn Update Agent - Usage Examples")
    print("="*60 + "\n")
    
    # Verificar configuração
    if not verificar_configuracao():
        return 1
    
    print("\n" + "-"*60 + "\n")
    
    # Perguntar ao usuário qual exemplo executar
    # Ask user which example to run
    print("Escolha um exemplo / Choose an example:")
    print("1. Publicação de texto simples / Simple text post")
    print("2. Publicação com link / Post with link")
    print("3. Ambos / Both")
    print("0. Sair / Exit")
    
    escolha = input("\nOpção / Option (0-3): ").strip()
    
    print("\n" + "-"*60 + "\n")
    
    if escolha == "1":
        exemplo_basico()
    elif escolha == "2":
        exemplo_com_link()
    elif escolha == "3":
        exemplo_basico()
        print("\n" + "-"*60 + "\n")
        exemplo_com_link()
    elif escolha == "0":
        print("Saindo... / Exiting...")
        return 0
    else:
        print("Opção inválida / Invalid option")
        return 1
    
    print("\n" + "="*60)
    print("✓ Exemplos concluídos / Examples completed")
    print("="*60 + "\n")
    
    return 0


if __name__ == "__main__":
    exit(main())
