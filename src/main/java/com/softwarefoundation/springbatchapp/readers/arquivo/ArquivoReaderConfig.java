package com.softwarefoundation.springbatchapp.readers.arquivo;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
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

import javax.sql.DataSource;

@Configuration
public class ArquivoReaderConfig {



    @StepScope
    @Bean
    @Qualifier("arquivoComLarguraFixaReader")
    public FlatFileItemReader<Cliente> arquivoComLarguraFixaReader(@Value("#{jobParameters['clientes-largura-fixa']}") Resource arquivoClientes) {
        return  arquivoLarguraFixaReader(arquivoClientes);
    }

    @StepScope
    @Bean
    @Qualifier("arquivoComDelimitadorReader")
    public FlatFileItemReader<Cliente> arquivoComDelimitadorReader(@Value("#{jobParameters['clientes-com-delimitador']}") Resource arquivoClientes) {
        return  arquivoDelimitadoReader(arquivoClientes);
    }

    @StepScope
    @Bean
    @Qualifier("arquivoComLineMaperReader")
    public FlatFileItemReader<Cliente> arquivoComLineMaperReader(@Value("#{jobParameters['clientes-multiplos-tipos']}") Resource arquivoClientes, LineMapper lineMapper) {
        return  arquivoCustomizadoReader(arquivoClientes,lineMapper);
    }

    @StepScope
    @Bean
    @Qualifier("multiplosArquivoReader")
    public MultiResourceItemReader multiplosArquivoReader(@Value("#{jobParameters['multiplos-arquivos-cliente']}") Resource[] arquivoClientes, @Qualifier("arquivoComLineMaperReader") FlatFileItemReader reader) {
        return  multiplosArquivosReader(arquivoClientes, reader);
    }

    @StepScope
    @Bean
    @Qualifier("jdbcCursorReader")
    public JdbcCursorItemReader<Cliente> jdbcCursorReader(@Qualifier("appDataSource")DataSource dataSource){
        return new JdbcCursorItemReaderBuilder<Cliente>()
                .name("JDBC Cursor reader")
                .dataSource(dataSource)
                .sql("SELECT * FROM TB01_CLIENTE")
                .rowMapper(new BeanPropertyRowMapper<Cliente>(Cliente.class))
                .build();
    }

    /**
     *
     * @param arquivo
     * @return
     */
    private FlatFileItemReader<Cliente> arquivoLarguraFixaReader(Resource arquivo){
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
     *
     * @param arquivo
     * @return
     */
    private FlatFileItemReader<Cliente> arquivoDelimitadoReader(Resource arquivo){
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
     *
     * @param arquivo
     * @param lineMapper
     * @return
     */
    private FlatFileItemReader arquivoCustomizadoReader(Resource arquivo, LineMapper lineMapper){
        FlatFileItemReader reader = new FlatFileItemReaderBuilder()
                .name("arquivoCustomizadoReader")
                .resource(arquivo)
                .lineMapper(lineMapper)
                .build();
        return reader;
    }

    /**
     *
     *
     * @param arquivos
     * @param reader
     * @return
     */
    private MultiResourceItemReader multiplosArquivosReader(Resource[] arquivos, FlatFileItemReader reader){
        MultiResourceItemReader multiResourceItemReader = new MultiResourceItemReaderBuilder()
                .name("Multiplos arquivos Reader")
                .resources(arquivos)
                .delegate(new ArquivoClienteTransacaoResourceReaderConfig(reader))
                .build();
        return multiResourceItemReader;
    }

}
