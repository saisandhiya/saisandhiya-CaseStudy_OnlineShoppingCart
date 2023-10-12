package com.naidu.login.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.naidu.login.Repository.ICustomerRepostiory;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtility {
	
	
	@Autowired
	private ICustomerRepostiory customerRepo;

	@Value("${jwtSecret}") // Make sure you have the correct property set in your application.properties or
							// // YAML file.
	private String secretKey;

	public String generateToken(String username) {

		Map<String, Object> claims = new HashMap<>();
		
//		claims.put("id", customerRepo.findByUserName(username).get());
		
		claims.put("cartId",Integer.parseInt(customerRepo.findByUserName(username).get().getMobileNumber().substring(0,5)));

		return createToken(claims, username);

	}

	public String createToken(Map<String, Object> claims, String username) {

		return Jwts.builder()

				.setClaims(claims)

				.setSubject(username)

				.setIssuedAt(new Date(System.currentTimeMillis()))

				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))

				.signWith(getKey(), SignatureAlgorithm.HS256).compact();

	}

	public Key getKey() {

		byte[] keys = Decoders.BASE64.decode(secretKey);

		return Keys.hmacShaKeyFor(keys);

	}

	public String extractUsername(String token) {

		return extractClaim(token, Claims::getSubject);

	}

	public Date extractExpiration(String token) {

		return extractClaim(token, Claims::getExpiration);

	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {

		final Claims claims = extractAllClaims(token);

		return claimsResolver.apply(claims);

	}

	public Claims extractAllClaims(String token) {

		return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();

	}

	public Boolean isTokenExpired(String token) {

		return extractExpiration(token).before(new Date());

	}

	public Boolean validateToken(String token, UserDetails userDetails) {

		final String username = extractUsername(token);

		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));

	}

//	// Generate a token for a given username and claims
//	public String generateToken(String username) {
//		Map<String, Object> claims = new HashMap<>();
//		return createToken(username, claims);
//	}
//
//	// Create a token based on the username and claims
//	private String createToken(String username, Map<String, Object> claims) {
//		Date now = new Date();
//		Date expirationDate = new Date(now.getTime() + 1000 * 60 * 30); // Token valid for 30 minutes
//
//		String token = Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(now)
//				.setExpiration(expirationDate).signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
//
//		// System.out.println("Generated token: " + token); // Print the generated token
//		// for debugging
//
//		return token;
//	}
//
//	// Decode the secret key and create a signing key
//	private Key getSignKey() {
//		byte[] keyBytes = secretKey.getBytes(); // Change to your preferred way of getting bytes from the secret key
//		return Keys.hmacShaKeyFor(keyBytes);
//	}

//	// Extract all claims from the token
//	private Claims extractAllClaims(String token) {
//		return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
//	}
//
//	// Extract a specific claim from the token
//	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//		final Claims claims = extractAllClaims(token);
//		return claimsResolver.apply(claims);
//	}
//
//	// Extract the username from the token
//	public String extractUsername(String token) {
//		return extractClaim(token, Claims::getSubject);
//	}
//
//	// Extract the expiration date from the token
//	public Date extractExpiration(String token) {
//		return extractClaim(token, Claims::getExpiration);
//	}
//
//	// Check if the token is expired
//	private Boolean isTokenExpired(String token) {
//		return extractExpiration(token).before(new Date());
//	}
//
//	// Validate the token
//	public Boolean validateToken(String token, UserDetails userDetails) {
//		final String username = extractUsername(token);
//		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//	}
}
