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

import com.ad.entity.Recipient;
import com.ad.service.IRecipientServiceRepo;

@RestController
@RequestMapping("/recipient-api")
public class RecipientOperationController {

	@Autowired
	private IRecipientServiceRepo repo;
	
	@PostMapping("/save")
	public ResponseEntity<String> registerNewRecipient(@RequestBody Recipient reci){
		try {
			String msg=repo.registerNewRecipient(reci);
			return new ResponseEntity<String>(msg, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllRecipients(){
		try {
	          List<Recipient> list=repo.getAllRcipients();
			return new ResponseEntity<List<Recipient>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<?> findRecipientById(@PathVariable Long id){
		try {
			Recipient recip=repo.getRecipientById(id);
			return new ResponseEntity<Recipient>(recip, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		try {
			String msg=repo.deleteRecipientInfo(id);
			return new ResponseEntity<String>(msg, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateRecipient(Recipient reci){
		try {
			String msg=repo.updateRecipientInfo(reci);
			return new ResponseEntity<Recipient>(reci, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
