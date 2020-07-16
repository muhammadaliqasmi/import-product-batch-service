
package com.qasmi.market.importproductbatchservice.job;

import static com.qasmi.market.importproductbatchservice.constants.JobConstants.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.batch.item.ExecutionContext;

/**
 * This class implements job's listener to perform any action before and/or after the
 * execution of any batch job.
 *
 * @author Muhammad Ali Qasmi
 * @since 1.0.0
 */
public class CustomizedJobExecutionListener extends JobExecutionListenerSupport {

    private static final Logger logger = LoggerFactory.getLogger(
            CustomizedJobExecutionListener.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void beforeJob(final JobExecution jobExecution) {
        super.beforeJob(jobExecution);
        setJobExecutionIdInExecutionContext(jobExecution);
        logger.trace("{} job started!", IMPORT_PRODUCT_JOB_NAME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void afterJob(final JobExecution jobExecution) {
        super.afterJob(jobExecution);
        logger.trace("{} job completed!", IMPORT_PRODUCT_JOB_NAME);
    }

    private void setJobExecutionIdInExecutionContext(final JobExecution jobExecution) {
        final ExecutionContext executionContext = jobExecution.getExecutionContext();
        final Long jobInstanceId = jobExecution.getJobInstance().getId();
        executionContext.put(JOB_INSTANCE_ID_KEY, jobInstanceId);
    }

}
