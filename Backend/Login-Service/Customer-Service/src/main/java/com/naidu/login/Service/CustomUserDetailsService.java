package com.naidu.login.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naidu.login.Entity.Login;
import com.naidu.login.Repository.ICustomerRepostiory;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	ICustomerRepostiory irepo;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Login> userDetail = irepo.findByUserName(username);

		// Converting userDetail to UserDetails

		return userDetail.map(CustomUserDetails::new)

				.orElseThrow(() -> new UsernameNotFoundException("User not found " + username));

	}
}
