# 📚 Sistema de Gerenciamento de Biblioteca

Este projeto é um sistema simples de gerenciamento de biblioteca desenvolvido em Java. Ele permite o cadastro de autores, livros, membros, empréstimos de livros e devoluções, além de gerar relatórios sobre os empréstimos e multas aplicadas. O projeto utiliza o MySQL para persistência de dados e uma estrutura modular com serviços e repositórios.

## 🚀 Como Usar

### 🖥️ Menu Principal

O sistema apresenta um menu principal com as seguintes opções:

1. **Autor**: Permite cadastrar ou listar autores.
2. **Membro**: Permite cadastrar ou listar membros.
3. **Livro**: Permite cadastrar ou listar livros.
4. **Empréstimo**: Permite cadastrar novos empréstimos, realizar devoluções ou listar os empréstimos existentes.
5. **Relatórios**: Permite gerar relatórios sobre livros emprestados e membros com multas.
6. **Sair**: Encerra o sistema.

### 📜 Submenus

- Ao escolher uma opção do menu principal, o sistema irá solicitar informações adicionais ou apresentar subopções para cadastro e listagem.

### 💡 Exemplo de Uso

1. Para cadastrar um novo autor, escolha a opção "1) Autor" no menu principal e, em seguida, "1) Cadastrar Autor".
2. Informe os dados do autor conforme solicitado.
3. O autor será armazenado no banco de dados e poderá ser listado posteriormente.

## 📁 Estrutura do Projeto

O projeto está organizado em pacotes conforme a seguinte estrutura:

- **aplicacao**: Contém a classe `Programa`, que é o ponto de entrada da aplicação e a interface com o usuário.
- **bd**: Contém a classe `BD`, responsável pela conexão com o banco de dados MySQL.
- **excecoes**: Contém classes de exceção personalizadas, como `Mensagem`, usada para lançar erros específicos durante a execução.
- **modelos.entidades**: Contém as classes de domínio, como `Autor`, `Livro`, `Membro`, `Emprestimo` e `Pessoa`, que representam as entidades do sistema.
- **modelos.entidades**: Contém a classe de enumeração, `EstadoEmprestimo` que os estados que um emprestimo possui.
- **modelos.repositorios**: Contém classes de repositórios, responsáveis pela interação com o banco de dados para cada entidade.
- **modelos.servicos**: Contém as classes de serviço que encapsulam a lógica de negócio, como `AutorServico`, `LivroServico`, `MembroServico`, e `EmprestimoServico`.
- **modelos.relatorios**: Contém a classe `RelatorioEmprestimo`, que gera relatórios sobre os empréstimos e multas.

## 🛠️ Ferramentas utilizadas

- Java 22 ou superior
- MySQL 8.0 ou superior
- JDBC para conexão com o banco de dados MySQL

## 📥 Instalação e Configuração

### 1. Clonar o Repositório

Clone este repositório em sua máquina local.

```
git clone https://github.com/lucasrengel/SP_SpringBoot_AWS_Desafio_01.git
```

### 2. Configurar o Banco de Dados

- Antes de executar o projeto, certifique-se de que você tem um servidor MySQL rodando. Em seguida, crie o banco de dados.

- Você pode usar o script SQL fornecido (script.sql) ou abrir o arquivo e copiar e colar na Query do MySQL. Siga os passos abaixo para executar o script no MySQL Workbench:
  - Inicie o MySQL Workbench e conecte-se ao seu servidor MySQL.
  - Importar o script SQL: No menu, vá para Server > Data Import
  - Em Import Options escolha a opção Import from Self-Contained File e selecione o arquivo script.sql que se encontra na pasta do projeto.
  - Para executar o script clique em Start Import.

- Após a execução, você pode verificar se o Schema e as tabelas foram criadas corretamente expandindo o banco de dados no painel Navigator e visualizando as tabelas.

### ⚙️ Se precisar 

Altere as configurações de conexão no arquivo `BD.java`, se necessário.

```
           String server = "localhost";
            String database = "desafio-01";
            String url = "jdbc:mysql://" + server + ":3306/" + database;

            String user = "root";
            String password = "12345";
```

### 3. Executar o Projeto

Para rodar o projeto, compile e execute a classe `Programa`, que contém o método `main`. O sistema apresentará um menu interativo no terminal, onde você poderá escolher as opções de cadastro, empréstimo, devolução, e geração de relatórios.


## 🎉 Considerações Finais

Sinta-se à vontade para contribuir com o projeto, relatar problemas ou buscar suporte!
