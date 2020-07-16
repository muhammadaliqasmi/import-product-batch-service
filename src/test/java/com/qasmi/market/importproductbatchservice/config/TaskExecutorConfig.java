
package com.qasmi.market.importproductbatchservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

/**
 * This class implements configuration for task executor.
 *
 * @author Muhammad Ali Qasmi
 * @since 1.0.0
 */
@Profile("test")
@Configuration
public class TaskExecutorConfig {

    /**
     * @return {@link TaskExecutor} for step.
     */
    @Bean
    public TaskExecutor taskExecutorForStep() {
        final SyncTaskExecutor syncTaskExecutor = new SyncTaskExecutor();
        return syncTaskExecutor;
    }

    /**
     * @return {@link TaskExecutor} for job.
     */
    @Bean
    public TaskExecutor taskExecutorForJob() {
        final SyncTaskExecutor syncTaskExecutor = new SyncTaskExecutor();
        return syncTaskExecutor;
    }

}
