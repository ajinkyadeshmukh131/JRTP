package com.ad.ms;

import java.util.List;
import java.util.Map;

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

import com.ad.entity.FixedDepositScheme;
import com.ad.service.IFDSchemeServiceRepo;



@RestController
@RequestMapping("/fdscheme/api")
public class FDSchemeOperationsController {
	@Autowired
private IFDSchemeServiceRepo servicerepo;
	
	@GetMapping("/categories")
	public ResponseEntity<?> showFDSchemeCategories(){
		try {
			Map<Integer,String> mapCategories=servicerepo.getFDSchemeCategory();
			return new ResponseEntity<Map<Integer,String>>(mapCategories, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/register")
	
	public ResponseEntity<String>registerFDScheme(@RequestBody FixedDepositScheme fds){
		try {
			String msg=servicerepo.registerNewFDScheme(fds);
			return new ResponseEntity<String>(msg, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/all")
	public ResponseEntity<?>getAllFDSchemes(){
		try {
			List<FixedDepositScheme>list=servicerepo.showAllFDSchemes();
			return new ResponseEntity<List<FixedDepositScheme>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/find/{schemeId}")
	public ResponseEntity<?>getFDSchemeById(@PathVariable Integer schemeId){
		try {
			FixedDepositScheme fds=servicerepo.showFDSchemeById(schemeId);
			return new ResponseEntity<FixedDepositScheme>(fds, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<?>updateScheme(@RequestBody FixedDepositScheme fds){
		try {
			String msg=servicerepo.upadteFDSchemeDetails(fds);
			return new ResponseEntity<String>(msg, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?>removeFDscheme(@PathVariable Integer schemeId){
		try {
			String msg=servicerepo.deleteFDScheme(schemeId);
			return new ResponseEntity<String>(msg, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PutMapping("/status-change/{schemeId}/{status}")
	public ResponseEntity<?> changeSchemeStatus(@PathVariable Integer schemeId, @PathVariable String status ){
		try {
			String msg=servicerepo.changeFDSchemeStatus(schemeId, status);
			return new ResponseEntity<String>(msg, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	
	
	
	
}
