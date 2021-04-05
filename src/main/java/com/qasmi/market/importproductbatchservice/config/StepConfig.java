
package com.qasmi.market.importproductbatchservice.config;

import static com.qasmi.market.importproductbatchservice.constants.JobConstants.*;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.qasmi.market.importproductbatchservice.domain.Product;
import com.qasmi.market.importproductbatchservice.job.step.CustomizedStepExecutionListener;

/**
 * This class houses all configuration for steps of the job.
 * 
 * @author Muhammad Ali Qasmi
 * @since 1.0
 */
@Configuration
public class StepConfig {

    /**
     * This method implements and configures import product step.
     * 
     * @param stepBuilderFactory Instance of step builder factory.
     * @param chunkSize Size of the chunk to be processed at a time.
     * @param reader The reader of the step.
     * @param processor The processor of the step.
     * @param writer The writer for of the step.
     * @param listener The listener of the step.
     * @return import product step instance.
     */
    @Bean
    public Step importProduct(final StepBuilderFactory stepBuilderFactory, //
            @Value("${application.chunk-size}") final Integer chunkSize,
            @Qualifier("mutiFileXMLReaderForProduct") final MultiResourceItemReader<Product> reader, //
            @Qualifier("importProcessor") final ItemProcessor<Product, Product> processor, //
            @Qualifier("writerForMongodb") final MongoItemWriter<Product> writer,
            @Qualifier("customizedStepExecutionListener") final StepExecutionListener listener) {
        return stepBuilderFactory.get(IMPORT_PRODUCT_STEP_NAME) //
                .<Product, Product> chunk(chunkSize) //
                .reader(reader) //
                .processor(processor) //
                .writer(writer) //
                .listener(listener) //
                .allowStartIfComplete(true) //
                .build();
    }
    
    /**
     * This method implements and configures import product step.
     * 
     * @param stepBuilderFactory Instance of step builder factory.
     * @param chunkSize Size of the chunk to be processed at a time.
     * @param reader The reader of the step.
     * @param processor The processor of the step.
     * @param writer The writer for of the step.
     * @param listener The listener of the step.
     * @return import product step instance.
     */
    @Bean
    public Step exportProduct(final StepBuilderFactory stepBuilderFactory, //
            @Value("${application.chunk-size}") final Integer chunkSize,
            @Qualifier("mongodbReaderForProducts") final ItemReader<Product> reader, //
            @Qualifier("exportProcessor") final ItemProcessor<Product, Product> processor, //
            @Qualifier("writerForMongodb") final MongoItemWriter<Product> writer,
            @Qualifier("customizedStepExecutionListener") final StepExecutionListener listener) {
        return stepBuilderFactory.get(EXPORT_PRODUCT_STEP_NAME) //
                .<Product, Product> chunk(chunkSize) //
                .reader(reader) //
                .processor(processor) //
                .writer(writer) //
                .listener(listener) //
                .allowStartIfComplete(true) //
                .build();
    }

    /**
     * @return The instance of customized step execution listener.
     */
    @Bean
    public StepExecutionListener customizedStepExecutionListener() {
        return new CustomizedStepExecutionListener();
    }

}
