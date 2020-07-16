package com.qasmi.market.importproductbatchservice.config;

import javax.sql.DataSource;

import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Houses all the batch related configurations
 *
 * @author Muhammad Ali Qasmi
 * @since 1.0.0
 */
@Configuration
public class BatchConfig {

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    @Value("${spring.datasource.username}")
    private String dataSourceUsername;

    @Value("${spring.datasource.password}")
    private String dataSourcePassword;

    /**
     * Loads data source for spring batch.
     *
     * @return {@link DataSource} for spring batch.
     */
    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(dataSourceUrl);
        dataSource.setUsername(dataSourceUsername);
        dataSource.setPassword(dataSourcePassword);
        return dataSource;
    }

    /**
     * Job Launcher for launching jobs.
     *
     * @param jobRepository Instance of {@link JobRepository}.
     * @param taskExecutor Instance of {@link TaskExecutor}.
     * @return JobLauncher that launches jobs asynchronously.
     */
    @Bean
    public SimpleJobLauncher simpleJobLauncher(final JobRepository jobRepository,
            @Qualifier("taskExecutorForJob") final TaskExecutor taskExecutor) {
        final SimpleJobLauncher simpleJobLauncher = new SimpleJobLauncher();
        simpleJobLauncher.setJobRepository(jobRepository);
        simpleJobLauncher.setTaskExecutor(taskExecutor);
        return simpleJobLauncher;
    }
}
