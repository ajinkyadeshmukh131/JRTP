package com.ad.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ad.entity.Product;
import com.ad.service.IProductService;

@RestController
@RequestMapping("/product-api")
public class ProductOperationsController {

	@Autowired
	private IProductService repo;
	
	@PostMapping("/save")
	public ResponseEntity<String> saveProduct(@RequestBody Product prod){
		//use service
		Product saveProduct=repo.addProduct(prod);
		return new ResponseEntity<String>(saveProduct.toString(), HttpStatus.CREATED);
	}
	
  @	GetMapping("/all")
	public ResponseEntity<List<Product>> showAllProducts(){
		List<Product> list=repo.showAllProducts();
		return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
	}
  
  @GetMapping("/find/{pid}")
  public ResponseEntity<Product> productById(@PathVariable Integer pid){
	  Product prod=repo.findProductById(pid);
	  return new ResponseEntity<Product>(prod, HttpStatus.OK);
  }
	
}
