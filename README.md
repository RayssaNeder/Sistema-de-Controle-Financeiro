#  XPTO - Sistema de Controle Financeiro

Sistema desenvolvido para a empresa XPTO com o objetivo de gerenciar receitas e despesas de clientes (PF e PJ), suas contas bancárias e movimentações financeiras. O projeto inclui funcionalidades de CRUD, geração de relatórios e integração com banco de dados Oracle.

##  Descrição do Projeto

A aplicação permite:

- Cadastro e manutenção de clientes (Pessoa Física e Jurídica)
- Gerenciamento de contas bancárias associadas aos clientes
- Registro de movimentações financeiras (crédito e débito)
- Cálculo da receita da empresa XPTO com base nas movimentações dos clientes
- Geração de relatórios:
  - Saldo do cliente
  - Saldo do cliente por período
  - Saldo geral de todos os clientes
  - Receita da empresa por período

## Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- Oracle Database
- Maven
- Swagger/OpenAPI
- JUnit para testes
- PL/SQL (procedures, triggers ou functions)

##  Estrutura do Projeto

- `com.xpto.demo.controller`: Controllers REST
- `com.xpto.demo.dto`: Data Transfer Objects
- `com.xpto.demo.service`: Regras de negócio
- `com.xpto.demo.repository`: Interfaces de acesso a dados
- `com.xpto.demo.swagger`: Documentação da API
- `com.xpto.demo.model`: Entidades JPA

##  Como Executar o Projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/xpto-financeiro.git

2. Configure o banco de dados Oracle e atualize as credenciais no `application.properties`.

3. Execute a aplicação:
		`./gradlew bootRun `
4. Execute a procedure prc_integracao_dados.sql para realizar a inegração dos clientes na base de dados ou adicionar manualmente via metodos de crud de cliente, endereço, conta e movimentacao

5. Acesse a documentação  Swagger da API em:
 `http://localhost:8080/swagger-ui/index.html#/`
  
 ##  Relatórios
 
 Os relatórios são gerados em formato .txt e podem ser acessados via endpoints específicos. 
 
 1 - Relatorio desaldo do cliente:
 `http://localhost:8080/relatorio/saldo-cliente/txt?uuidCliente={uuidCLiente}`
 2 - Relatório de saldo de cliente por periodo
 `http://localhost:8080/relatorio/saldo-cliente-por-periodo/txt?uuidCliente={uuidCliente}&dataInicio=2025-04-01&dataFim=2025-05-12`
 3 - Relatório de receitas:
 `http://localhost:8080/relatorio/receitas/txt?dataInicio=2025-04-12&dataFim=2025-05-12`
 4 - Relatóriode Salfo Geral:
 `http://localhost:8080/relatorio/saldo-geral/txt`