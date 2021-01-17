package com.yrc.aisservice.config;

import com.yrc.aisservice.entity.DataSeg;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import javax.sql.DataSource;

@Configuration
public class CsvBatchJobConfig {

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Autowired
    DataSource dataSource;

    @Bean
    @StepScope
    FlatFileItemReader<DataSeg> itemReader() {
        FlatFileItemReader<DataSeg> reader = new FlatFileItemReader<>();
        reader.setLinesToSkip(1);
        reader.setResource(new FileSystemResource("text.csv"));
        reader.setLineMapper(new DefaultLineMapper<DataSeg>(){{
            setLineTokenizer(new DelimitedLineTokenizer(){{
                setNames("MMSI","BaseDateTime","LAT","LON","SOG","COG","Heading","VesselName","IMO","CallSign","VessleType","Status","Length","Width","Draft","Cargo");
                setDelimiter(",");
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper(){{
                setTargetType(DataSeg.class);
            }});
        }});
        return reader;
    }

    @Bean
    JdbcBatchItemWriter jdbcBatchItemWriter() {
        JdbcBatchItemWriter writer = new JdbcBatchItemWriter();
        writer.setDataSource(dataSource);
        writer.setSql("insert into dataseg(MMSI,BaseDateTime,SOG,COG)" + "values(:MMSI,:BaseDateTime,:SOG,:COG)");
        writer.setItemSqlParameterSourceProvider(
                new BeanPropertyItemSqlParameterSourceProvider<>()
        );
        return writer;
    }

    @Bean
    Step csvStep() {
        return stepBuilderFactory.get("csvStep")
                .<DataSeg,DataSeg>chunk(2)
                .reader(itemReader())
                .writer(jdbcBatchItemWriter())
                .build();
    }

    @Bean
    Job csvJob() {
        return jobBuilderFactory.get("csvJob")
                .start(csvStep())
                .build();
    }
}
