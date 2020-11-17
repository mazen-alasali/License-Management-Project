package com.datagear.goaml.license.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.datagear.goaml.license.model.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {

	public Bank findByName(String name);

	
}
