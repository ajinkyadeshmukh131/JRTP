package com.ad.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ad.entity.Product;
import com.ad.repository.IProductRepo;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IProductRepo repo;
	 
	
	@Override
	@CachePut(value="prods",key="#prod.pid")
	public Product addProduct(Product prod) {
		repo.save(prod);
		return prod;
	}

	@Override
	@Cacheable(value="prods")
	public List<Product> showAllProducts() {
		
		return repo.findAll();
	}

	@Override
	@CacheEvict(value="prods",key="#pid")
	public String deleteProduct(int pid) {
		Optional<Product> opt=repo.findById(pid);
		if (opt.isPresent()) {
			repo.deleteById(pid);
			return "Product is Deleted";
		}
		return "Product is not found and deleted";
	}

	@Override
@Cacheable(value="prods",key="#pid")
	public Product findProductById(int pid) {
		return repo.findById(pid).orElseThrow(()->new IllegalArgumentException("Invalid PID"));
	}

}
