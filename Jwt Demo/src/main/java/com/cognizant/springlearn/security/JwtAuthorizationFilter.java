package com.cognizant.springlearn.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

	public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
		log.info("Start");
		log.debug("{}: ", authenticationManager);

	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.info("Start");
		String header = request.getHeader("Authorization");
		log.debug(header);
		if (header == null || !header.startsWith("Bearer ")) {
			chain.doFilter(request, response);
			return;
		}
		UsernamePasswordAuthenticationToken authentication = getAuthentication(request);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
		log.info("End");
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) { 
		 String token = request.getHeader("Authorization"); 
		if (token != null) { 
		Jws<Claims> jws; 
		try { 
		jws = Jwts.parser() 
		.setSigningKey("secretkey") 
		.parseClaimsJws(token.replace("Bearer ", "")); 
		String user = jws.getBody().getSubject(); 
		log.debug(user); 
		if (user != null) { 
		return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
		} 
		} 
		catch (JwtException ex) { 
		 return null; 
		}
		return null; 
		}
		return null;
		 
		}

}
