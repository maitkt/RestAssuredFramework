package com.spotify.oauth2.commons;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {

	public static RequestSpecification getrequestspec() {
		return (RequestSpecification) new RequestSpecBuilder()
				.setBaseUri("https://api.spotify.com")
				.setBasePath("/v1").setContentType(ContentType.JSON).addFilter(new AllureRestAssured())
				.log(LogDetail.ALL).build();
	}

	public static ResponseSpecification getresponsespec() {
		return new ResponseSpecBuilder().expectContentType(ContentType.JSON).log(LogDetail.ALL).build();
	}

}
