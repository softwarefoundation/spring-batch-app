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
