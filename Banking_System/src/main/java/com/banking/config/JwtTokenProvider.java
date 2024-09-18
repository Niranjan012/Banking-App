package com.banking.config;

import java.util.Date;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {

	@Value("${app.jwt.secret}")
	private String jwtSecret;

	@Value("${app.jwt.expiration}")
	private long jwtExpirationInMs;

	// Convert secret key string to SecretKey
	private SecretKey getSigningKey() {
		return new SecretKeySpec(jwtSecret.getBytes(), SignatureAlgorithm.HS512.getJcaName());
	}

	// Generate JWT token
	public String generateToken(String username) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

		JwtBuilder builder = Jwts.builder().setSubject(username).setIssuedAt(now).setExpiration(expiryDate)
				.signWith(getSigningKey(), SignatureAlgorithm.HS512);

		return builder.compact();
	}

	// Get username from JWT token
	public String getUsernameFromToken(String token) {
		Claims claims = Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
		return claims.getSubject();
	}

	// Validate JWT token
	public boolean validateToken(String authToken) {
		try {
			Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(authToken);
			return true;
		} catch (Exception ex) {
			// Handle various token validation exceptions
			return false;
		}
	}
}