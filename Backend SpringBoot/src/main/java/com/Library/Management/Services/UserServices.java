package com.Library.Management.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Library.Management.DAO.UsersDAO;
import com.Library.Management.Model.Response.userResponce;
import com.Library.Management.Models.Request.AddUserRequest;

@Service
public class UserServices {
	
	@Autowired
	private UsersDAO userDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public void addUser(AddUserRequest user) {
		
		if(user.getName()==null || user.getName().trim().isBlank()) {
			throw new IllegalArgumentException("Name of a user cannot be empty !");
		}
		if (user.getUserName()==null || user.getUserName().trim().isBlank()) {
			throw new IllegalArgumentException("Username cannot be empty !");
		}
		
		userDAO.insertUser(user.getUserName(), user.getEmail(), user.getName(), passwordEncoder.encode(user.getPassword()),user.getRoleId());
		
	}

	public Boolean loginUser(String email,String password) {
		if (email==null || email.trim().isBlank()) {
			throw new IllegalArgumentException("Email cannot be empty !");
		}if (password==null || password.trim().isBlank()) {
			throw new IllegalArgumentException("Password cannot be empty !");
		}
		
		userResponce responce=userDAO.login(email);
		
		if(responce != null) {
			boolean matches = passwordEncoder.matches(password, responce.getPassword());
			
			if(matches) {
				return true;
			}
			throw new RuntimeException("Invalid password");
		}
		
		 throw new RuntimeException("User not found");
	}

}
