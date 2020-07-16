package com.qasmi.market.importproductbatchservice.job;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class implements an endpoint to start the job.
 * 
 * @author Muhammad Ali Qasmi
 * @since 1.0
 */
@RestController
@RequestMapping("/job")
public class JobController {
    
    @PutMapping("/execute")
    public ResponseEntity<Void> execute() {
        return ResponseEntity.ok().build();
    }

}
