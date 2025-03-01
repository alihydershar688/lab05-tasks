package com.scad.lab5_task1.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceBooking {
    private String id;
    private String name;
    private String model;
    private String regNum;
    private String serviceType;
    private String preferedDate;
}
