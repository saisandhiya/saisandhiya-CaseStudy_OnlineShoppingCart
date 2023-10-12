package com.OnlineShoppingCart.Controller;

import org.springframework.beans.factory.annotation.Autowired;




import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.OnlineShopping.ServiceImpl.ProfileServiceImpl;
import com.OnlineShoppingCart.Entity.UserProfile;

import jakarta.validation.Valid;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("/profile")
@CrossOrigin("http://localhost:4200")
public class ProfileController {
	private static Logger logger = LogManager.getLogger();

	@Autowired
	ProfileServiceImpl profileServiceimpl;
	
	

	@PostMapping("/add")
	public ResponseEntity<UserProfile> addNewCustomerProfile(@Valid @RequestBody UserProfile userProfile) {
		logger.info("Sending request to the product");
		UserProfile profile = profileServiceimpl.addNewCustomerProfile(userProfile);
		logger.info("added");
		return new ResponseEntity<>(profile, HttpStatus.CREATED);
	}
	@GetMapping("/getAllProfiles")
	public ResponseEntity<List<UserProfile> >getAllProfiles(){
		logger.info("Sending request to the product");
		List<UserProfile> profile = profileServiceimpl.getAllProfiles();
		logger.info("added");
		return new ResponseEntity<>(profile, HttpStatus.CREATED);
		
	}
	@GetMapping("/getByProfileId/{profileId}")
	public ResponseEntity<UserProfile> getByProfileId( @PathVariable int profileId) {
		logger.info("Sending request to the product");
		System.out.println("ghghsgh");
		UserProfile profile = profileServiceimpl.getByProfileId(profileId);
		logger.info("added");
		return new ResponseEntity<>(profile, HttpStatus.CREATED);
			
	}
	@DeleteMapping("/deleteProfile/{profileId}")
 public ResponseEntity<String> deleteProfile(@PathVariable int profileId) {
		logger.info("Sending request to the product");
		
		 String deleteProfile = profileServiceimpl.deleteProfile(profileId);
		logger.info("added");
		return new ResponseEntity<>(deleteProfile, HttpStatus.CREATED);
		
	}
	@PutMapping("/updateProfile/{profileId}")
	public ResponseEntity<UserProfile> updateProfile(@Valid @PathVariable int profileId,@RequestBody UserProfile profile) {
		logger.info("Sending request to the product");
		
		
		 UserProfile profile2 = profileServiceimpl.updateProfile(profileId, profile);
		logger.info("added");
		return new ResponseEntity<>(profile2, HttpStatus.CREATED);
		
	}
	
}
