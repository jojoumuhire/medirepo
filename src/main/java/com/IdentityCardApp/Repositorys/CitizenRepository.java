package com.IdentityCardApp.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.IdentityCardApp.Models.Citizenmodel;


public interface CitizenRepository extends JpaRepository<Citizenmodel, Integer>{

	
}
