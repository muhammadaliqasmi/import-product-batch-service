
package com.qasmi.market.importproductbatchservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qasmi.market.importproductbatchservice.domain.Product;

import feign.FeignException;

/**
 * This class implments service for {@link ProductServiceClient}.
 * 
 * @author Muhammad Ali Qasmi
 * @since 1.0
 */
@Service
public class ProductService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ProductServiceClient productServiceClient;

    @Autowired
    public ProductService(final ProductServiceClient productServiceClient) {
        this.productServiceClient = productServiceClient;
    }

    public Product save(final Product product) {
        try {
           final Product tempProduct =  this.productServiceClient.save(product).getBody();
           logger.debug("Exported product", tempProduct);
        } catch (final FeignException feignException) {
            logger.error("Failed to save {} because {}", product,
                    feignException.getMessage());
        }
        return null;
    }

}
