package com.Library.Management.Models.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.Library.Management.Model.Response.userResponce;

public class UserRowMapper implements RowMapper<userResponce>{
	
	@Override
	public userResponce mapRow(ResultSet rs, int rowNum)throws SQLException{
		userResponce user=new userResponce();
		
		user.setEmail(rs.getString("email"));
		user.setPassword(rs.getString("password_hash"));
		
		return user;
	}
	
}
