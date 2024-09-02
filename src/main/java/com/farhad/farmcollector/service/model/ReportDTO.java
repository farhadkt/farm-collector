package com.farhad.farmcollector.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ReportDTO {
    private String farmName;
    private String cropType;
    private String season;
    private double expectedProductAmount;
    private double actualHarvestedAmount;
}
