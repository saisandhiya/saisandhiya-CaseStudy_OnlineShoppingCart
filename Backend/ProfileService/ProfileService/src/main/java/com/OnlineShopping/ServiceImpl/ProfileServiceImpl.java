package com.OnlineShopping.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OnlineShopping.Exceptions.ProfileAlreadyExistsException;
import com.OnlineShopping.Exceptions.ProfileNotFoundException;
import com.OnlineShopping.Service.ProfileService;
import com.OnlineShoppingCart.Entity.UserProfile;
import com.OnlineShoppingCart.Repository.ProfileRepository;

@Service
public class ProfileServiceImpl implements ProfileService {
	@Autowired
	ProfileRepository profileRepo;

	@Override
	public List<UserProfile> getAllProfiles() throws ProfileNotFoundException {
		List<UserProfile> profile = profileRepo.findAll();
		if (!profile.isEmpty()) {
			return profileRepo.findAll();
		} else {
			throw new ProfileNotFoundException("No data found");
		}

	}

	@Override
	public UserProfile addNewCustomerProfile(UserProfile userProfile) throws ProfileAlreadyExistsException {
		// Optional<UserProfile> add = profileRepo.findById(userProfile.getProfileId());
		Optional<UserProfile> add = profileRepo.findById(userProfile.getProfileId());

		if (!add.isPresent()) {
			return profileRepo.insert(userProfile);
		} else {

			throw new ProfileAlreadyExistsException("Profile Already Exists");
		}

	}
	@Override
	public UserProfile updateProfile(int profileId, UserProfile userProfile) {
	    Optional<UserProfile> os = profileRepo.findById(profileId);

	    if (os.isPresent()) {
	        UserProfile existingProfile = os.get();
	        
	        // Update the fields with values from the userProfile parameter
	        existingProfile.setFullName(userProfile.getFullName());
	        existingProfile.setEmailId(userProfile.getEmailId());
	        existingProfile.setAddress(userProfile.getAddress());
	        existingProfile.setGender(userProfile.getGender());
	        existingProfile.setDateOfBirth(userProfile.getDateOfBirth());
	        existingProfile.setMobileNumber(userProfile.getMobileNumber());
	        
	        // Save the updated profile using the save method
	        UserProfile updatedProfile = profileRepo.save(existingProfile);
	        
	        return updatedProfile;
	    }
	    
	    throw new ProfileNotFoundException("Profile Not found");
	}


		

	@Override
	
		public String deleteProfile(int profileId) throws ProfileNotFoundException {
		
			Optional<UserProfile> findDeleteById = profileRepo.findById(profileId);
			if (findDeleteById.isPresent()) {
			
				profileRepo.deleteById(profileId);
				return "deleted successfully";
			

			} else {
				
				throw new ProfileNotFoundException("User Profile Does not Exists");
				
			}


	}

	@Override
	public UserProfile getByProfileId(int profileId)throws ProfileNotFoundException {
		Optional<UserProfile> ps = profileRepo.findById(profileId);
		if(ps.isPresent()) {
			return ps.get();
		}
		
		 else {

			throw new ProfileNotFoundException(profileId + "User Does not Exists");
		}

	}
}
