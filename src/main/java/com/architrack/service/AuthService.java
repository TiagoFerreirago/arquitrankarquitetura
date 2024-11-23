package com.architrack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.architrack.exception.InvalidJwtAuthenticationException;
import com.architrack.jwt.acess.AccountCredentialsVo;
import com.architrack.jwt.acess.TokenVo;
import com.architrack.repository.UsersRepository;
import com.architrack.security.jwt.JwtTokenProvider;

@Service
public class AuthService {

	@Autowired
	private AuthenticationManager manager;
	@Autowired
	private UsersRepository repository;
	@Autowired
	private JwtTokenProvider provider;
	
	
	public ResponseEntity<TokenVo>signin(AccountCredentialsVo data){
	   try {
		var username = data.getUserName();
		var password = data.getPassword();
		
		var user = repository.findByUsername(username);
		
		manager.authenticate(new UsernamePasswordAuthenticationToken(username,password ));
		
		TokenVo token = new TokenVo();
		
		
			if(user != null) {
			
				token = provider.createAcessToken(username, user.getRoles());
			}
			else {
				throw new UsernameNotFoundException("username "+username+" not found!");
			}
			
			return ResponseEntity.ok(token);
		}
		catch(Exception ex) {
			
			throw new InvalidJwtAuthenticationException("invalid authentication token");
		}
	}
	
	@SuppressWarnings("rawtypes")
	public ResponseEntity refreshToken(String username, String refreshToken) {
		
		var user = repository.findByUsername(username);
		
		var token = new TokenVo();
		
		if(user != null) {
			
			token = provider.refreshToken(refreshToken);
		}
		
		else {
			throw new UsernameNotFoundException("username "+username+" not found!");
		}
		
		return ResponseEntity.ok(token);
	}
	
}
