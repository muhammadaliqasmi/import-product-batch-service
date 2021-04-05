package com.qasmi.market.importproductbatchservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.qasmi.market.importproductbatchservice.domain.Product;

/**
 * This interface exposes client for product-service 
 * 
 * @author Muhammad Ali Qasmi
 * @since 1.0
 */
@FeignClient(name = "product-service", url = "${application.export-product}")
public interface ProductServiceClient {

    @PostMapping(path = "/products", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity<Product> save(@RequestBody Product product);
}
