package com.ad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ad.entity.FixedDepositScheme;

public interface IFDSchemeRepo extends JpaRepository<FixedDepositScheme, Integer> {

}
