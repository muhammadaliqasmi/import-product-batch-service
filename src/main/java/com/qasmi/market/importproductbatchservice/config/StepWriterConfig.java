
package com.qasmi.market.importproductbatchservice.config;

import static com.qasmi.market.importproductbatchservice.constants.JobConstants.*;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;

import com.qasmi.market.importproductbatchservice.domain.Product;

/**
 * This class implements all configuration related to step item writers for the batch job.
 * 
 * @author Muhammad Ali Qasmi
 * @since 1.0
 */
@Configuration
public class StepWriterConfig {

    private static final String COLLECTION_NAME = PRDOUCT_COLLECTION_NAME;

    /**
     * Configures item writer for mongodb.
     *
     * @param template Instance of {@link MongoOperations}
     * @return Instance of {@link MongoItemWriter}
     */
    @Bean
    @StepScope
    public MongoItemWriter<Product> writerForMongodb(final MongoOperations template) {
        final MongoItemWriter<Product> writer = new MongoItemWriter<>();
        writer.setTemplate(template);
        writer.setCollection(COLLECTION_NAME);
        return writer;
    }
}
