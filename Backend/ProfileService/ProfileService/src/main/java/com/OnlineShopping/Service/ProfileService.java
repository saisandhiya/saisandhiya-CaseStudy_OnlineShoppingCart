package com.OnlineShopping.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.OnlineShoppingCart.Entity.UserProfile;

@Service
public interface ProfileService {
	public List<UserProfile> getAllProfiles();

	public UserProfile getByProfileId(int profileId);  

	public UserProfile addNewCustomerProfile(UserProfile userProfile);  

	public UserProfile updateProfile(int profileId,UserProfile userProfile); 

	public String deleteProfile(int profileId);
	

}
	


