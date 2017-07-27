package com.niit.domain;

public class Message {

	private String message;

	private long id;
	
	public String userId;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	
	public Message(String message, long id, String userId) {
		this.message = message;
		this.id = id;
		this.userId = userId;
	}

	
	public Message()
	{
		
	}

	
}
