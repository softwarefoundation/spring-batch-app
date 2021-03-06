package com.softwarefoundation.springbatchapp.jobs;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.MultiResourceItemReaderBuilder;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Configuration
public class ArquivoReaderConfig {


    @StepScope
    @Bean
    @Qualifier("arquivoComLarguraFixaReader")
    public FlatFileItemReader<Cliente> arquivoComLarguraFixaReader(@Value("#{jobParameters['clientes-largura-fixa']}") Resource arquivoClientes) {
        return arquivoLarguraFixaReader(arquivoClientes);
    }

    @StepScope
    @Bean
    @Qualifier("arquivoComDelimitadorReader")
    public FlatFileItemReader<Cliente> arquivoComDelimitadorReader(@Value("#{jobParameters['clientes-com-delimitador']}") Resource arquivoClientes) {
        return arquivoDelimitadoReader(arquivoClientes);
    }

    @StepScope
    @Bean
    @Qualifier("arquivoComLineMaperReader")
    public FlatFileItemReader<Cliente> arquivoComLineMaperReader(@Value("#{jobParameters['clientes-multiplos-tipos']}") Resource arquivoClientes, LineMapper lineMapper) {
        return arquivoCustomizadoReader(arquivoClientes, lineMapper);
    }

    @StepScope
    @Bean
    @Qualifier("multiplosArquivoReader")
    public MultiResourceItemReader multiplosArquivoReader(@Value("#{jobParameters['multiplos-arquivos-cliente']}") Resource[] arquivoClientes, @Qualifier("arquivoComLineMaperReader") FlatFileItemReader reader) {
        return multiplosArquivosReader(arquivoClientes, reader);
    }

    @StepScope
    @Bean
    @Qualifier("jdbcCursorReader")
    public JdbcCursorItemReader<Cliente> jdbcCursorReader(@Qualifier("appDataSource") DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<Cliente>()
                .name("JDBC Cursor reader")
                .dataSource(dataSource)
                .sql("SELECT * FROM TB01_CLIENTE")
                .rowMapper(new BeanPropertyRowMapper<Cliente>(Cliente.class))
                .build();
    }

    @StepScope
    @Bean
    @Qualifier("jdbcPagingItemReader")
    public JdbcPagingItemReader<Cliente> jdbcPagingItemReader(@Qualifier("appDataSource") DataSource dataSource, PagingQueryProvider queryProvider) {
        return new JdbcPagingItemReaderBuilder<Cliente>()
                .name("JDBC paginação reader")
                .dataSource(dataSource)
                .queryProvider(queryProvider)
                .pageSize(2)
                .rowMapper(new BeanPropertyRowMapper<Cliente>(Cliente.class))
                .build();
    }

    @Bean
    public SqlPagingQueryProviderFactoryBean queryProvider(@Qualifier("appDataSource") DataSource dataSource) {
        SqlPagingQueryProviderFactoryBean query = new SqlPagingQueryProviderFactoryBean();
        query.setDataSource(dataSource);
        query.setSelectClause("SELECT *");
        query.setFromClause("FROM TB01_CLIENTE");
        query.setSortKey("id");
        return query;
    }

    @StepScope
    @Bean
    public JdbcCursorItemReader<Cliente> skipExceptionReader(@Qualifier("appDataSource") DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<Cliente>()
                .name("JDBC Cursor reader")
                .dataSource(dataSource)
                .sql("SELECT * FROM TB01_CLIENTE")
                .rowMapper(rowMapper())
                .build();
    }

    @StepScope
    @Bean
    public FlatFileItemReader<Cliente> arquivoParaValidacaoReader(@Value("#{jobParameters['clientes-validacao']}") Resource arquivo) {
        FlatFileItemReader reader = new FlatFileItemReaderBuilder()
                .name("Arquivo para validação Reader")
                .resource(arquivo)
                .delimited()
                .names(new String[]{"nome", "idade", "email"})
                .targetType(Cliente.class)
                .build();
        return reader;
    }

    private RowMapper<Cliente> rowMapper() {
        return new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet rs, int i) throws SQLException {

                if (rs.getRow() > 4) {
                    throw new SQLException(String.format("Encerrando a execução - Cliente %s", rs.getString("nome")));
                } else {
                    return clienteRowMapper(rs);
                }

            }
        };
    }

    private Cliente clienteRowMapper(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setNome(rs.getString("nome"));
        cliente.setSobrenome(rs.getString("sobrenome"));
        cliente.setIdade(rs.getInt("idade"));
        cliente.setEmail(rs.getString("email"));


        return cliente;
    }


    /**
     * @param arquivo
     * @return
     */
    private FlatFileItemReader<Cliente> arquivoLarguraFixaReader(Resource arquivo) {
        FlatFileItemReader reader = new FlatFileItemReaderBuilder<Cliente>()
                .name("arquivoLarguraFixaReader")
                .resource(arquivo)
                .fixedLength()
                .columns(new Range(1, 10), new Range(11, 20), new Range(21, 23), new Range(24, 43))
                .names(new String[]{"nome", "sobrenome", "idade", "email"})
                .targetType(Cliente.class)
                .build();
        return reader;
    }

    /**
     * @param arquivo
     * @return
     */
    private FlatFileItemReader<Cliente> arquivoDelimitadoReader(Resource arquivo) {
        FlatFileItemReader reader = new FlatFileItemReaderBuilder<Cliente>()
                .name("arquivoDelimitadoReader")
                .resource(arquivo)
                .delimited()
                .names(new String[]{"nome", "sobrenome", "idade", "email"})
                .targetType(Cliente.class)
                .build();
        return reader;
    }


    /**
     * @param arquivo
     * @param lineMapper
     * @return
     */
    private FlatFileItemReader arquivoCustomizadoReader(Resource arquivo, LineMapper lineMapper) {
        FlatFileItemReader reader = new FlatFileItemReaderBuilder()
                .name("arquivoCustomizadoReader")
                .resource(arquivo)
                .lineMapper(lineMapper)
                .build();
        return reader;
    }

    /**
     * @param arquivos
     * @param reader
     * @return
     */
    private MultiResourceItemReader multiplosArquivosReader(Resource[] arquivos, FlatFileItemReader reader) {
        MultiResourceItemReader multiResourceItemReader = new MultiResourceItemReaderBuilder()
                .name("Multiplos arquivos Reader")
                .resources(arquivos)
                .delegate(new ArquivoClienteTransacaoResourceReaderConfig(reader))
                .build();
        return multiResourceItemReader;
    }

}
