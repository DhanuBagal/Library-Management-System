package com.Library.Management.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.Library.Management.Model.Response.userResponce;
import com.Library.Management.Models.RowMapper.UserRowMapper;

@Repository
public class UsersDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public void insertUser(String userName, String email, String name, String password, int roleId) {
		String query="INSERT INTO users(user_name, email, name, password_hash, role_id) values (:userName, :email, :name, :password, :roleId)";
		
		MapSqlParameterSource mapSqlParameterSource=new MapSqlParameterSource()
				.addValue("userName", userName)
				.addValue("email", email)
				.addValue("name", name)
				.addValue("password", password)
				.addValue("roleId", roleId);
		
		namedParameterJdbcTemplate.update(query, mapSqlParameterSource);
		
	}

	public userResponce login(String email) {
		String query="Select email, password_hash from users where email=:email";
		
		MapSqlParameterSource mapSqlParameterSource=new MapSqlParameterSource()
				.addValue("email", email);
		
		return namedParameterJdbcTemplate.queryForObject(query,mapSqlParameterSource, new UserRowMapper());
	}

}
