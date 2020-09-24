# Desenvolvimento de Jobs com Spring Batch

Neste projeto será desenvolvido um conjunto de Jobs para exemplificar as formas de construção,
características e arquitetura.

### Configuração do Banco de Dados
`spring.datasource.url=jdbc:postgresql://127.0.0.1:5432/postgres?currentSchema=spring_batch`

`spring.datasource.username=postgres`

`spring.datasource.password=root`

`spring.jpa.show-sql=true`

`spring.batch.initialize-schema=always`

### Parâmetros de execução
Para referenciar o arquivo clientes.txt, que está na raiz do projeto,
como parâmetro de entrada adicione a linha abaixo como Program Arguments na IDE.

```arquivoClientes=file:files/clientes.txt```

Multiplos arquivos

```multiplos-arquivos-cliente=file:files/clientes-multiplos-tipos-*```

Tabela tb01_cliente schema app_spring_batch
```#sql
CREATE TABLE app_spring_batch.tb01_cliente (
	id serial NOT NULL,
	nome varchar(1000) NULL,
	sobronome varchar(1000) NULL,
	idade int8 NULL,
	email varchar(1000) NULL,
	CONSTRAINT tb01_cliente_pk PRIMARY KEY (id)
);
```
Registros para insert:
```#sql
INSERT INTO app_spring_batch.tb01_cliente (id, nome, sobronome, idade, email) VALUES(1, 'Ana', 'Maria', 18, 'anamaria@gmail.com');
INSERT INTO app_spring_batch.tb01_cliente (id, nome, sobronome, idade, email) VALUES(2, 'Claudia', 'Ribeiro', 19, 'claudia@gmail.com');
INSERT INTO app_spring_batch.tb01_cliente (id, nome, sobronome, idade, email) VALUES(3, 'Bob', 'Tanny', 21, 'bob@gmail.com');
INSERT INTO app_spring_batch.tb01_cliente (id, nome, sobronome, idade, email) VALUES(4, 'Alice', 'Grey', 29, 'alice@gmail.com');
INSERT INTO app_spring_batch.tb01_cliente (id, nome, sobronome, idade, email) VALUES(5, 'Maria', 'Mary', 52, 'maria@gmail.com');
INSERT INTO app_spring_batch.tb01_cliente (id, nome, sobronome, idade, email) VALUES(6, 'Cristina', 'Thon', 42, 'cristina@gmail.com');
```
