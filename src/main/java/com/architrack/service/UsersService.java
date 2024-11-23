package com.architrack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.architrack.entities.Users;
import com.architrack.repository.UsersRepository;

import jakarta.transaction.Transactional;

@Service
public class UsersService {

	@Autowired
	private UsersRepository repository;
	@Transactional
	public Users findByUsername(String username) {
		
		Users user =  repository.findByUsername(username);
		
		if(user == null) {
			
			throw new UsernameNotFoundException("Username "+username+" not found!");
		}
		
		else {
			
			return user;
		}
	}
}
