package com.qasmi.market.importproductbatchservice.config;

import java.net.ConnectException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.backoff.BackOffPolicy;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;

/**
 * This class houses configurations related to steps which interact with different
 * services.
 *
 * @author Muhammad Ali Qasmi
 * @since 1.0.0
 */
@Configuration
public class ServiceStepConfig {

    /**
     * Retry policy.
     *
     * @param maxRetryAttempts Maximum number of retry attempts.
     *
     * @return {@link SimpleRetryPolicy} instance that retries {@link ExecutionContextConstants#MAX_RETRY_ATTEMPTS} times.
     * @see RetryPolicy
     */
    @Bean
    public RetryPolicy simpleRetryPolicy(
            @Value("${application.max-retry-attempts}") final int maxRetryAttempts) {
        return new SimpleRetryPolicy(maxRetryAttempts,
                Collections.<Class<? extends Throwable>, Boolean> singletonMap(
                        ConnectException.class, true),
                true);
    }

    /**
     * Backoff Policy.
     *
     * @param initialIntervalSleep Initial sleep interval.
     *
     * @return {@link ExponentialBackOffPolicy} instance.
     * @see BackOffPolicy
     */
    @Bean
    public BackOffPolicy exponentialBackOffPolicy(
            @Value("${application.initial-interval-sleep}") final int initialIntervalSleep) {
        final ExponentialBackOffPolicy exponentialBackOffPolicy = new ExponentialBackOffPolicy();
        exponentialBackOffPolicy.setInitialInterval(initialIntervalSleep);
        return exponentialBackOffPolicy;
    }
}
