# Quick Start Guide / Guia de Início Rápido

## 🇧🇷 Português

### Passo a Passo para Começar

#### 1. Obter Credenciais do LinkedIn

1. Acesse [LinkedIn Developers](https://www.linkedin.com/developers/apps)
2. Clique em "Create app"
3. Preencha as informações do aplicativo
4. Em "Auth" → "OAuth 2.0 settings":
   - Adicione `http://localhost:8080/callback` como Redirect URL
5. Em "Products":
   - Solicite acesso ao "Share on LinkedIn"
6. Obtenha suas credenciais:
   - Client ID
   - Client Secret

#### 2. Obter Access Token

**Opção A: Usar OAuth 2.0 Flow (Recomendado para produção)**

```bash
# Você precisará implementar um fluxo OAuth completo
# Veja: https://docs.microsoft.com/en-us/linkedin/shared/authentication/authorization-code-flow
```

**Opção B: Usar LinkedIn Token Generator (Desenvolvimento/Teste)**

```bash
# Use o LinkedIn Developer Portal para gerar um token de teste
# Navegue até seu app → Auth → OAuth 2.0 tools
```

#### 3. Configurar o Ambiente

```bash
# Clone o repositório
git clone https://github.com/gustavoalfonso-ti/site.git
cd site

# Instale as dependências
pip install -r requirements.txt

# Configure o token
export LINKEDIN_ACCESS_TOKEN="seu_token_aqui"

# Ou crie um arquivo .env
cp .env.example .env
# Edite .env e adicione seu token
```

#### 4. Testar a Conexão

```bash
# Execute o exemplo interativo
python example_usage.py
```

#### 5. Publicar seu Primeiro Post

```bash
# Post simples
python linkedin_agent.py --text "Meu primeiro post automatizado! 🚀"

# Post com link
python linkedin_agent.py \
  --text "Confira meu projeto!" \
  --link "https://github.com/gustavoalfonso-ti/site" \
  --link-title "LinkedIn Update Agent" \
  --link-description "Agente de automação para LinkedIn"
```

### Casos de Uso

#### Publicações Agendadas

Crie um arquivo `meus_posts.json`:

```json
{
  "posts": [
    {
      "text": "Bom dia! Começando a semana com energia! ⚡",
      "visibility": "PUBLIC"
    },
    {
      "text": "Artigo interessante sobre tecnologia:",
      "link_url": "https://seu-blog.com/artigo",
      "link_title": "Título do Artigo",
      "link_description": "Descrição do artigo",
      "visibility": "PUBLIC"
    }
  ]
}
```

Execute:

```bash
python linkedin_scheduler.py --config meus_posts.json --delay 300
```

#### Integração com Cron (Linux/Mac)

```bash
# Adicione ao crontab
crontab -e

# Publicar todos os dias às 9h
0 9 * * * cd /caminho/para/site && /usr/bin/python3 linkedin_scheduler.py --config posts_diarios.json

# Publicar toda segunda-feira às 8h
0 8 * * 1 cd /caminho/para/site && /usr/bin/python3 linkedin_agent.py --text "Ótima semana a todos! 💼"
```

### Dicas de Uso

1. **Respeite os limites da API**: Evite publicar muito frequentemente
2. **Use delays adequados**: Recomendado mínimo de 60 segundos entre posts
3. **Teste com CONNECTIONS primeiro**: Use `--visibility CONNECTIONS` para testar
4. **Monitore os logs**: O agente gera logs detalhados das operações
5. **Rotacione tokens**: Gere novos tokens periodicamente por segurança

---

## 🇺🇸 English

### Step-by-Step Getting Started

#### 1. Get LinkedIn Credentials

1. Go to [LinkedIn Developers](https://www.linkedin.com/developers/apps)
2. Click "Create app"
3. Fill in application information
4. In "Auth" → "OAuth 2.0 settings":
   - Add `http://localhost:8080/callback` as Redirect URL
5. In "Products":
   - Request access to "Share on LinkedIn"
6. Get your credentials:
   - Client ID
   - Client Secret

#### 2. Obtain Access Token

**Option A: Use OAuth 2.0 Flow (Recommended for production)**

```bash
# You'll need to implement a complete OAuth flow
# See: https://docs.microsoft.com/en-us/linkedin/shared/authentication/authorization-code-flow
```

**Option B: Use LinkedIn Token Generator (Development/Testing)**

```bash
# Use the LinkedIn Developer Portal to generate a test token
# Navigate to your app → Auth → OAuth 2.0 tools
```

#### 3. Setup Environment

```bash
# Clone repository
git clone https://github.com/gustavoalfonso-ti/site.git
cd site

# Install dependencies
pip install -r requirements.txt

# Configure token
export LINKEDIN_ACCESS_TOKEN="your_token_here"

# Or create .env file
cp .env.example .env
# Edit .env and add your token
```

#### 4. Test Connection

```bash
# Run interactive example
python example_usage.py
```

#### 5. Post Your First Update

```bash
# Simple post
python linkedin_agent.py --text "My first automated post! 🚀"

# Post with link
python linkedin_agent.py \
  --text "Check out my project!" \
  --link "https://github.com/gustavoalfonso-ti/site" \
  --link-title "LinkedIn Update Agent" \
  --link-description "Automation agent for LinkedIn"
```

### Use Cases

#### Scheduled Posts

Create a `my_posts.json` file:

```json
{
  "posts": [
    {
      "text": "Good morning! Starting the week with energy! ⚡",
      "visibility": "PUBLIC"
    },
    {
      "text": "Interesting article about technology:",
      "link_url": "https://your-blog.com/article",
      "link_title": "Article Title",
      "link_description": "Article description",
      "visibility": "PUBLIC"
    }
  ]
}
```

Run:

```bash
python linkedin_scheduler.py --config my_posts.json --delay 300
```

#### Cron Integration (Linux/Mac)

```bash
# Add to crontab
crontab -e

# Post daily at 9 AM
0 9 * * * cd /path/to/site && /usr/bin/python3 linkedin_scheduler.py --config daily_posts.json

# Post every Monday at 8 AM
0 8 * * 1 cd /path/to/site && /usr/bin/python3 linkedin_agent.py --text "Great week everyone! 💼"
```

### Usage Tips

1. **Respect API limits**: Avoid posting too frequently
2. **Use appropriate delays**: Minimum 60 seconds between posts recommended
3. **Test with CONNECTIONS first**: Use `--visibility CONNECTIONS` to test
4. **Monitor logs**: The agent generates detailed operation logs
5. **Rotate tokens**: Generate new tokens periodically for security
