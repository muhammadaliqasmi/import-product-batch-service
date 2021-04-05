
package com.qasmi.market.importproductbatchservice;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Represents the Application class which houses the main entry-point to run the
 * application
 * 
 * @author Muhammad Ali Qasmi
 * @since 1.0
 */
@EnableFeignClients
@EnableBatchProcessing
@SpringBootApplication
public class ImportProductBatchServiceApplication {

    /**
     * main entry-point
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(ImportProductBatchServiceApplication.class, args);
    }

}
