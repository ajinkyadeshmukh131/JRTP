package com.ad.service;

import java.util.List;

import com.ad.entity.Product;

public interface IProductService {
   public Product addProduct(Product prod);
   public List<Product> showAllProducts();
   public String deleteProduct(int pid);
   public Product findProductById(int pid);
	
	
}
