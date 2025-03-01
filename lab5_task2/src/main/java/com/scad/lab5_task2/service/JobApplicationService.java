package com.scad.lab5_task2.service;


import com.scad.lab5_task2.model.JobApplication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class JobApplicationService {
    private final List<JobApplication> applications = new ArrayList<>();
    private static final String UPLOAD_DIR = "C:\\Users\\PMLS\\OneDrive - Higher Education Commission\\Abdullah University Data\\6th Semester__\\Software Construction\\Lab\\Lab5\\lab5_task2\\lab5_task2\\uploads";

    public JobApplication uploadApplication(String applicantName, MultipartFile file) throws IOException {
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists() && !uploadDir.mkdirs()) {
            throw new IOException("Failed to create upload directory");
        }

        String uniqueFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        File destFile = new File(uploadDir, uniqueFileName);
        file.transferTo(destFile);

        JobApplication application = new JobApplication(UUID.randomUUID().toString(), applicantName, uniqueFileName, LocalDate.now());
        applications.add(application);
        return application;
    }

    public List<JobApplication> getAllApplications() {
        return applications;
    }

    public boolean deleteApplication(String id) {
        JobApplication application = applications.stream().filter(app -> app.getId().equals(id)).findFirst().orElse(null);
        if (application != null) {
            File file = new File(UPLOAD_DIR, application.getFileName());
            if (file.exists()) {
                file.delete();
            }
            applications.remove(application);
            return true;
        }
        return false;
    }
}

