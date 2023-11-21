package com.spotify.oauth2.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Error {

	@JsonProperty("error")
	public InnerError getError;
}
