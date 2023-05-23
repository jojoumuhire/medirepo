package com.IdentityCardApp.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.IdentityCardApp.Models.signup;


public interface Signuprepo  extends JpaRepository<signup, Integer>{
	signup findByEmail(String email);

}
