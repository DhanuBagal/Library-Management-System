package com.Library.Management.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Library.Management.Model.Response.userResponce;
import com.Library.Management.Models.Request.AddUserRequest;
import com.Library.Management.Services.UserServices;

@RestController
@RequestMapping("/users")
public class UsersController {
	
	@Autowired
	private UserServices userServices;
	
	@PostMapping("/add")
	public String addUser(@RequestBody AddUserRequest user) {
		userServices.addUser(user);
		return "User added Successfully!";
	}
	
	@PostMapping("/login")
	public Boolean loginUser(@RequestParam String email,@RequestParam String password) {
		return userServices.loginUser(email, password);
	}
}
