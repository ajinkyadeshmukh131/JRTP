package com.ad.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchInputs {
	private Integer trainNo;
   private String trainCategory;
   private String activeDays;
   private String sourceStation;
   private String destinationStation;
  
}
