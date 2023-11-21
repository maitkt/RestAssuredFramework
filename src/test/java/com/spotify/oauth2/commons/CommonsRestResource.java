package com.spotify.oauth2.commons;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import com.spotify.oauth2.commons.SpecBuilder;
import com.spotify.oauth2.pojo.Playlist;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CommonsRestResource {

	public static Response post(String path, String token, Object requestPlayList) {
		return given(SpecBuilder.getrequestspec()).body(requestPlayList).header("Authorization", "Bearer " + token)
				.when().post(path).then().spec(SpecBuilder.getresponsespec()).assertThat().extract().response();

		// users/31vvvjvgpaqnjrlq647sdu5ijmka/playlists
	}

	public static Response Get(String path, Object token) {
		return given(SpecBuilder.getrequestspec()).header("Authorization", "Bearer " + token).when().get(path).then()
				.spec(SpecBuilder.getresponsespec()).extract().response();
	}

	public static Response postAccount(HashMap<String, String> formParams) {
		return given().baseUri("https://accounts.spotify.com").contentType(ContentType.URLENC).formParams(formParams)
				.log().all().when().post("/api/token").then().spec(SpecBuilder.getresponsespec()).extract().response();
	}

}
