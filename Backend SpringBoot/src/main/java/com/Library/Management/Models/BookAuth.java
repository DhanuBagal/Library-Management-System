package com.Library.Management.Models;

public class BookAuth {
	private int bookAuthId;
	private int bookId;
	private int authId;

	public BookAuth() {
	}

	public BookAuth(int bookAuthId, int bookId, int authId) {
		this.bookAuthId = bookAuthId;
		this.bookId = bookId;
		this.authId = authId;
	}

	public int getBookAuthId() {
		return bookAuthId;
	}

	public void setBookAuthId(int bookAuthId) {
		this.bookAuthId = bookAuthId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getAuthId() {
		return authId;
	}

	public void setAuthId(int authId) {
		this.authId = authId;
	}

	@Override
	public String toString() {
		return "BookAuth [bookAuthId=" + bookAuthId + ", bookId=" + bookId + ", authId=" + authId + "]";
	}
}
