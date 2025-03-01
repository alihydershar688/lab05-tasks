package com.scad.lab5_task2.controller;

import com.scad.lab5_task2.model.JobApplication;
import com.scad.lab5_task2.service.JobApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

    @RestController
    @RequestMapping("/applications")
    public class JobApplicationController {
        private final JobApplicationService service;

        public JobApplicationController(JobApplicationService service) {
            this.service = service;
        }

        @PostMapping
        public ResponseEntity<?> uploadApplication(@RequestParam("file") MultipartFile file, @RequestParam("applicantName") String applicantName) {
            try {
                JobApplication application = service.uploadApplication(applicantName, file);
                return ResponseEntity.ok(application);
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed: " + e.getMessage());
            }
        }

        @GetMapping
        public List<JobApplication> listApplications() {
            return service.getAllApplications();
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteApplication(@PathVariable String id) {
            if (service.deleteApplication(id)) {
                return ResponseEntity.ok("Application deleted successfully.");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Application not found.");
        }
    }


