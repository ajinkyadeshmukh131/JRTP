package com.ad.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ad.entity.TrainDetails;

public interface ITrainDetailsRepository extends JpaRepository<TrainDetails, Integer> {

	@Query("select distinct (category) from TrainDetails")
	public Set<String> getUniqueTrainCategories();
	
	@Query("select distinct (sourceStation) from TrainDetails")
	public Set<String> getUniqueSourceStation();
	
	@Query("select distinct (destinationStation) from TrainDetails")
	public Set<String> getUniqueDestinationStation();
	
	@Query("select distinct (activeDays) from TrainDetails")
	public Set<String> getUniqueActiveDays();
	

	

}
