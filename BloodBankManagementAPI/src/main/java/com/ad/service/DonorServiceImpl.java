package com.ad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ad.entity.Donor;
import com.ad.repository.IDonorRepository;

public class DonorServiceImpl implements IDonorServiceRepo {

	@Autowired
	
	private IDonorRepository donorRepo;
	
	@Override
	public String registerNewDonor(Donor donor) {
		 Donor donor1=donorRepo.save(donor);
		 if (donor1.getDonorId()!=null) {
			return "Donor is registered with id::"+donor1.getDonorId();
		}
		return "Problem occured in registering Donor";
	}

	@Override
	public List<Donor> getAllDonors() {
		
		return donorRepo.findAll();
	}

	@Override
	public Donor getDonorById(Long id) {
		
		return donorRepo.findById(id).orElseThrow(()->new IllegalArgumentException("Donor Not Found"));
	}

	@Override
	public String deleteDonorInfo(Long id) {
	
	   donorRepo.deleteById(id);	
	   return "Donor is Deleted having id::"+id;
	}

	@Override
	public String updateDonorInfo(Long id) {
		Donor donor1=donorRepo.getById(id);
		if (donor1!=null) {
			donorRepo.save(donor1);
			return "Donor's info is updated with new details";
		}
		return "Donor's Info is not updated ";
	}
}


