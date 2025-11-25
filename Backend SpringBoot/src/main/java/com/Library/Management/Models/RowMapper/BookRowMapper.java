package com.Library.Management.Models.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.Library.Management.Model.Response.BookResponse;

public class BookRowMapper implements RowMapper<BookResponse>{
	
	@Override
    public BookResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        BookResponse book = new BookResponse();
        book.setBookId(rs.getInt("book_id"));
        book.setBookName(rs.getString("book_name"));
        book.setDescription(rs.getString("description"));
        book.setBookType(rs.getString("book_type"));
        String author = rs.getString("auth_name");
        book.setAuthors(author != null ? new ArrayList<>(List.of(author)) : new ArrayList<>());
        return book;
    }

}
