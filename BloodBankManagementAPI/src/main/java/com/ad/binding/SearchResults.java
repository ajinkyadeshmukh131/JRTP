package com.ad.binding;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchResults {
  
	private Long donorId;
	private String firstName;
	private String lastName;
	private String bloodGroup;
	private String availabilityStatus;
	private Long phoneNumber;
	private String emailId;
	
	
	
	
}
