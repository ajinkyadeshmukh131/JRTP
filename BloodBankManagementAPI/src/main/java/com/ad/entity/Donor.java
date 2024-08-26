package com.ad.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="DONORS_INFO")
public class Donor {
	@Id
	@Column(name="DONOR_ID", length = 10)
	@GeneratedValue(generator ="gen1",strategy=GenerationType.SEQUENCE)
	private Long donorId;
	@Column(name="FIRST_NAME",length = 20)
	private String firstName;
	@Column(name="LAST_NAME",length=30)
	private String lastName;
	@Column(name="EMAIL_ID", length = 60)
	private String emailId;
	@Column(name="PHONE_NUMBER",length = 10)
	private Long phoneNumber;
	
	
}
