
package com.qasmi.market.importproductbatchservice.config;

import static com.qasmi.market.importproductbatchservice.constants.JobConstants.*;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParametersIncrementer;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.qasmi.market.importproductbatchservice.job.CustomizedJobExecutionListener;
import com.qasmi.market.importproductbatchservice.job.CustomizedJobParametersValidator;
import com.qasmi.market.importproductbatchservice.job.step.StepExecutionDecider;

/**
 * This class houses all configurations for batch jobs.
 * 
 * @author Muhammad Ali Qasmi
 * @since 1.0
 */
@Configuration
public class JobConfig {

    /**
     * This method configures and implements import product job.
     * 
     * @param jobBuilderFactory Build factor for the job.
     * @param jobParametersIncrementer The id generator for job.
     * @param validator Validator for the job.
     * @param listener Listener for the job.
     * @param importProduct Step of the job.
     * @param exportProduct Step of the job.
     * @return Import product job instance.
     */
    @Bean
    public Job importProductJob(final JobBuilderFactory jobBuilderFactory,
            @Qualifier("runIdIncrementer") final JobParametersIncrementer jobParametersIncrementer, //
            @Qualifier("customizedJobParametersValidator") final JobParametersValidator validator, //
            @Qualifier("customizedJobExecutionListener") final JobExecutionListener listener,
            @Qualifier("jobMainFlow") final Flow jobMainFlow) {
        return jobBuilderFactory.get(IMPORT_PRODUCT_JOB_NAME) //
                .incrementer(jobParametersIncrementer) //
                .listener(listener) //
                .start(jobMainFlow) //
                .end() //
                .build();
    }
    
    @Bean
    public Flow jobMainFlow(@Qualifier("importProduct") final Step importProduct,
            @Qualifier("exportProduct") final Step exportProduct) {
        final StepExecutionDecider stepExecutionDecider = new StepExecutionDecider();
        return new FlowBuilder<Flow>("exportProductFlow") //
                .start(importProduct) //
                .next(stepExecutionDecider) //
                .on(StepExecutionDecider.EXECUTE_EXPORT_STEP) //
                .to(exportProduct) //
                .from(stepExecutionDecider) //
                .on(FlowExecutionStatus.COMPLETED.toString()) //
                .end() //
                .build();
    }

    /**
     * @return The instance of customized job execution listener.
     */
    @Bean
    public JobExecutionListener customizedJobExecutionListener() {
        return new CustomizedJobExecutionListener();
    }

    /**
     * Validates parameters required for successful job execution.
     *
     * @return Instance of {@link JobParametersValidator}.
     */
    @Bean
    public JobParametersValidator customizedJobParametersValidator() {
        return new CustomizedJobParametersValidator();
    }

    /**
     * Generates job execution Id.
     *
     * @return Instance of {@link RunIdIncrementer}
     */
    @Bean
    public RunIdIncrementer runIdIncrementer() {
        return new RunIdIncrementer();
    }

}
