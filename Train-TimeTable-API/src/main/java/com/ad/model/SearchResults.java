package com.ad.model;

import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchResults {
     private Integer trainNo;
     private String trainName;
     private String category;
     private String activeDays;
     private String trainStatus;
     private String sourceStation;
     private String destinationStation;
     
     private LocalTime sourceStationTime;
     private LocalTime destinationStationTime;
     
}
