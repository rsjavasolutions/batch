package com.rsjavasolutions.batch.configuration;

import com.rsjavasolutions.batch.model.CarEntity;
import com.rsjavasolutions.batch.model.CarRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class SpringBatchConfig {

    @Value("${file.input}")
    private String fileInput;

    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory,
                   StepBuilderFactory stepBuilderFactory,
                   ItemReader<CarRequest> itemReader,
                   ItemProcessor<CarRequest, CarEntity> itemProcessor,
                   ItemWriter<CarEntity> itemWriter) {

        Step step = stepBuilderFactory.get("car-file-load")
                .<CarRequest, CarEntity>chunk(100)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();

        return jobBuilderFactory.get("car-load")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

    @Bean
    public FlatFileItemReader reader() {
        return  new FlatFileItemReaderBuilder()
                .name("Car-Reader")
                .resource(new ClassPathResource(fileInput))
                .delimited()
                .names("brand", "model", "bodyType", "year")
                .fieldSetMapper(new BeanWrapperFieldSetMapper() {{
                    setTargetType(CarRequest.class);
                }})
                .linesToSkip(1)
                .build();
    }
}
