package com.OnlineShoppingCart.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.OnlineShopping.Service.ProfileService;
import com.OnlineShoppingCart.Entity.UserProfile;

@Repository
public interface ProfileRepository extends MongoRepository<UserProfile,Integer> {
   //Optional<UserProfile> findByProfileId(int profileId);
   
}
