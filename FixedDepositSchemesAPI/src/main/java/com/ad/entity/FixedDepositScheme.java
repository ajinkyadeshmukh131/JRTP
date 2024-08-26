package com.ad.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="FD_SCHEMES")
@Data
public class FixedDepositScheme {
	@Id
	@Column(name="SCHEME_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
private Integer schemeId;
	@Column(name="SCHEME_NAME",length = 60)
	private String schemeName;
	@Column(name="SCHEME_DESCRIPTION",length = 80)
	private String schemeDescription;
	@Column(name="SCHEME_MIN_INVESTMENT")
	private Integer schemeMinInvestment;
	@Column(name="ACTIVE_SW",length = 20)
	private String active_SW="active";
	@Column(name="CATEGORY_ID")
	private Integer categoryId;
	@Column(name="CREATED_DATE",insertable = true, updatable = false)
	private LocalDateTime created_Date;
	@Column(name="UPDATED_DATE",insertable = false, updatable = true)
	private LocalDateTime updated_Date;
	@Column(name="CREATED_BY",length = 50)
	private String created_By;
	@Column(name="UPDATED_BY",length = 50)
	private String updated_By;
}
