package com.ad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ad.entity.SchemeCategory;

public interface IFDSchemeCategoryRepo extends JpaRepository<SchemeCategory, Integer> {

}
