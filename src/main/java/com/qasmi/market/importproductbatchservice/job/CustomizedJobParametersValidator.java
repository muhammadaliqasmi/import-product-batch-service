
package com.qasmi.market.importproductbatchservice.job;

import static com.qasmi.market.importproductbatchservice.constants.JobConstants.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;

/**
 * This class implements job's listener to perform any action before and/or after the
 * execution of any batch job.
 *
 * @author Muhammad Ali Qasmi
 * @since 1.0.0
 */
public class CustomizedJobParametersValidator implements JobParametersValidator {

    private final List<String> errors = new ArrayList<String>();
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void validate(JobParameters parameters) throws JobParametersInvalidException {
        checkWhetherJobParameterExists(parameters, JOB_PARAMETER_TIME_KEY);
    }
    
    private void checkWhetherJobParameterExists(final JobParameters parameters, //
            final String jobParameterKey) {
        if (parameters.getString(jobParameterKey, "").isEmpty()) {
            this.errors.add(String //
                    .format("Job parameter[%s] is not provided.", //
                            jobParameterKey));
        }
    }

}
