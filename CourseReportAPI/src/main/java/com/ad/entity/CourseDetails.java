package com.ad.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="COURSE_DETAILS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDetails {
	@Id
	@GeneratedValue
  private Integer courseId;
	@Column(length = 60)
	private String courseName;
	@Column(length = 60)
	private String location;
	@Column(length = 60)
	private String courseCategory;
	@Column(length = 60)
	private String facultyName;
	private Double fee;
	@Column(length = 60)
	private String adminName;
	private Long adminContact;
	@Column(length = 60)
	private String trainingMode;
	private LocalDateTime startDate;
	@Column(length = 60)
	private String courseStatus;
	
	@CreationTimestamp
	@Column(insertable = true, updatable = false)
	private LocalDateTime creationDate;
	@UpdateTimestamp
	@Column(insertable = false, updatable = true)
	private LocalDateTime updationDate;
	@Column(length = 60)
	private String createdBy;
	@Column(length = 60)
	private String updatedBy;
}
