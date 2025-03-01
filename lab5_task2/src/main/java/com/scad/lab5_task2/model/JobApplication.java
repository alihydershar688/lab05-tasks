package com.scad.lab5_task2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobApplication {
    private String id;
    private String applicantName;
    private String fileName;
    private LocalDate uploadDate;
}
