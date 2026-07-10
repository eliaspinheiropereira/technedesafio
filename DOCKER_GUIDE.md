# 🐳 Guia de Execução com Docker

## 📋 Pré-requisitos

Antes de começar, certifique-se que você tem instalado:

- **Docker**: [Download Docker Desktop](https://www.docker.com/products/docker-desktop)
- **Docker Compose**: Normalmente vem com o Docker Desktop

### Verificar se está tudo instalado:

```bash
docker --version
docker-compose --version
```

---

## 🚀 Passo a Passo para Executar

### **Opção 1: Usando Docker Compose (RECOMENDADO)**

Esta é a forma mais simples! Docker Compose sempre cuidará de tudo para você.

#### Passo 1: Abra o terminal na pasta do projeto

```bash
cd "/home/eliasppereira/dev/processos seletivos/technedesafio"
```

#### Passo 2: Execute o comando para construir e iniciar

```bash
docker-compose up --build
```

Isso vai:
- ✅ Construir a imagem Docker
- ✅ Iniciar o container
- ✅ Exibir os logs em tempo real

#### Passo 3: Aguarde a mensagem de sucesso

Você verá algo assim no terminal:
```
technedesafio-api  | 2026-07-10T14:55:00.000-03:00  INFO 1 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http)
```

#### Passo 4: Acesse a API! 🎉

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI Docs**: http://localhost:8080/v3/api-docs
- **Health Check**: http://localhost:8080/actuator/health

#### Para parar a aplicação:

No terminal, pressione `Ctrl + C` ou em outro terminal execute:

```bash
docker-compose down
```

---

### **Opção 2: Usando Dockerfile (Manual)**

Se preferir total controle:

#### Passo 1: Construir a imagem

```bash
cd "/home/eliasppereira/dev/processos seletivos/technedesafio"
docker build -t technedesafio-api:latest .
```

- `technedesafio-api` = nome da imagem
- `latest` = tag da versão
- `.` = Dockerfile está no diretório atual

Isso vai demorar alguns minutos na primeira execução (baixando dependências Maven).

#### Passo 2: Executar o container

```bash
docker run -p 8080:8080 --name technedesafio-container technedesafio-api:latest
```

Ou em background (sem ver logs):

```bash
docker run -d -p 8080:8080 --name technedesafio-container technedesafio-api:latest
```

#### Passo 3: Monitorar logs (se executar em background)

```bash
docker logs -f technedesafio-container
```

#### Passo 4: Parar o container

```bash
docker stop technedesafio-container
docker rm technedesafio-container
```

---

## 📌 Verificar se está funcionando

### Teste 1: Acessar Swagger UI

Abra no navegador:
```
http://localhost:8080/swagger-ui.html
```

### Teste 2: Fazer uma requisição HTTP

```bash
curl -X POST http://localhost:8080/api/v1/cursos \
  -H "Content-Type: application/json" \
  -d '{"nome": "Engenharia de Software", "cargaHoraria": 200}'
```

### Teste 3: Ver logs do container

```bash
docker logs technedesafio-api
# ou
docker logs technedesafio-container
```

---

## 🔧 Commandos Úteis

### Listar imagens Docker construídas

```bash
docker images | grep technedesafio
```

### Listar containers em execução

```bash
docker ps
```

### Listar todos os containers (inclusive parados)

```bash
docker ps -a
```

### Ver detalhes de um container

```bash
docker inspect technedesafio-container
```

### Limpar imagens e containers antigos

```bash
docker system prune -a
```

### Executar comando dentro do container

```bash
docker exec -it technedesafio-container /bin/sh
```

---

## 🔍 Solução de Problemas

### ❌ Erro: "Port 8080 is already allocated"

**Causa**: Outra aplicação está usando a porta 8080

**Solução**:
```bash
# Opção 1: Usar outra porta
docker run -p 9090:8080 technedesafio-api:latest

# Opção 2: Matar o processo na porta 8080
lsof -i :8080
kill -9 <PID>
```

### ❌ Erro: "docker: command not found"

**Causa**: Docker não está instalado

**Solução**: [Instale o Docker Desktop](https://www.docker.com/products/docker-desktop)

### ❌ Erro Build falha com "Connection refused"

**Causa**: Problema de conexão durante download de dependências Maven

**Solução**:
```bash
# Tente novamente (pode ser problema de rede)
docker build -t technedesafio-api:latest .

# Ou limpe o cache
docker build --no-cache -t technedesafio-api:latest .
```

### ❌ Erro: "Swagger UI mostra erro 500"

**Causa**: Versão incompatível do Swagger

**Solução**: Reconstruir a imagem
```bash
docker build --no-cache -t technedesafio-api:latest .
```

---

## 📊 Informações da Imagem Docker

- **Base Image**: `eclipse-temurin:21-jre-alpine` (leve e otimizada)
- **Tamanho**: ~400-500 MB (dependendo das dependências)
- **Porta**: 8080
- **Banco de dados**: H2 (em memória)

---

## 🎯 Próximos Passos

1. **Testar endpoints** via Swagger: http://localhost:8080/swagger-ui.html
2. **Integrar PostgreSQL** (opcional): editar `docker-compose.yml`
3. **Deploy em produção**: usar Docker Registry ou Kubernetes

---

## 💡 Dicas Importantes

✅ **Sempre usar Docker Compose** para desenvolvimento (mais simples)
✅ **Dados H2 são perdidos** quando o container para (normal para testes)
✅ **Buildar uma única vez** depois reusar a imagem
✅ **Ver logs em tempo real**: `docker-compose logs -f`

---

Qualquer dúvida? Execute:

```bash
docker-compose --help
docker --help
```

