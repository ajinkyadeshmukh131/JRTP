package com.ad.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.ad.binding.SearchInputs;
import com.ad.binding.SearchResults;
import com.ad.entity.Donor;
import com.ad.repository.IDonorRepository;

@Service
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
	public String updateDonorInfo(Donor donor) {
		Optional<Donor> opt=donorRepo.findById(donor.getDonorId());
		if (opt.isPresent()) {
	  donorRepo.save(donor);
	  return donor.getDonorId()+"Donor's info is updated with new details";
	}
		return donor.getDonorId()+"Donor's Info is not updated ";
	}

	//SearchInputs method
	@Override
	public List<SearchResults> getDonorDetailsByInputs(SearchInputs inputs) {
		Donor entity=new Donor();
		String bloodGroup=inputs.getBloodGroup();
		if (StringUtils.hasLength(bloodGroup)) 
			entity.setBloodGroup(bloodGroup);
		
		String availabilityStatus=inputs.getAvailabiltyStatus();
		if (StringUtils.hasLength(availabilityStatus))
		entity.setAvailabilityStatus(availabilityStatus);
		
		Example<Donor> example=Example.of(entity);
		List<Donor> listEntity=donorRepo.findAll(example);
		List<SearchResults> listResults=new ArrayList();
		listEntity.forEach(donor->{
			SearchResults result=new SearchResults();
			BeanUtils.copyProperties(donor,result);
			listResults.add(result);
		});
		return listResults;
	}
}
		
