package com.Library.Management.Services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.Library.Management.Controllers.BooksController;
import com.Library.Management.DAO.BooksDAO;
import com.Library.Management.Model.Response.BookResponse;
import com.Library.Management.Models.Request.AddBookRequest;

@Service
public class BooksServices {


	@Autowired
	private BooksDAO bookDAO;

   
	@Transactional
	public void addBook(AddBookRequest book) {

		if (book.getBookName() == null || book.getBookName().trim().isEmpty()) {
			throw new IllegalArgumentException("Book name cannot be empty");
		}

		if (book.getAuthors() == null || book.getAuthors().length == 0) {
			throw new IllegalArgumentException("At least one author is required");
		}

//		bookDAO.insertBooks(book.getBookName(), book.getDescription(), book.getBookType(), book.getAuthors());
		
		int bookId=bookDAO.insertBook(book.getBookName(), book.getDescription(), book.getBookType());
		
//		Integer author;
//
//        for (String authorName : book.getAuthors()) {
//            
//            Integer authorId = bookDAO.findAuthorIdByName(authorName);
//
//            
//            if (authorId == null) {
//                authorId = bookDAO.insertAuthor(authorName);
//            }
//
//            bookDAO.insertBookAuth(bookId,authorId);
//        }
        
		List<Integer> authIds = Arrays.stream(book.getAuthors())
		        .filter(name -> name != null && !name.isBlank())
		        .distinct()
		        .map(authorName -> {
		            Integer authorId = bookDAO.findAuthorIdByName(authorName);
		            if (authorId == null) {
		                authorId = bookDAO.insertAuthor(authorName);
		            }
		            return authorId;
		        })
		        .toList();

        
        bookDAO.insertBookAuthBatch(bookId,authIds);
	}
	
	@Transactional
	public void deleteBook(int bookId) {
		bookDAO.deleteBookAuth(bookId);
		bookDAO.deleteBook(bookId);
	}
	
	@Transactional
	public void updateBook(int bookId, AddBookRequest book) {
	    
	    if (book.getBookName() == null || book.getBookName().trim().isEmpty()) {
	        throw new IllegalArgumentException("Book name cannot be empty");
	    }

	    if (book.getAuthors() == null || book.getAuthors().length == 0) {
	        throw new IllegalArgumentException("At least one author is required");
	    }

	    bookDAO.updateBookDetails(bookId, book.getBookName(), book.getDescription(), book.getBookType());

	    List<Integer> authIds = Arrays.stream(book.getAuthors())
	            .filter(name -> name != null && !name.isBlank())
	            .distinct()
	            .map(authorName -> {
	                Integer authorId = bookDAO.findAuthorIdByName(authorName);
	                if (authorId == null) {
	                    authorId = bookDAO.insertAuthor(authorName);
	                }
	                return authorId;
	            })
	            .toList();


	    bookDAO.deleteBookAuth(bookId);

	    bookDAO.insertBookAuthBatch(bookId, authIds);
	}
	
	public List<BookResponse> findBookWithAuthor() {
		
		List<BookResponse> list=bookDAO.findAllBooksWithAuthors();
		
		Map<Integer, BookResponse> grouped = list.stream()
                .collect(Collectors.toMap(
                		b -> b.getBookId(),
                        b -> b,
                        (b1, b2) -> {
                            b1.getAuthors().addAll(b2.getAuthors());
                            return b1;
                        }
                ));

        return new ArrayList<>(grouped.values());
	}

}
