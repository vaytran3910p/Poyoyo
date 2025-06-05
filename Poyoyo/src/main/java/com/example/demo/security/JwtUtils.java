package com.example.demo.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import com.example.demo.controller.CategoryController;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;


@Component
public class JwtUtils {

    private final CategoryController categoryController;
    
    @Value("${jwt.secret}")
	private static String SECRET_KEY;
	
	private static final long EXPIREATION_TIME = 1000 * 60 * 60 * 10;

    JwtUtils(CategoryController categoryController) {
        this.categoryController = categoryController;
    }
	
	// generating JWT
    public String generateToken (UserDetails userDetail) {
    	Map<String, Object> claims = new HashMap<String, Object>();
    	return createToken(claims, userDetail.getUsername());
    }
	public String createToken(Map<String, Object> claims,String subject) { // => use in AuthController to return a token to user
		String token = Jwts.builder()
							.setClaims(claims)
							.setSubject(subject)
							.setIssuedAt(new Date())
							.setExpiration(new Date((new Date().getTime() + EXPIREATION_TIME)))
							.signWith(getSignKey(), SignatureAlgorithm.HS256)
							.compact();
		return token;
	}
	
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getSignKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	public String extractUerName(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	public Date extractexpireation(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	public boolean isTokenExpired(String token) {
		return extractexpireation(token).before(new Date());
	}
	
	public boolean validateJwtToken(String token, UserDetails userDetail) { 
		final String username = extractUerName(token);
		return (username.equals(userDetail.getUsername()) && !isTokenExpired(token));
	}

	private Key getSignKey() {
		return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	}
	// --------------------------
	
	
	
}
