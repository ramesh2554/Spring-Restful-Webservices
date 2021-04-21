package com.cognizant.springlearn.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class AuthenticationController {
	@GetMapping("/authenticate")
	public Map<String, String> authenticate(
			@RequestHeader(value = "Authorization", required = false) String authHeader) {
		System.out.println("Authorization details recieved");
		log.info("Start");
		Map<String, String> map = new HashMap<String, String>();
		String generateJwt = generateJwt("ramesh");
		map.put(generateJwt, "");
		log.info("End");
		
		return map;

	}

	private String generateJwt(String user) {
		JwtBuilder builder = Jwts.builder();
		builder.setSubject(user);

		builder.setIssuedAt(new Date());

		builder.setExpiration(new Date((new Date()).getTime() + 1200000));
		builder.signWith(SignatureAlgorithm.HS256, "secretkey");

		String token = builder.compact();

		return token;

	}

}
