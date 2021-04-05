
package com.qasmi.market.importproductbatchservice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.qasmi.market.importproductbatchservice.domain.Product;
import com.qasmi.market.importproductbatchservice.job.step.processor.ExportProcessor;
import com.qasmi.market.importproductbatchservice.service.ProductService;

/**
 * This class implements all configuration for step item processors of the batch job.
 * 
 * @author Muhammad Ali Qasmi
 * @see 1.0
 */
@Configuration
public class ProcessorConfig {

    private static final Logger logger = LoggerFactory.getLogger(ProcessorConfig.class);

    /**
     * @return import product processor.
     */
    @Bean
    @StepScope
    public ItemProcessor<Product, Product> importProcessor(@Value("#{jobExecutionContext[jobInstanceId]}") final Long jobInstanceId, //
            @Value("#{jobExecutionContext[currentStepExecutionId]}") final Long stepExecutionId) {
        return new ItemProcessor<Product, Product>() {
            @Override
            public Product process(final Product item) throws Exception {
                logger.trace("Importing {}", item);
                item.setJobInstanceId(jobInstanceId);
                item.setStepExecutionId(stepExecutionId);
                return item;
            }
        };
    }
    
    /**
     * @return export product processor.
     */
    @Bean
    @StepScope
    public ItemProcessor<Product, Product> exportProcessor(final ProductService productService, // 
            @Value("#{jobExecutionContext[jobInstanceId]}") final Long jobInstanceId, //
            @Value("#{jobExecutionContext[currentStepExecutionId]}") final Long stepExecutionId) {
        return new ExportProcessor(productService, jobInstanceId, stepExecutionId);
    }

}
