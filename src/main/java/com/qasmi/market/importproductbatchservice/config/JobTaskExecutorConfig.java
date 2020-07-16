package com.qasmi.market.importproductbatchservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

/**
 * {@link JobTaskExecutorConfig} provides configuration for task execution in step.
 *
 * @author Muhammad Ali Qasmi
 * @since 1.0.0
 */
@Configuration
public class JobTaskExecutorConfig {

    /**
     * Configurations to execute jobs as separate threads. Used for executing jobs in parallel. Creates thread pool of size {@link BatchConfigurationContants#JOB_CONCURRENCY_LIMIT}. Note: this
     * bean is only available in non-test profiles.
     *
     * @param concurrencyLimit Maximum number of allowed parallel jobs.
     * @return {@link TaskExecutor} instance.
     */
    @Profile("!test")
    @Bean
    public TaskExecutor taskExecutorForJob(
            @Value("${application.max-parallel-jobs}") final Integer concurrencyLimit) {
        final SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
        asyncTaskExecutor.setConcurrencyLimit(concurrencyLimit);
        return asyncTaskExecutor;
    }

}
