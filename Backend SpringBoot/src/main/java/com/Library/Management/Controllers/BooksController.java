package com.Library.Management.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Library.Management.Model.Response.BookResponse;
import com.Library.Management.Models.Request.AddBookRequest;
import com.Library.Management.Services.BooksServices;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "http://localhost:4200")
public class BooksController {
	@Autowired
	private BooksServices bookService;
	
	@GetMapping
    public List<BookResponse> getAllBooks() {
//		System.out.println(bookService.findBookWithAuthor());
        return bookService.findBookWithAuthor();
    }
	
	@PostMapping("/add")
	public String addBook(@RequestBody AddBookRequest book) {
		bookService.addBook(book);
		return "Book inserted successfully!";
	}

	@DeleteMapping("/{id}")
	public String deleteBook(@PathVariable("id") int bookId) {
		bookService.deleteBook(bookId);
		return "Book Deleted Successfully!";
	}
	
	@PutMapping("/{bookId}")
    public String updateBook(@PathVariable int bookId, @RequestBody AddBookRequest book) {
        bookService.updateBook(bookId, book);
        return "Book updated successfully!";
    }
}
