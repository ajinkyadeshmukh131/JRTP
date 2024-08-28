package com.ad.service;

import java.util.List;

import com.ad.binding.SearchInputs;
import com.ad.binding.SearchResults;
import com.ad.entity.Donor;

public interface IDonorServiceRepo {
 public String registerNewDonor(Donor donor);
  public List<Donor>getAllDonors();
  public  Donor getDonorById(Long id);
  public String deleteDonorInfo(Long id);
  public String updateDonorInfo(Donor donor);
 public List<SearchResults> getDonorDetailsByInputs(SearchInputs inputs);
  
	
	
	
}
