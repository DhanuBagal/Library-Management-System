package com.Library.Management.Models;

public class Authors {
	private int authId;
	private String authName;

	public Authors() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Authors(int authId, String authName) {
		super();
		this.authId = authId;
		this.authName = authName;
	}

	public int getAuthId() {
		return authId;
	}

	public void setAuthId(int authId) {
		this.authId = authId;
	}

	public String getAuthName() {
		return authName;
	}

	public void setAuthName(String authName) {
		this.authName = authName;
	}

	@Override
	public String toString() {
		return "Authors [authId=" + authId + ", authName=" + authName + "]";
	}

	
}
