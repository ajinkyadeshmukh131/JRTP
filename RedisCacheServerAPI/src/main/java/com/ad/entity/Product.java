package com.ad.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="CACHE_PRODUCTS")
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
	
	@Id
  @GeneratedValue(strategy =GenerationType.IDENTITY)
	private Integer pid;
	@Column(length = 30)
	private String pname;
	private Double price;
	private Double qty;
	

}
