package com.study.spring.util;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JWTUtil {
	
	private static String key = "123456789123456789123456789123456789";

	public static String generateToken(Map<String, Object> claims, int i) {
		
		SecretKey key = null;
		
		try {
			key = Keys.hmacShaKeyFor(JWTUtil.key.getBytes("UTF-8"));
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
		String jwtStr = Jwts.builder()
				.setHeader(Map.of("typ","JWT"))
				.setClaims(claims)
				.setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
				.setExpiration(Date.from(ZonedDateTime.now().plusMinutes(i).toInstant()))
				.signWith(key)
				.compact();
		
		return jwtStr;
	}

}
