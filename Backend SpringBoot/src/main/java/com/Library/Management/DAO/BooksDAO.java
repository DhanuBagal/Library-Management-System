package com.Library.Management.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.Library.Management.Model.Response.BookResponse;
import com.Library.Management.Models.RowMapper.BookRowMapper;
//import org.springframework.transaction.annotation.Transactional;

@Repository
public class BooksDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public List<BookResponse> findAllBooksWithAuthors(){
		
		String query="SELECT b.book_id, b.book_name, b.description, b.book_type, a.auth_name "
				+ "FROM books b "
				+ "LEFT JOIN book_auth ba ON b.book_id = ba.book_id "
				+ "LEFT JOIN authors a ON ba.auth_id = a.auth_id";
		
		return namedParameterJdbcTemplate.query(query,new BookRowMapper());
	}

//	@Transactional
//	public void insertBooks(String bookName, String bookType, String description, String[] authors) {
////		String insertBookQuery = "insert into books (book_name,description,book_type) values(?,?,?)";
////
////		jdbcTemplate.update(insertBookQuery, bookName, description, bookType);
////
////		int bookId = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
//		
//		String query = "insert into books (book_name,description,book_type) values(:bookName, :bookType, :description, :author)";
//		
//		SqlParameterSource params=new MapSqlParameterSource()
//				.addValue("boonName", bookName)
//				.addValue("bookType", bookType)
//				.addValue("description", description)
//				.addValue("author", authors);
//		
//		KeyHolder keyHolder=new GeneratedKeyHolder();
//		namedParameterJdbcTemplate.update(query,params,keyHolder);
//		
//		int bookId=keyHolder.getKey().intValue();
//		
//		
//		for (String authorName : authors) {
//			int authId;
//			try {
//				authId = jdbcTemplate.queryForObject("SELECT auth_id FROM authors WHERE auth_name = ?",
//						new Object[] { authorName }, Integer.class);
//			} catch (Exception e) {
////				jdbcTemplate.update("INSERT INTO authors (auth_name) VALUES (?)", authorName);
////				authId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
//				
//				String query1=;
//				SqlParameterSource params1= new MapSqlParameterSource()
//						.
//			}
//			jdbcTemplate.update("INSERT INTO book_auth (book_id, auth_id) VALUES (?, ?)", bookId, authId);
//		}
//	}

	public int insertBook(String bookName, String bookType, String description) {
		String query = "insert into books (book_name,description,book_type) values(:bookName, :bookType, :description)";

		SqlParameterSource params = new MapSqlParameterSource().addValue("bookName", bookName)
				.addValue("bookType", bookType).addValue("description", description);

		KeyHolder keyHolder = new GeneratedKeyHolder();
		namedParameterJdbcTemplate.update(query, params, keyHolder);

		return keyHolder.getKey().intValue();
	}

	public Integer findAuthorIdByName(String authorName) {
		String sql = "SELECT auth_id FROM authors WHERE auth_name = :name";
		MapSqlParameterSource params = new MapSqlParameterSource().addValue("name", authorName.trim());

//        List<Integer> ids = namedParameterJdbcTemplate.query(sql, params,
//                (rs, rowNum) -> rs.getInt("author_id"));
//
//        return ids.isEmpty() ? null : ids.get(0);

		try {
			return namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
		} catch (Exception e) {
			return null;
		}

	}

	public Integer insertAuthor(String authorName) {
		String sql = "INSERT INTO authors (auth_name) VALUES (:name)";
		MapSqlParameterSource params = new MapSqlParameterSource().addValue("name", authorName.trim());

		KeyHolder keyHolder = new GeneratedKeyHolder();
		namedParameterJdbcTemplate.update(sql, params, keyHolder, new String[] { "author_id" });
		return keyHolder.getKey().intValue();
	}

	public void insertBookAuth(int bookId, int authId) {
		String query = "INSERT INTO book_auth (book_id, auth_id) VALUES (:bookId, :authId)";
		MapSqlParameterSource params = new MapSqlParameterSource().addValue("bookId", bookId).addValue("authId",
				authId);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		namedParameterJdbcTemplate.update(query, params, keyHolder);
	}

	public void deleteBook(int bookId) {
		String sql = "DELETE FROM books WHERE book_id = :bookId";
		namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource("bookId", bookId));

	}

	public void deleteBookAuth(int bookId) {
		String sql = "DELETE FROM book_auth WHERE book_id = :bookId";
		namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource("bookId", bookId));

	}

	public void insertBookAuthBatch(int bookId,List<Integer> authid) {
		
		String sql = "INSERT INTO book_auth (book_id, auth_id) VALUES (:bookId, :authId)";
		
		List<MapSqlParameterSource> paramList = authid.stream()
			    .map(authorId -> new MapSqlParameterSource()
			            .addValue("bookId", bookId)
			            .addValue("authId", authorId))
			    .toList();

			MapSqlParameterSource[] batch = paramList.toArray(new MapSqlParameterSource[0]);

			namedParameterJdbcTemplate.batchUpdate(sql, batch);
		
	}
	
	
	public void updateBookDetails(int bookId, String name, String description, String type) {
	    String sql = "UPDATE books SET book_name = :bookName, description = :description, book_type = :bookType WHERE book_id = :bookId";
	    MapSqlParameterSource params = new MapSqlParameterSource()
	            .addValue("bookId", bookId)
	            .addValue("bookName", name)
	            .addValue("description", description)
	            .addValue("bookType", type);

	    namedParameterJdbcTemplate.update(sql, params);
	}
	
	
}
