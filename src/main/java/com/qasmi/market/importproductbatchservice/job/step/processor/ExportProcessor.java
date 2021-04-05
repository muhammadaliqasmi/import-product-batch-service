package com.qasmi.market.importproductbatchservice.job.step.processor;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.qasmi.market.importproductbatchservice.domain.Product;
import com.qasmi.market.importproductbatchservice.service.ProductService;

/**
 * This class implements processor to export products.
 * 
 * @author Muhammad Ali Qasmi
 * @since 1.0
 */
public class ExportProcessor implements ItemProcessor<Product, Product> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    private final ProductService productService;
    
    private final Long jobInstanceId;
    
    private final Long stepExecutionId;
    
    /**
     * 
     * @param productService
     */
    public ExportProcessor(final ProductService productService, // 
            final Long jobInstanceId, //
            final Long stepExecutionId) {
        this.productService = productService;
        this.jobInstanceId = jobInstanceId;
        this.stepExecutionId = stepExecutionId;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Product process(Product item) throws Exception {
        
        if(null == item || StringUtils.isBlank(item.getId())) {
            return null;
        }
        
        logger.trace("Exporting product={}", item);
        this.productService.save(item);
        
        item.setJobInstanceId(this.jobInstanceId);
        item.setStepExecutionId(this.stepExecutionId);
        return item;
    }

}
