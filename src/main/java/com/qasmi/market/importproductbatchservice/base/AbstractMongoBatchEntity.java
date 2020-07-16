
package com.qasmi.market.importproductbatchservice.base;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Base class for Mongo entity for batch implementations.
 * 
 * @author Muhammad Ali Qasmi
 * @since 1.0
 */
public abstract class AbstractMongoBatchEntity extends AbstractMongoEntity {

    @JsonIgnore
    private Long jobInstanceId;

    @JsonIgnore
    private Long stepExecutionId;

    /**
     * @return the jobInstanceId
     */
    public Long getJobInstanceId() {
        return jobInstanceId;
    }

    /**
     * @param jobInstanceId the jobInstanceId to set
     */
    public void setJobInstanceId(Long jobInstanceId) {
        this.jobInstanceId = jobInstanceId;
    }

    /**
     * @return the stepExecutionId
     */
    public Long getStepExecutionId() {
        return stepExecutionId;
    }

    /**
     * @param stepExecutionId the stepExecutionId to set
     */
    public void setStepExecutionId(Long stepExecutionId) {
        this.stepExecutionId = stepExecutionId;
    }

}
