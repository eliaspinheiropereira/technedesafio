# 📚 Techne Desafio - Sistema de Gestão Acadêmica

API REST para gerenciamento de um sistema acadêmico com alunos, cursos, disciplinas, turmas e matrículas.

## 📋 Índice

- [Tecnologias](#-tecnologias)
- [Como rodar o projeto localmente](#-como-rodar-o-projeto-localmente)
- [Executar com Docker](#-executar-com-docker)
- [Banco de Dados](#-banco-de-dados)
- [Acessar a API](#-acessar-a-api)
- [Endpoints Principais](#-endpoints-principais)
- [Como testar manualmente o fluxo de matrícula](#-como-testar-manualmente-o-fluxo-de-matrícula)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Ferramentas de IA Utilizadas](#-ferramentas-de-ia-utilizadas)
- [Acessar a API](#-acessar-a-api)
- [Endpoints Principais](#-endpoints-principais)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Ferramentas de IA Utilizadas](#-ferramentas-de-ia-utilizadas)

---

## 🚀 Tecnologias

- **Java 17+**
- **Spring Boot 3.5.16**
- **Spring Data JPA**
- **H2 Database** (em memória para testes)
- **MapStruct** (mapeamento de DTOs)
- **SpringDoc OpenAPI** (Swagger/OpenAPI 3.0)
- **Docker & Docker Compose**
- **Maven**
- **JUnit 5** (testes)
- **AssertJ** (assertions em testes)

> 📌 **Observação Importante:** Para testar os endpoints da API, recomenda-se utilizar **Postman**, **Insomnia**, **Thunder Client** ou qualquer cliente HTTP similar. Essas ferramentas facilitam o envio de requisições HTTP com diferentes métodos (GET, POST, PUT, DELETE) e permitem gerenciar headers, autenticação e salvar coleções de requisições para reutilização.

---

## 💻 Como rodar o projeto localmente

### Pré-requisitos

- Java 17 ou superior
- Maven 3.8+
- Git

### Passo 1: Clone o repositório

```bash
git clone https://github.com/seu-usuario/technedesafio.git
cd technedesafio
```

### Passo 2: Compile o projeto

```bash
./mvnw clean install
```

### Passo 3: Execute a aplicação

```bash
./mvnw spring-boot:run
```

A aplicação estará disponível em:
```
http://localhost:8080
```

### Passo 4: Acesse o Swagger

```
http://localhost:8080/swagger-ui.html
```

---

## 🐳 Executar com Docker


### Passo 1: Clone ou navegue até o projeto

```bash
cd "/home/eliasppereira/dev/processos seletivos/technedesafio"
```

### Passo 2: Execute o Docker Compose

```bash
docker-compose up --build
```

Aguarde até ver esta mensagem no console:

```
technedesafio-api  | APPLICATION STARTED on port 8080
```

✅ **Pronto! A aplicação está rodando!**

### Passo 3: Parar a aplicação

Pressione `Ctrl + C` no terminal

---

## 🗄️ Banco de Dados

### Banco Utilizado

O projeto utiliza **H2 Database** - um banco de dados em memória leve e rápido, perfeito para desenvolvimento.

### Configuração Principal (application.yaml)

```yaml
spring:
  datasource:
    url: jdbc:h2:mem:technedesafio
    driver-class-name: org.h2.Driver
    username: sa
    password: (vazio)
  
  h2:
    console:
      enabled: true
      path: /h2-console
  
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: false
    database-platform: org.hibernate.dialect.H2Dialect
```

### Acessar Console H2

Para gerenciar o banco de dados durante desenvolvimento:

```
http://localhost:8080/h2-console
```

**Credenciais padrão:**
- JDBC URL: `jdbc:h2:mem:technedesafio`
- User: `sa`
- Password: (deixar em branco)

### Estratégia de Criação de Tabelas

- `ddl-auto: update` - Cria/atualiza as tabelas automaticamente sem dropar dados
- Para testes, usa-se `create-drop` (veja `application-test.yaml`)

---

## 🌐 Acessar a API

### Swagger UI (Documentação Interativa)

```
http://localhost:8080/swagger-ui.html
```

Na UI do Swagger você pode:
- ✅ Visualizar todos os endpoints disponíveis
- ✅ Testar requisições direto no navegador
- ✅ Ver exemplos de request/response
- ✅ Consultar códigos de status HTTP
- ✅ Verificar modelos de dados

---

## 🔗 Endpoints Principais

### 📍 Endereços

**POST** - Cadastrar Endereço
```
localhost:8080/api/v1/enderecos
```
```json
{
  "logradouro": "rua nova",
  "numero": "688",
  "bairro": "pedreira",
  "cidade": "belém",
  "estado": "pa",
  "cep": "66083442"
}
```

**PUT** - Atualizar Endereço
```
localhost:8080/api/v1/enderecos/:id
```
```json
{
  "logradouro": "rua da felicidade",
  "numero": "688",
  "bairro": "bairro bom em sp",
  "cidade": "são paulo",
  "estado": "sp",
  "cep": "66083442"
}
```

**GET** - Buscar Endereço por ID
```
localhost:8080/api/v1/enderecos/:id
```

**DELETE** - Deletar Endereço
```
localhost:8080/api/v1/enderecos/:id
```

---

### 👤 Alunos

**POST** - Cadastrar Aluno
```
localhost:8080/api/v1/alunos
```
```json
{
  "nome": "João Silva",
  "cpf": "123.456.789-10",
  "email": "joao@example.com",
  "dataNascimento": "15/05/1995",
  "endereco": {
    "logradouro": "Rua das Flores",
    "numero": "123",
    "bairro": "Centro",
    "cidade": "São Paulo",
    "estado": "sp",
    "cep": "12345678"
  }
}
```

**PUT** - Atualizar Aluno
```
localhost:8080/api/v1/alunos/:id
```
```json
{
  "nome": "João da Silva",
  "cpf": "123.456.789-10",
  "email": "joao.updated@example.com",
  "dataNascimento": "15/05/1995",
  "endereco": {
    "logradouro": "Avenida Paulista",
    "numero": "1500",
    "bairro": "Bela Vista",
    "cidade": "São Paulo",
    "estado": "sp",
    "cep": "01311100"
  }
}
```

**GET** - Buscar Aluno por ID
```
localhost:8080/api/v1/alunos/:id
```

**DELETE** - Deletar Aluno
```
localhost:8080/api/v1/alunos/:id
```

---

### 📚 Cursos

**POST** - Cadastrar Curso
```
localhost:8080/api/v1/cursos
```
```json
{
  "nome": "Engenharia de Software",
  "duracaoEmSemestre": 8
}
```

**PUT** - Atualizar Curso
```
localhost:8080/api/v1/cursos/:id
```
```json
{
  "nome": "Engenharia de Software - Especialização",
  "duracaoEmSemestre": 9
}
```

**GET** - Buscar Curso por ID
```
localhost:8080/api/v1/cursos/:id
```

**DELETE** - Deletar Curso
```
localhost:8080/api/v1/cursos/:id
```

---

### 📖 Disciplinas

**POST** - Cadastrar Disciplina
```
localhost:8080/api/v1/disciplinas
```
```json
{
  "nome": "Programação Java",
  "cargaHoraria": 120,
  "cursoId": 1
}
```

**PUT** - Atualizar Disciplina
```
localhost:8080/api/v1/disciplinas/:id
```
```json
{
  "nome": "Programação Java Avançada",
  "cargaHoraria": 150,
  "cursoId": 1
}
```

**GET** - Buscar Disciplina por ID
```
localhost:8080/api/v1/disciplinas/:id
```

**DELETE** - Deletar Disciplina
```
localhost:8080/api/v1/disciplinas/:id
```

---

### 🎓 Turmas

**POST** - Cadastrar Turma
```
localhost:8080/api/v1/turmas
```
```json
{
  "periodo": "2024/1",
  "turno": "MANHA",
  "status": "ABERTA",
  "limiteVaga": 30,
  "disciplinaIds": [1, 2]
}
```

**Valores possíveis para turno:** MANHA, TARDE, NOITE

**Valores possíveis para status:** ABERTA, FECHADA, CANCELADA

**PUT** - Atualizar Turma
```
localhost:8080/api/v1/turmas/:id
```
```json
{
  "periodo": "2024/2",
  "turno": "TARDE",
  "status": "ABERTA",
  "limiteVaga": 25,
  "disciplinaIds": [1, 2, 3]
}
```

**GET** - Buscar Turma por ID
```
localhost:8080/api/v1/turmas/:id
```

**DELETE** - Deletar Turma
```
localhost:8080/api/v1/turmas/:id
```

---

### ✏️ Matrículas

**POST** - Cadastrar Matrícula
```
localhost:8080/api/v1/matriculas
```
```json
{
  "alunoId": 1,
  "turmaId": 1,
  "dataMatricula": "15/01/2024"
}
```

**PUT** - Atualizar Status da Matrícula
```
localhost:8080/api/v1/matriculas/:id
```
```json
{
  "statusMatricula": "CONFIRMADA"
}
```

**Valores válidos para statusMatricula:** PENDENTE, CONFIRMADA, CANCELADA

**GET** - Buscar Matrícula por ID
```
localhost:8080/api/v1/matriculas/:id
```

**DELETE** - Deletar/Cancelar Matrícula
```
localhost:8080/api/v1/matriculas/:id
```

---

## 🧪 Como testar manualmente o fluxo de matrícula

Este guia mostra um exemplo prático de como fazer o cadastro completo de uma matrícula, seguindo o fluxo passo a passo.

### Pré-requisitos

- API rodando em `localhost:8080`
- Postman, Insomnia, Thunder Client ou outro cliente HTTP
- Swagger UI aberto em `http://localhost:8080/swagger-ui.html` (opcional)

### Passo 1️⃣ - Cadastrar um Aluno

**POST** `localhost:8080/api/v1/alunos`

```json
{
  "nome": "Jo��o Silva",
  "cpf": "123.456.789-10",
  "email": "joao@example.com",
  "dataNascimento": "15/05/1995",
  "endereco": {
    "logradouro": "Rua das Flores",
    "numero": "123",
    "bairro": "Centro",
    "cidade": "São Paulo",
    "estado": "sp",
    "cep": "12345678"
  }
}
```

**Resposta esperada (201 Created):**
```json
{
  "id": 1,
  "nome": "João Silva",
  "cpf": "123.456.789-10",
  "email": "joao@example.com",
  "dataNascimento": "15/05/1995",
  "endereco": {
    "id": 1,
    "logradouro": "Rua das Flores",
    "numero": "123",
    "bairro": "Centro",
    "cidade": "São Paulo",
    "estado": "sp",
    "cep": "12345678"
  }
}
```

✅ **Guarde o ID do aluno: `1`**

---

### Passo 2️⃣ - Cadastrar um Curso

**POST** `localhost:8080/api/v1/cursos`

```json
{
  "nome": "Engenharia de Software",
  "duracaoEmSemestre": 8
}
```

**Resposta esperada (201 Created):**
```json
{
  "id": 1,
  "nome": "Engenharia de Software",
  "duracaoEmSemestre": 8
}
```

✅ **Guarde o ID do curso: `1`**

---

### Passo 3️⃣ - Cadastrar uma Disciplina

**POST** `localhost:8080/api/v1/disciplinas`

```json
{
  "nome": "Programação Java",
  "cargaHoraria": 120,
  "cursoId": 1
}
```

**Resposta esperada (201 Created):**
```json
{
  "id": 1,
  "nome": "Programação Java",
  "cargaHoraria": 120,
  "cursoId": 1
}
```

✅ **Guarde o ID da disciplina: `1`**

---

### Passo 4️⃣ - Cadastrar uma Turma

**POST** `localhost:8080/api/v1/turmas`

```json
{
  "periodo": "2024/1",
  "turno": "MANHA",
  "status": "ABERTA",
  "limiteVaga": 30,
  "disciplinaIds": [1]
}
```

**Resposta esperada (201 Created):**
```json
{
  "id": 1,
  "periodo": "2024/1",
  "turno": "MANHA",
  "status": "ABERTA",
  "limiteVaga": 30,
  "vagaDisponivel": 30,
  "disciplinaIds": [1]
}
```

✅ **Guarde o ID da turma: `1`**

---

### Passo 5️⃣ - Cadastrar a Matrícula

**POST** `localhost:8080/api/v1/matriculas`

Agora com os IDs do **Aluno (1)** e **Turma (1)**:

```json
{
  "alunoId": 1,
  "turmaId": 1,
  "dataMatricula": "10/07/2024"
}
```

**Resposta esperada (201 Created):**
```json
{
  "id": 1,
  "alunoId": 1,
  "turmaId": 1,
  "dataMatricula": "10/07/2024",
  "statusMatricula": "PENDENTE"
}
```

✅ **Parabéns! Matrícula cadastrada com sucesso!**

---

### 🎯 Teste Adicional: Confirmar Matrícula

Você pode confirmar a matrícula alterando seu status:

**PUT** `localhost:8080/api/v1/matriculas/1`

```json
{
  "statusMatricula": "CONFIRMADA"
}
```

**Resposta esperada (200 OK):**
```json
{
  "id": 1,
  "alunoId": 1,
  "turmaId": 1,
  "dataMatricula": "10/07/2024",
  "statusMatricula": "CONFIRMADA"
}
```

---

## 📝 Resumo do Fluxo

```
1. Aluno (ID: 1) ✅
   ↓
2. Curso (ID: 1) ✅
   ↓
3. Disciplina (ID: 1) ✅
   ↓
4. Turma (ID: 1) com Disciplina ✅
   ↓
5. Matrícula (Aluno + Turma) ✅
   ↓
Status: PENDENTE → CONFIRMADA (opcional)
```

---

## 💡 Dicas Importantes

- Os campos com tipo **ENUM** aceitam apenas valores específicos:
  - **Turno**: MANHA, TARDE, NOITE
  - **Status (Turma)**: ABERTA, FECHADA, CANCELADA
  - **Status (Matrícula)**: PENDENTE, CONFIRMADA, CANCELADA

- **Datas** devem estar no formato: `DD/MM/YYYY` (ex: 10/07/2024)

- Sempre capture o **ID** da resposta de cada recurso para usar no próximo passo

- Para testar **múltiplas matrículas**, repita o processo alterando os dados do aluno

---

## 🔢 Como validar a regra de limite de vagas

O sistema controla automaticamente o número de vagas disponíveis em uma turma baseado no status das matrículas.

### Conceito

- **limiteVaga**: Número máximo de vagas da turma (definido no cadastro - **imutável**)
- **vagaDisponivel**: Vagas disponíveis em tempo real (varia conforme matrículas são confirmadas ou canceladas)

### Regra de Negócio

Quando uma matrícula muda de status:

| Status da Matrícula | Ação | Impacto em `vagaDisponivel` |
|---|---|---|
| PENDENTE | Criada ou permanece | ❌ Sem alteração |
| PENDENTE → **CONFIRMADA** | Confirmada | ⬇️ Diminui 1 vaga |
| CONFIRMADA → **CANCELADA** | Cancelada | ⬆️ Aumenta 1 vaga |
| PENDENTE → **CANCELADA** | Cancelada diretamente | ❌ Sem alteração |

---

### 🧪 Exemplo Prático de Teste

#### Cenário Inicial

Turma criada com `limiteVaga: 2`:

**GET** `localhost:8080/api/v1/turmas/1`

```json
{
  "id": 1,
  "periodo": "2024/1",
  "turno": "MANHA",
  "status": "ABERTA",
  "limiteVaga": 2,
  "vagaDisponivel": 2,
  "disciplinaIds": [1]
}
```

✅ **Vagas disponíveis: 2**

---

#### Passo 1: Criar Primeira Matrícula

**POST** `localhost:8080/api/v1/matriculas`

```json
{
  "alunoId": 1,
  "turmaId": 1,
  "dataMatricula": "10/07/2024"
}
```

**Resposta:**
```json
{
  "id": 1,
  "alunoId": 1,
  "turmaId": 1,
  "dataMatricula": "10/07/2024",
  "statusMatricula": "PENDENTE"
}
```

**Verificar turma:**

**GET** `localhost:8080/api/v1/turmas/1`

```json
{
  "id": 1,
  "periodo": "2024/1",
  "turno": "MANHA",
  "status": "ABERTA",
  "limiteVaga": 2,
  "vagaDisponivel": 2,
  "disciplinaIds": [1]
}
```

✅ **Vagas ainda: 2** (matrícula em PENDENTE não desconta)

---

#### Passo 2: Confirmar Primeira Matrícula

**PUT** `localhost:8080/api/v1/matriculas/1`

```json
{
  "statusMatricula": "CONFIRMADA"
}
```

**Resposta:**
```json
{
  "id": 1,
  "alunoId": 1,
  "turmaId": 1,
  "dataMatricula": "10/07/2024",
  "statusMatricula": "CONFIRMADA"
}
```

**Verificar turma:**

**GET** `localhost:8080/api/v1/turmas/1`

```json
{
  "id": 1,
  "periodo": "2024/1",
  "turno": "MANHA",
  "status": "ABERTA",
  "limiteVaga": 2,
  "vagaDisponivel": 1,
  "disciplinaIds": [1]
}
```

⬇️ **Vagas agora: 1** (uma vaga foi desconta ao confirmar!)

---

#### Passo 3: Criar Segunda Matrícula

**POST** `localhost:8080/api/v1/matriculas`

```json
{
  "alunoId": 2,
  "turmaId": 1,
  "dataMatricula": "10/07/2024"
}
```

**Resposta:**
```json
{
  "id": 2,
  "alunoId": 2,
  "turmaId": 1,
  "dataMatricula": "10/07/2024",
  "statusMatricula": "PENDENTE"
}
```

**Verificar turma:**

**GET** `localhost:8080/api/v1/turmas/1`

```json
{
  "id": 1,
  "limiteVaga": 2,
  "vagaDisponivel": 1,
  "disciplinaIds": [1]
}
```

✅ **Vagas continua: 1** (segunda matrícula em PENDENTE)

---

#### Passo 4: Confirmar Segunda Matrícula

**PUT** `localhost:8080/api/v1/matriculas/2`

```json
{
  "statusMatricula": "CONFIRMADA"
}
```

**Verificar turma:**

**GET** `localhost:8080/api/v1/turmas/1`

```json
{
  "id": 1,
  "limiteVaga": 2,
  "vagaDisponivel": 0,
  "disciplinaIds": [1]
}
```

⬇️ **Vagas agora: 0** (todas as vagas ocupadas!)

---

#### Passo 5: Cancelar Primeira Matrícula

**PUT** `localhost:8080/api/v1/matriculas/1`

```json
{
  "statusMatricula": "CANCELADA"
}
```

**Verificar turma:**

**GET** `localhost:8080/api/v1/turmas/1`

```json
{
  "id": 1,
  "limiteVaga": 2,
  "vagaDisponivel": 1,
  "disciplinaIds": [1]
}
```

⬆️ **Vagas agora: 1** (uma vaga foi reatribuída ao cancelar!)

---

### 📊 Resumo Visual do Teste

```
Início:
  limiteVaga = 2
  vagaDisponivel = 2 ✅✅

Matrícula 1 (PENDENTE):
  vagaDisponivel = 2 ✅✅ (sem mudança)

Matrícula 1 (CONFIRMADA):
  vagaDisponivel = 1 ✅❌ (1 vaga desconta)

Matrícula 2 (PENDENTE):
  vagaDisponivel = 1 ✅❌ (sem mudança)

Matrícula 2 (CONFIRMADA):
  vagaDisponivel = 0 ❌❌ (turma cheia!)

Matrícula 1 (CANCELADA):
  vagaDisponivel = 1 ✅❌ (1 vaga reatribuída)
```

---

## 💻 Como validar na API

### Abordagem 1: Usando Swagger UI

1. Abra `http://localhost:8080/swagger-ui.html`
2. Faça as requisições na ordem descrita acima
3. Após cada `PUT` em matrículas, execute um `GET` na turma
4. Compare o valor de `vagaDisponivel` com o esperado

### Abordagem 2: Usando Console H2

1. Acesse `http://localhost:8080/h2-console`
2. Execute a query:
   ```sql
   SELECT id, limite_vaga, vaga_disponivel FROM turma WHERE id = 1;
   ```
3. Veja a mudança em tempo real

### Abordagem 3: Logs da Aplicação

A aplicação pode logar as alterações. Verifique a saída do console para mensagens de atualização de vagas.

---

## ⚠️ Casos Especiais

**O que acontece quando não há vagas?**

Se tentar confirmar uma matrícula e `vagaDisponivel` chegar a 0, a turma está **cheia**, mas o sistema ainda permite a confirmação (depende das regras de negócio implementadas).

**É possível ter vagas negativas?**

Não! O sistema valida que `vagaDisponivel` nunca seja menor que 0.

---

## 📂 Estrutura do Projeto

```
technedesafio/
├── src/
│   ├── main/
│   │   ├── java/io/github/eliaspinheiropereira/technedesafio/
│   │   │   ├── TechnedesafioApplication.java
│   │   │   ├── config/              # Configurações (OpenAPI, Swagger)
│   │   │   ├── controller/          # REST Controllers (6 controllers)
│   │   │   ├── service/             # Lógica de negócio
│   │   │   ├── repository/          # Data Access Layer
│   │   │   ├── model/               # Entidades JPA
│   │   │   ├── dto/
│   │   │   │   ├── request/         # DTOs para entrada
│   │   │   │   └── response/        # DTOs para saída
│   │   │   ├── mapper/              # MapStruct (entity ↔ DTO)
│   │   │   ├── validator/           # Validações de negócio
│   │   │   ├── exception/           # Exceções customizadas
│   │   │   └── handler/             # Global Exception Handler
│   │   └── resources/
│   │       ├── application.yaml     # Configuração principal
│   │       └── application-test.yaml # Configuração de testes
│   └── test/
│       └── java/...                 # Testes unitários
├── Dockerfile                       # Imagem Docker
├── docker-compose.yml              # Orquestração Docker
├── pom.xml                         # Dependências Maven
└── README.md                       # Este arquivo
```

---

## 🤖 Ferramentas de IA Utilizadas

### GitHub Copilot com Claude

Ferramentas de IA foram utilizadas em diversas partes do desenvolvimento:

#### ✅ Partes geradas com assistência de IA:

- **Entidades JPA** (Aluno, Curso, Disciplina, Turma, Matricula, Endereco)
  - Mapeamento de relacionamentos (One-to-Many, Many-to-One, Many-to-Many)
  - Anotações de validação

- **Services** (AlunoService, CursoService, DisciplinaService, TurmaService, MatriculaService)
  - Métodos CRUD com lógica de negócio
  - Integração com repositories

- **Controllers** (AlunoController, CursoController, DisciplinaController, TurmaController, MatriculaController)
  - Endpoints REST com anotações Swagger
  - Tratamento de erros

- **DTOs e Mappers** (MapStruct)
  - Estrutura de request/response
  - Mapeamento automático entity ↔ DTO

- **Testes Unitários**
  - Testes com AssertJ
  - Cobertura do MatriculaService e MatriculaValidator

- **Configuração Docker**
  - Dockerfile multi-stage
  - docker-compose.yml

- **Documentação**
  - Anotações Swagger/OpenAPI em controllers
  - Configuração OpenApiConfig

#### 🔍 Partes revisadas manualmente:

- ✏️ Relacionamento entre entidades (validação de lógica)
- ✏️ Validações de negócio (MatriculaValidator)
- ✏️ Métodos de repository personalizados
- ✏️ Tratamento de exceções (GlobalExceptionHandler)
- ✏️ Testes de integração (lógica de dados)
- ✏️ Configuração do H2 Database
- ✏️ README.md e documentação do projeto

---

**Última atualização**: 10/07/2026

