package com.cg.exception;

import java.time.LocalDateTime;

public class ErrorInformation {
	
	private String url;
	private String message;
	private LocalDateTime datetime;
	public ErrorInformation(String url, String message, LocalDateTime datetime) {
		super();
		this.url = url;
		this.message = message;
		this.datetime = datetime;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LocalDateTime getDatetime() {
		return datetime;
	}
	public void setDatetime(LocalDateTime datetime) {
		this.datetime = datetime;
	}
	
	
}
