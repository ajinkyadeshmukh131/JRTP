package com.ad.entity;

import java.time.LocalDate;
import java.time.LocalTime;
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
@Table(name="TRAIN_TIMETABLE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainDetails {
	@Id
	@GeneratedValue
  private Integer SrNo;
  private Integer trainNo;
	@Column(length = 60)
	private String trainName;
	@Column(length = 60)
	private String category;
	@Column(length = 60)
	private String sourceStation;
	@Column(length = 60)
	private String destinationStation;
	private LocalTime sourceStationTime;
	private LocalTime destinationStationTime;
	@Column(length = 60)
	private String activeDays;
	@Column(length = 60)
	private String trainStatus;
	@CreationTimestamp
	@Column(insertable = true, updatable = false)
	private LocalDate recordCreationDate;
	@UpdateTimestamp
	@Column(insertable = false, updatable = true)
	private LocalDate recordUpdationDate;
	@Column(length = 60)
	private String createdBy;
	@Column(length = 60)
	private String updatedBy;
	
}
