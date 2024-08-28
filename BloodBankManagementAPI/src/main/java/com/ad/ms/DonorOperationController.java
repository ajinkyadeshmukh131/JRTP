package com.ad.ms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ad.binding.SearchInputs;
import com.ad.binding.SearchResults;
import com.ad.entity.Donor;
import com.ad.service.IDonorServiceRepo;

@RestController
@RequestMapping("/donor-api")
public class DonorOperationController {
	
	@Autowired
	private IDonorServiceRepo repo;
  
	@PostMapping("/save")
	public ResponseEntity<String> registerNewDonor(@RequestBody Donor donor){
		try {
			String msg=repo.registerNewDonor(donor);
			return new ResponseEntity<String>(msg, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllDonors(){
		try {
			List<Donor> list =repo.getAllDonors();
			return new ResponseEntity<List<Donor>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<?> getDonorById(@PathVariable Long id){
		try {
			Donor msg=repo.getDonorById(id);
			return new ResponseEntity<Donor>(msg, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		try {
			 String msg= repo.deleteDonorInfo(id);
			 return new ResponseEntity<String>(msg, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateDonorInfo(Donor donor){
		try {
			String msg=repo.updateDonorInfo(donor);
			return new ResponseEntity<String>(msg, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/search")
	public ResponseEntity<?> searchDonorByInputs(@RequestBody SearchInputs inputs){
		try {
			List<SearchResults> list=repo.getDonorDetailsByInputs(inputs);
			return new ResponseEntity<List<SearchResults>>(list, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
