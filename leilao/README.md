# Sistema de Leilões Eletrônicos

Este projeto é uma aplicação simples que implementa um sistema de leilões eletrônicos para dispositivos de informática e veículos apreendidos pela Receita Federal do Brasil. A aplicação foi desenvolvida para atender os requisitos funcionais básicos solicitados.

## Funcionalidades

A aplicação inclui as seguintes funcionalidades básicas:

1. **Gerenciamento de Produtos**:
   - Registro, consulta, atualização e remoção de dispositivos de informática (ex: notebooks, monitores) e veículos (ex: carros, motocicletas).

2. **Gerenciamento de Leilões**:
   - Registro, consulta, atualização e remoção de leilões.
   - Associação de produtos a leilões e possibilidade de reatribuir itens não vendidos a novos leilões.

3. **Gerenciamento de Clientes**:
   - Registro, consulta, atualização e remoção de clientes que podem participar dos leilões e dar lances.

4. **Gerenciamento de Lances**:
   - Clientes cadastrados podem dar lances nos produtos dos leilões.

## Como Rodar a Aplicação

### Pré-requisitos

- **Java 8+**: Certifique-se de ter o JDK instalado no seu sistema.
- **Maven**: Ferramenta para gerenciamento de dependências e build da aplicação.

### Passos para Execução

1. **Clone o Repositório**:
```bash
   git clone https://github.com/Caio-sousaFatec/LP-2.git
   cd LP-2
```
2. **Compilar e Rodar a Aplicação**:
```bash
mvn spring-boot:run
```
3. **Acessar a aplicação**:
```bash
http://localhost:8080/swagger-ui.html
```
   
