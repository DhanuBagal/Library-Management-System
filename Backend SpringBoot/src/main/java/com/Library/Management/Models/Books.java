package com.Library.Management.Models;

import org.springframework.beans.factory.annotation.Autowired;

public class Books {
	private int bookId;
	private String bookName;
	private String description;
	private String bookType;

	public Books() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Books(int bookId, String bookName, String description, String bookType) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.description = description;
		this.bookType = bookType;
	}

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

	@Override
	public String toString() {
		return "Books [bookId=" + bookId + ", bookName=" + bookName + ", description=" + description + ", bookType="
				+ bookType + "]";
	}

}
