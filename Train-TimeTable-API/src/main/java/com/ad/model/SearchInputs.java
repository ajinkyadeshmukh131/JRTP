package com.ad.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchInputs {
	
   private String category;
   private String sourceStation;
   private String destinationStation;
  
}
