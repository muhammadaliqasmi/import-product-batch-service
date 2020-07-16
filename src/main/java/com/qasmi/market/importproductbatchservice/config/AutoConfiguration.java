package com.qasmi.market.importproductbatchservice.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * Provides configuration for spring's auto configuration.
 *
 * @author Muhammad Ali Qasmi
 * @since 1.0.0
 */
@Configuration
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
public class AutoConfiguration {

}
