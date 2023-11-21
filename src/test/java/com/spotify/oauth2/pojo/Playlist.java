package com.spotify.oauth2.pojo;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Builder
@Jacksonized
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Playlist {
	@JsonProperty("collaborative")
	public boolean collabortive;

	@JsonProperty("description")
	public String Description;
	@JsonProperty("external_urls")
	public ExternalUrls External_urls;

	@JsonProperty("followers")
	public Followers Followers;

	@JsonProperty("href")
	public String Href;

	@JsonProperty("id")
	public String id;

	@JsonProperty("images")
	public ArrayList<Object> Images;

	@JsonProperty("Name")
	public String Name;

	@JsonProperty("owner")
	public Owner owner;

	@JsonProperty("primary_color")
	public Object Primary_color;

	@JsonProperty("public")
	public boolean Mypublic;

	@JsonProperty("snapshot_id")
	public String Snapshot_id;

	@JsonProperty("tracks")
	public Tracks Tracks;

	@JsonProperty("type")
	public String Type;

	@JsonProperty("uri")
	public String uri;

	public String getMypublic() {
		// TODO Auto-generated method stub
		return null;
	}
}
