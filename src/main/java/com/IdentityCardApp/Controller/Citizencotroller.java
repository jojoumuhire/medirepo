package com.IdentityCardApp.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.IdentityCardApp.Models.Citizenmodel;
import com.IdentityCardApp.Models.signup;
import com.IdentityCardApp.Repositorys.CitizenRepository;
import com.IdentityCardApp.Repositorys.Signuprepo;
import com.IdentityCardApp.Services.Save;




@Controller
public class Citizencotroller {
	@Autowired
	private Save Services;
	@Autowired
	private CitizenRepository repos;
	
	@Autowired
	private Signuprepo reposig;
	
	@RequestMapping("/")
    private  String homePage(){
        return "Login";
    }
	
	@RequestMapping("/viewregistered")
    private  String homeRegistered(){
        return "CitizForm";
    }
	
	@RequestMapping("/sigup")
    private  String homeCreateAccout(){
        return "Signup";
    }
	
	
@RequestMapping("/Save")
public ModelAndView createcit(@RequestParam("names") String names, @RequestParam("gender") String gender, @RequestParam("phone") String phone, @RequestParam("birthdate") String birthdate, @RequestParam("address") String address,  @RequestParam("email") String email)
{
Citizenmodel cit =new Citizenmodel();

cit.setNames(names);
cit.setGender(gender);
cit.setBirthdate(birthdate);
cit.setPhone(phone);
cit.setAddress(address);
cit.setEmail(email);
Services.savecit(cit);
ModelAndView mav=new ModelAndView();
mav.setViewName("CitizForm");
mav.addObject("success","Registered Success");
return mav;
}

@RequestMapping("/Acc")
public String sigacc(@RequestParam("fname") String fullnames, @RequestParam("email") String email, @RequestParam("pass") String password){
signup cit =new signup();
cit.setNames(fullnames);
cit.setEmail(email);
cit.setPassword(password);
Services.createacc(cit);
return "Login";
}

@RequestMapping("/Log")
public String loged(@RequestParam("email") String email, @RequestParam("password") String password){

	String emails="admin@gmail.com";
	String passwords="kigali";
	signup user = null;	
	try {
		user = reposig.findByEmail(email);	
				
	}catch (Exception e) {
		System.out.println(e);
	}
	if(user!=null &&(user.getEmail().equals(email)&& user.getPassword().equals(password))) {
		return "CitizForm";
	}else if(emails.equals(emails) && passwords.equals(passwords)) {
		return "redirect:/view";
		}else{
			return "Login";
		}

}

@RequestMapping("/view")
public  ModelAndView homeafter(){    
	return PageShow(1);
}

@GetMapping("/page/{pageNo}")
public  ModelAndView PageShow(@PathVariable (value = "pageNo") int pageNo){
    ModelAndView mav=new ModelAndView();
    int pageSize=5;
    Page<Citizenmodel> page=Services.getPaginated(pageNo,pageSize);
    List<Citizenmodel> allusersdata=page.getContent();
    mav.setViewName("view");
    mav.addObject("currentPage",pageNo);
    mav.addObject("totalPages",page.getTotalPages());
    mav.addObject("totalItems",page.getTotalElements());
    mav.addObject("displayAll",allusersdata);
    return  mav;
}

@GetMapping("/delete/{id}")
public String deleteUser(@PathVariable("id") Integer id) {
    Citizenmodel user = repos.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    repos.delete(user);
    return "redirect:/view";
}

@PostMapping("/update/{id}")
public String updateUser(@PathVariable("id") String id,Citizenmodel user) {
	Citizenmodel userAttributes=new Citizenmodel();
    userAttributes.setId(user.getId());
    userAttributes.setNames(user.getNames());
    userAttributes.setEmail(user.getEmail());
    userAttributes.setPhone(user.getPhone());
    userAttributes.setAddress(user.getAddress());
    userAttributes.setBirthdate(user.getBirthdate());
    userAttributes.setGender(user.getGender());
    Services.updateUser(userAttributes);
    return "redirect:/view";
}

@GetMapping("/edit/{id}")
public ModelAndView showUpdateForm(@PathVariable("id") Integer id) {
    Optional<Citizenmodel> citize = repos.findById(id);
    ModelAndView mav = new ModelAndView();
    mav.setViewName("updatecitizen");
    mav.addObject("allusers",citize);

    return mav;
}


}