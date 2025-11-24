# LinkedIn Update Agent / Agente de Atualização do LinkedIn

🤖 Um agente automatizado para publicar atualizações no LinkedIn usando a API oficial.

🤖 An automated agent to post updates on LinkedIn using the official API.

---

## 🇧🇷 Português

### 📋 Descrição

Este projeto fornece ferramentas para automatizar publicações no LinkedIn:

- **linkedin_agent.py**: Agente principal para interagir com a API do LinkedIn
- **linkedin_scheduler.py**: Agendador para publicações em lote
- **example_posts.json**: Arquivo de exemplo com publicações configuradas

### 🚀 Como Usar

#### 1. Instalação

```bash
# Clone o repositório
git clone https://github.com/gustavoalfonso-ti/site.git
cd site

# Instale as dependências
pip install -r requirements.txt
```

#### 2. Configuração

Você precisa de um **Access Token** do LinkedIn:

1. Acesse [LinkedIn Developers](https://www.linkedin.com/developers/)
2. Crie um aplicativo
3. Configure as permissões OAuth 2.0: `w_member_social` (para publicar)
4. Obtenha seu Access Token

Configure o token de uma das formas:

**Opção A: Variável de ambiente**
```bash
export LINKEDIN_ACCESS_TOKEN="seu_token_aqui"
```

**Opção B: Arquivo .env**
```bash
cp .env.example .env
# Edite .env e adicione seu token
```

**Opção C: Parâmetro na linha de comando**
```bash
python linkedin_agent.py --token "seu_token_aqui" --text "Olá LinkedIn!"
```

#### 3. Uso Básico

**Publicar um texto simples:**
```bash
python linkedin_agent.py --text "Olá LinkedIn! 🚀"
```

**Publicar com link:**
```bash
python linkedin_agent.py \
  --text "Confira este artigo!" \
  --link "https://example.com" \
  --link-title "Título do Artigo" \
  --link-description "Descrição do artigo"
```

**Publicar apenas para conexões:**
```bash
python linkedin_agent.py --text "Post privado" --visibility CONNECTIONS
```

#### 4. Uso do Agendador

O agendador permite publicar múltiplos posts de um arquivo JSON:

```bash
# Usando o arquivo de exemplo
python linkedin_scheduler.py --config example_posts.json --delay 60

# Com delay personalizado (120 segundos entre posts)
python linkedin_scheduler.py --config meus_posts.json --delay 120
```

**Formato do arquivo JSON:**
```json
{
  "posts": [
    {
      "text": "Texto da primeira publicação",
      "visibility": "PUBLIC"
    },
    {
      "text": "Publicação com link",
      "link_url": "https://example.com",
      "link_title": "Título",
      "link_description": "Descrição",
      "visibility": "PUBLIC"
    }
  ]
}
```

### 📦 Estrutura do Projeto

```
site/
├── linkedin_agent.py          # Agente principal
├── linkedin_scheduler.py      # Agendador de publicações
├── requirements.txt           # Dependências Python
├── .env.example              # Exemplo de configuração
├── example_posts.json        # Exemplos de publicações
└── README.md                 # Esta documentação
```

### 🔒 Segurança

- **Nunca** commite seu Access Token no repositório
- Use variáveis de ambiente ou arquivos `.env` (adicionados ao `.gitignore`)
- Rotacione seus tokens periodicamente
- Configure permissões mínimas necessárias no aplicativo LinkedIn

### 📚 Recursos da API

- Publicação de texto simples
- Publicação com links/artigos
- Controle de visibilidade (PUBLIC ou CONNECTIONS)
- Logging detalhado de operações
- Tratamento de erros robusto

### 🛠️ Desenvolvimento

**Executar testes:**
```bash
# Testar autenticação
python linkedin_agent.py --text "Teste de autenticação" --visibility CONNECTIONS
```

### ❓ Solução de Problemas

**Erro de autenticação:**
- Verifique se o token está correto
- Confirme que o token não expirou
- Verifique as permissões do aplicativo (`w_member_social`)

**Erro ao publicar:**
- Verifique sua conexão com a internet
- Confirme que o texto não está vazio
- Revise os limites de taxa da API do LinkedIn

---

## 🇺🇸 English

### 📋 Description

This project provides tools to automate LinkedIn posts:

- **linkedin_agent.py**: Main agent to interact with LinkedIn API
- **linkedin_scheduler.py**: Scheduler for batch posting
- **example_posts.json**: Example file with configured posts

### 🚀 How to Use

#### 1. Installation

```bash
# Clone the repository
git clone https://github.com/gustavoalfonso-ti/site.git
cd site

# Install dependencies
pip install -r requirements.txt
```

#### 2. Configuration

You need a LinkedIn **Access Token**:

1. Go to [LinkedIn Developers](https://www.linkedin.com/developers/)
2. Create an application
3. Configure OAuth 2.0 permissions: `w_member_social` (to post)
4. Get your Access Token

Configure the token in one of these ways:

**Option A: Environment variable**
```bash
export LINKEDIN_ACCESS_TOKEN="your_token_here"
```

**Option B: .env file**
```bash
cp .env.example .env
# Edit .env and add your token
```

**Option C: Command line parameter**
```bash
python linkedin_agent.py --token "your_token_here" --text "Hello LinkedIn!"
```

#### 3. Basic Usage

**Post simple text:**
```bash
python linkedin_agent.py --text "Hello LinkedIn! 🚀"
```

**Post with link:**
```bash
python linkedin_agent.py \
  --text "Check out this article!" \
  --link "https://example.com" \
  --link-title "Article Title" \
  --link-description "Article description"
```

**Post only to connections:**
```bash
python linkedin_agent.py --text "Private post" --visibility CONNECTIONS
```

#### 4. Using the Scheduler

The scheduler allows posting multiple posts from a JSON file:

```bash
# Using the example file
python linkedin_scheduler.py --config example_posts.json --delay 60

# With custom delay (120 seconds between posts)
python linkedin_scheduler.py --config my_posts.json --delay 120
```

**JSON file format:**
```json
{
  "posts": [
    {
      "text": "First post text",
      "visibility": "PUBLIC"
    },
    {
      "text": "Post with link",
      "link_url": "https://example.com",
      "link_title": "Title",
      "link_description": "Description",
      "visibility": "PUBLIC"
    }
  ]
}
```

### 📦 Project Structure

```
site/
├── linkedin_agent.py          # Main agent
├── linkedin_scheduler.py      # Post scheduler
├── requirements.txt           # Python dependencies
├── .env.example              # Configuration example
├── example_posts.json        # Post examples
└── README.md                 # This documentation
```

### 🔒 Security

- **Never** commit your Access Token to the repository
- Use environment variables or `.env` files (added to `.gitignore`)
- Rotate your tokens periodically
- Configure minimum necessary permissions in LinkedIn app

### 📚 API Features

- Simple text posting
- Post with links/articles
- Visibility control (PUBLIC or CONNECTIONS)
- Detailed operation logging
- Robust error handling

### 🛠️ Development

**Run tests:**
```bash
# Test authentication
python linkedin_agent.py --text "Authentication test" --visibility CONNECTIONS
```

### ❓ Troubleshooting

**Authentication error:**
- Check if the token is correct
- Confirm the token hasn't expired
- Verify app permissions (`w_member_social`)

**Post error:**
- Check your internet connection
- Confirm text is not empty
- Review LinkedIn API rate limits

---

## 📄 License

This project is open source and available for educational and commercial use.

## 🤝 Contributing

Contributions are welcome! Feel free to open issues or submit pull requests.

---

**Developed with ❤️ for LinkedIn automation**
