package com.Library.Management.Model.Response;

import java.util.ArrayList;
import java.util.List;

public class BookResponse {
	private int bookId;
	private String bookName;
	private String description;
	private String bookType;
	private List<String> authors = new ArrayList<>();

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBookType() {
		return bookType;
	}

	public void setBookType(String bookType) {
		this.bookType = bookType;
	}

	public List<String> getAuthors() {
		return authors;
	}

	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}

	@Override
	public String toString() {
		return "BookResponse [bookId=" + bookId + ", bookName=" + bookName + ", description=" + description
				+ ", bookType=" + bookType + ", authors=" + authors + "]";
	}

}
