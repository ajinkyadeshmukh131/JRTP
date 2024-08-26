package com.ad.service;

import java.util.List;

import com.ad.entity.Donor;

public interface IDonorServiceRepo {
 public String registerNewDonor(Donor donor);
  public List<Donor>getAllDonors();
  public  Donor getDonorById(Long id);
  public String deleteDonorInfo(Long id);
  public String updateDonorInfo(Long id);
  
	
	
	
}
