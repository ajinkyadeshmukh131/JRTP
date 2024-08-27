package com.ad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ad.entity.Product;

public interface IProductRepo extends JpaRepository<Product, Integer> {

}
