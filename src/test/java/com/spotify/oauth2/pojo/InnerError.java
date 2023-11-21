package com.spotify.oauth2.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InnerError {
	@JsonProperty("status")
	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	int status;

	@JsonProperty("message")
	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	String message;
}
