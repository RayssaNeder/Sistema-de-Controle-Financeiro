# XPTO - Sistema de Controle Financeiro

## Descrição
Sistema desenvolvido para gerenciamento financeiro de clientes (Pessoa Física e Jurídica), incluindo:
- Cadastro de clientes e contas bancárias
- Registro de movimentações financeiras
- Cálculo automático de receitas
- Geração de relatórios analíticos

## Funcionalidades
✅ CRUD completo de clientes, contas e movimentações  
✅ Cálculo automático de receitas  
✅ Geração de relatórios em formato .txt  
✅ API documentada com Swagger  
✅ Testes automatizados com JUnit  

## Tecnologias
| Categoria       | Tecnologias                          |
|-----------------|--------------------------------------|
| Backend         | Java 17, Spring Boot, Spring Data JPA|
| Banco de Dados  | Oracle (produção), H2 (testes)       |
| Ferramentas     | Gradle, Swagger, Docker               |

## Configuração

### Pré-requisitos
- Java 17+
- Oracle Database ou Docker (para ambiente de produção)
- Gradle
###  Banco de Dados

O projeto utiliza duas configurações distintas de banco de dados, conforme o ambiente:

####  Testes: H2 (em memória)

Durante a execução dos testes, é utilizado o banco **H2**, configurado automaticamente por meio do profile `test`.  
Essa abordagem elimina a necessidade de uma instância Oracle local apenas para rodar os testes automatizados.

---

#### Execução da Aplicação: Oracle

Para executar a aplicação em ambiente de desenvolvimento, homologação ou produção, é necessário utilizar um banco de dados **Oracle**.

As credenciais e parâmetros de conexão devem ser definidos no arquivo `application.properties` caso queira apontar para uma instância do oracle local.
Mas tambem pode ser obtida uma instancia via container Docker. Para isso existe um arquivo em src/main/resources/docker-compose.yaml que sobe um container Docker do Oracle já configurada

### Instalação
1. Clone o repositório:
```bash
git clone https://github.com/seu-usuario/xpto-financeiro.git
```

2. Configure o banco de dados (Oracle):
 Configurando credencias do banco Orable local no application.properties OU rodando container Docker via comando
 ```bash
docker-compose up
```
ou

```bash
spring.datasource.url=jdbc:oracle:thin:@localhost:1521/XEPDB1
spring.datasource.username=app
spring.datasource.password=app
```
3. Build a aplicação:
```bash
./gradlew clean build --refresh-dependencies 
```
4. Execute a aplicação:
```bash
./gradlew bootRun 
```

5. Execute a procedure
   Essa procedure é responsável por fazer a integracao de clientes já configurados com conta e movimentaçoes. Mas a inclusão desses dados tbm pode ser feito via REST chamando os respectivos endpoints de CRUD de cliente, conta, endereco e movimentacao.
```bash
prc_integracao_dados.sql
```

6. Acesse a documentação #Swagger# da API em:
   O projeto conta com uma documentação detalhada de todos os endpoints disponíveis, seus parâmetros esperados e respectivos retornos na documentação OpenApi/Swagger que pode ser acessada via:
```bash
http://localhost:8080/swagger-ui/index.html#/
 ```
 ##  Relatórios
 Além dos endpois de CRUD REST, também temos os endpoints responsáveis por gerar relatórios pertientes.
 Os relatórios são gerados em formato .txt e podem ser acessados via endpoints específicos. Ex:
 
 1 - Relatorio desaldo do cliente:
 ```bash
 	http://localhost:8080/relatorio/saldo-cliente/txt?uuidCliente={uuidCLiente}
 ```
 2 - Relatório de saldo de cliente por periodo:
```bash
     http://localhost:8080/relatorio/saldo-cliente-por-periodo/txt?uuidCliente={uuidCliente}&dataInicio=2025-04-01&dataFim=2025-05-12
```
 3 - Relatório de receitas:
 ```bash
 	http://localhost:8080/relatorio/receitas/txt?dataInicio=2025-04-12&dataFim=2025-05-12
 ```
 4 - Relatóriode Saldo Geral:
 ```bash
 	http://localhost:8080/relatorio/saldo-geral/txt
 ```
