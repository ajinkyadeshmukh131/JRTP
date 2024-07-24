package com.ad.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="SCHEME_CATEGORY")
@Data
public class SchemeCategory {
	@Id
	@Column(name="CATEGORY_ID")
	@SequenceGenerator(name="gen1",sequenceName = "category_seq",initialValue = 1,allocationSize = 1)
	@GeneratedValue(generator ="gen1",strategy=GenerationType.SEQUENCE)
private Integer categoryId;
	@Column(name="CATEGORY_NAME",length = 50)
	private String categoryName;
	@Column(name="ACTIVE_SW",length = 20)
	private String active_SW="active";
	@Column(name="CREATED_DATE",insertable = true, updatable = false)
	private LocalDateTime created_Date;
	@Column(name="UPDATED_DATE",insertable = false, updatable = true)
	private LocalDateTime updated_Date;
	@Column(name="CREATED_BY",length = 50)
	private String created_By;
	@Column(name="UPDATED_BY",length = 50)
	private String updated_By;
	
}
