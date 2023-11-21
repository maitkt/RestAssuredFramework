package com.spotify.oauth2.commons.API01;

import static io.restassured.RestAssured.given;

import com.spotify.oauth2.commons.CommonsRestResource;
import com.spotify.oauth2.commons.Route;
import com.spotify.oauth2.commons.SpecBuilder;
import com.spotify.oauth2.commons.TokenManager;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.ConfigLoader;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PlayListClass {

	@Step
	public static Response post(Playlist requestPlayList) {
		return CommonsRestResource.post(Route.USERS + "/" + ConfigLoader.getInstance().getUser() + Route.PLAYLISTS,
				TokenManager.getToken(), requestPlayList);
	}

	public static Response post_token(String token, Playlist requestPlayList) {
		return CommonsRestResource.post(Route.USERS + "/" + ConfigLoader.getInstance().getUser() + Route.PLAYLISTS,
				token, requestPlayList);
	}

	public static Response Get(String Playlistid) {
		return CommonsRestResource.Get(Route.PLAYLISTS + Playlistid, TokenManager.getToken());
	}
}
