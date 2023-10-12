package com.naidu.login.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.naidu.login.Entity.Login;

@Repository
public interface ICustomerRepostiory extends MongoRepository<Login, String> {

	Optional<Login> findByUserName(String userName);

	public Optional<Login> findByEmail(String email);

	
	

}
