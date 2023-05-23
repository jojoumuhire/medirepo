package com.IdentityCardApp.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.IdentityCardApp.Models.Citizenmodel;
import com.IdentityCardApp.Models.signup;
import com.IdentityCardApp.Repositorys.CitizenRepository;
import com.IdentityCardApp.Repositorys.Signuprepo;

@Service
public class Services implements Save  {

	@Autowired
	private CitizenRepository repos;
	
	@Autowired
	private Signuprepo reposig;
	@Override
	public Citizenmodel savecit(Citizenmodel citm) {
		return repos.save(citm);
	}
	@Override
	public signup createacc(signup acc) {
		return reposig.save(acc);
	}
	@Override
	public List<Citizenmodel> getAllCitizen() {
		return repos.findAll();
	}
	@Override
	public Page<Citizenmodel> getPaginated(int pageNo, int pageSize) {
		PageRequest pageable= PageRequest.of(pageNo-1,pageSize);
		return this.repos.findAll(pageable);
	}
	@Override
	public Citizenmodel updateUser(Citizenmodel citizenmodel) {
		Citizenmodel citizen = repos.findById(citizenmodel.getId()).get();
		citizen.setNames(citizenmodel.getNames());
		citizen.setEmail(citizenmodel.getEmail());
		citizen.setPhone(citizenmodel.getPhone());
		citizen.setGender(citizenmodel.getGender());;
		citizen.setBirthdate(citizenmodel.getBirthdate());
		citizen.setAddress(citizenmodel.getAddress());		
		Citizenmodel updatedUser = repos.save(citizen);
		return updatedUser;
	}
	

}
