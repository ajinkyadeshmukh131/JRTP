package com.ad.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wish-api")
public class WishMessageOperationsController {

	@GetMapping("/display")
	public ResponseEntity<String> showWishMesaage(){
		return new ResponseEntity<String>("Good Morning from Deshmukh Techno", HttpStatus.OK);
	}
	
}
