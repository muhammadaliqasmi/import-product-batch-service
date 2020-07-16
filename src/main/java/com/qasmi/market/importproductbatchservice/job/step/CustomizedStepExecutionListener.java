
package com.qasmi.market.importproductbatchservice.job.step;

import static com.qasmi.market.importproductbatchservice.constants.JobConstants.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.batch.item.ExecutionContext;

/**
 * This class implements step's listener to perform any action before and/or after the
 * execution of any step.
 *
 * @author Muhammad Ali Qasmi
 * @since 1.0.0
 */
public class CustomizedStepExecutionListener extends StepExecutionListenerSupport {

    private static final Logger logger = LoggerFactory.getLogger(
            CustomizedStepExecutionListener.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void beforeStep(final StepExecution stepExecution) {
        final ExecutionContext executionContext = stepExecution.getJobExecution() //
                .getExecutionContext();
        
        executionContext.put(CURRENT_STEP_EXECUTION_ID, stepExecution.getId());
        
        logger.trace("{} step started!", stepExecution.getStepName());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ExitStatus afterStep(final StepExecution stepExecution) {
        final ExecutionContext executionContext = stepExecution.getJobExecution() //
                .getExecutionContext();

        // only set previous step id if the step execution was successful
        if (!stepExecution.getStatus().isUnsuccessful()) {
            executionContext.put(PREVIOUS_STEP_EXECUTION_ID, stepExecution.getId());
        }

        logger.trace("{} step {}!", stepExecution.getStepName(), //
                stepExecution.getExitStatus());
        return stepExecution.getExitStatus();
    }

}
