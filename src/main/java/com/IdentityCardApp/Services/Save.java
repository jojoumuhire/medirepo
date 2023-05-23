package com.IdentityCardApp.Services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.IdentityCardApp.Models.Citizenmodel;
import com.IdentityCardApp.Models.signup;



public interface Save {
	public Citizenmodel savecit(Citizenmodel citm);
	
	public signup createacc(signup acc);
	public Citizenmodel updateUser(Citizenmodel citizenmodel);
	public List<Citizenmodel> getAllCitizen();
	public Page<Citizenmodel> getPaginated(int pageNo,int pageSize);

}
