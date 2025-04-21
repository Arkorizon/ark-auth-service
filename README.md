# 🔐 ArkAuth - Sistema de Autenticação Multi-Tenant com JWT

**ArkAuth** é o serviço de autenticação oficial da plataforma Arkorizon. Ele foi desenvolvido com foco em segurança, escalabilidade e modularidade, seguindo rigorosamente os princípios da Arquitetura Hexagonal e Domain-Driven Design (DDD).

---

## 🧠 Como funciona a autenticação no ArkAuth

### 1. Autenticação baseada em **JWT (JSON Web Token)**

- O usuário realiza login enviando: `email`, `password`, `groupId` e `subGroupId`.
- O sistema valida as credenciais e contexto (multi-tenant) e retorna um **access token** e **refresh token** assinados com chaves RSA.
- O access token contém as seguintes claims:
  - `sub`: ID do usuário
  - `group_id`: ID do grupo (tenant principal)
  - `sub_group_id`: ID do subgrupo (tenant secundário)
  - `roles`: Lista de roles do usuário

### 2. Multi-Tenant Hierárquico

- **Group:** Representa o sistema ou cliente principal (ex: Governate, Facebook, Telegram)
- **SubGroup:** Representa um escopo isolado dentro de um grupo (ex: cidade, filial, departamento)
- Cada usuário está **vinculado a um subgrupo e grupo**, e seu login é validado considerando esse contexto.

### 3. Controle de Acesso baseado em Roles

- Cada usuário possui uma ou mais **roles**.
- Cada role tem uma lista de **permissions** (ex: `LOGIN`, `REFRESH_TOKEN`, `LOGOUT`).
- A verificação de permissões é feita através das roles, e pode ser validada pelo backend com uso de claims ou cache.

### 4. Tokens

| Tipo          | Uso                                     | Expiração padrão |
|---------------|------------------------------------------|------------------|
| Access Token  | Usado para autenticar requisições       | 10 minutos       |
| Refresh Token | Usado para renovar o access token       | 24 horas         |

- O refresh token é armazenado (temporariamente) no Redis ou em memória.
- O logout remove o refresh token do armazenamento, invalidando futuras renovações.

### 5. Segurança

- Tokens assinados com **chaves assimétricas (RSA)**.
- Stateless (sem sessão no servidor).
- Segurança configurada com Spring Security e OAuth2 Resource Server.
- Apenas os endpoints públicos são expostos sem autenticação:

```
POST /api/v1/auth/login
POST /api/v1/auth/refresh-token
POST /api/v1/auth/logout
```

---

## 🧩 Stack principal

- Java 21 + Spring Boot 3
- Spring Security + JWT (OAuth2 Resource Server)
- Arquitetura Hexagonal
- Armazenamento com JPA (PostgreSQL ou H2)
- MapStruct para conversão
- Redis (ou fake) para refresh tokens

---

## ✨ Benefícios do modelo atual

- Autenticação 100% isolada por grupo e subgrupo
- Reuso de token entre serviços com segurança garantida
- Escalável para múltiplos sistemas clientes sem acoplamento
- Infraestrutura preparada para serviços externos, gateways e SaaS
- Limpeza e legibilidade de código com foco em domínio

---

## 🔐 Exemplo de fluxo

1. Usuário acessa `/login` com `email`, `senha`, `groupId`, `subGroupId`
2. Recebe access token + refresh token
3. Front usa o access token nos headers das requisições
4. Quando expira, envia o refresh token para `/refresh-token`
5. Em logout, refresh token é invalidado

---

## 📱 Exemplo de JWT (claims simplificadas)

```json
{
  "sub": "33333333-3333-3333-3333-333333333333",
  "group_id": "11111111-1111-1111-1111-111111111111",
  "sub_group_id": "22222222-2222-2222-2222-222222222222",
  "roles": ["ADMIN", "SUPERVISOR"],
  "iat": 1710000000,
  "exp": 1710000600
}
```

---

## 🧱 Próximos passos

- Documentar fluxo de OAuth2 externo (login via Google etc)
- Gerar documentação Swagger da API
- Criar integração com sistema de roles customizadas
- Expor rota `/me` para introspecção de token e dados do usuário
