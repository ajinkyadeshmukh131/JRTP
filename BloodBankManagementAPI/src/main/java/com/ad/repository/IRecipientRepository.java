package com.ad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ad.entity.Recipient;

public interface IRecipientRepository extends JpaRepository<Recipient, Long> {

}
