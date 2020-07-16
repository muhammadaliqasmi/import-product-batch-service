package com.qasmi.market.importproductbatchservice.job;

import static com.qasmi.market.importproductbatchservice.constants.JobConstants.*;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class implements an end point to start the job.
 * 
 * @author Muhammad Ali Qasmi
 * @since 1.0
 */
@RestController
@RequestMapping("/job")
public class JobController {
    
    private final Job importProductJob;
    
    private final SimpleJobLauncher simpleJobLauncher;
    
    /**
     * Creates instance of job controller.
     * 
     * @param importProductJob Instance of import product job.
     * @param simpleJobLauncher Instance of simple job launcher.
     */
    @Autowired
    public JobController(final Job importProductJob, //
            final SimpleJobLauncher simpleJobLauncher) {
        this.importProductJob = importProductJob;
        this.simpleJobLauncher = simpleJobLauncher;
    }
    
    /**
     * This methods starts the import product job.
     * 
     * @return Response entity with Ok status.
     * @throws Exception that may occur in starting the job.
     */
    @PostMapping("/execute")
    public ResponseEntity<Void> execute() throws Exception {
        final JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        jobParametersBuilder.addLong(JOB_PARAMETER_TIME_KEY, //
                System.currentTimeMillis());
        this.simpleJobLauncher.run(importProductJob, jobParametersBuilder.toJobParameters());
        return ResponseEntity.ok().build();
    }

}
