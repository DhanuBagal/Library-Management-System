package com.Library.Management.Models;

import java.time.LocalDateTime;

public class BookIssued {
	private int bookIssueId;
	private int bookId;
	private int userId;
	private LocalDateTime issuedAt;
	private LocalDateTime returnedAt;

	public BookIssued() {
	}

	public BookIssued(int bookIssueId, int bookId, int userId, LocalDateTime issuedAt, LocalDateTime returnedAt) {
		this.bookIssueId = bookIssueId;
		this.bookId = bookId;
		this.userId = userId;
		this.issuedAt = issuedAt;
		this.returnedAt = returnedAt;
	}

	public int getBookIssueId() {
		return bookIssueId;
	}

	public void setBookIssueId(int bookIssueId) {
		this.bookIssueId = bookIssueId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public LocalDateTime getIssuedAt() {
		return issuedAt;
	}

	public void setIssuedAt(LocalDateTime issuedAt) {
		this.issuedAt = issuedAt;
	}

	public LocalDateTime getReturnedAt() {
		return returnedAt;
	}

	public void setReturnedAt(LocalDateTime returnedAt) {
		this.returnedAt = returnedAt;
	}

	@Override
	public String toString() {
		return "BooksIssued [bookIssueId=" + bookIssueId + ", bookId=" + bookId + ", userId=" + userId + ", issuedAt="
				+ issuedAt + ", returnedAt=" + returnedAt + "]";
	}
}
