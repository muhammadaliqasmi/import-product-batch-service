package com.qasmi.market.importproductbatchservice.job.step;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;

public class StepExecutionDecider implements JobExecutionDecider {

    public static final String EXECUTE_EXPORT_STEP = "executeExportStep";
    /**
     * Note this is hard coded. Value should come from execution context
     */
    private static final boolean ENABLE_EXPORT_PRODUCT = true;
    
    @Override
    public FlowExecutionStatus decide(JobExecution jobExecution,
            StepExecution stepExecution) {
        if(ENABLE_EXPORT_PRODUCT) {
            return new FlowExecutionStatus(EXECUTE_EXPORT_STEP);
        } else {
            return FlowExecutionStatus.COMPLETED;
        }
    }

}
