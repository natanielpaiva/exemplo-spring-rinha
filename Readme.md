# Instruções de Uso

## Pré-requisitos

Antes de começar, certifique-se de ter o Docker e o Docker Compose instalados na sua máquina.

## 1. Gerar a Imagem Docker

Na raiz do projeto, execute o comando abaixo para construir a imagem Docker do Spring Boot localmente:

```bash
docker build -t natanielpaiva/exemplo-image-springboot-local:latest .
```

## 2. Subir o Nginx Customizado

Navegue até o diretório docker no seu projeto e execute o seguinte comando para subir a imagem Docker do Nginx customizado:
```bash
docker compose up --build
```

