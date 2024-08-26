package com.ad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ad.entity.Donor;

public interface IDonorRepository extends JpaRepository<Donor, Long> {

}
