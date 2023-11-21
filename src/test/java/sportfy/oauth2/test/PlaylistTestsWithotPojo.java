package sportfy.oauth2.test;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.matcher.*;

public class PlaylistTestsWithotPojo {
	RequestSpecification requestspecification;
	ResponseSpecification responsespecification;
	String access_token = "BQAZ6tjspIMDEYbckA982zmK9A4uMNcpO2YusTAsVi71d0j0xIIBdt3ZQeE6_L_Zx_j11t5L5xFiU-E3d-aVZk5nOsRjoCradm6COxVxYKOI0uPWabhmqsnvAJB0GzHI5zj0fhZrzfYK-hQE1EUskfBhgCY5jFQa5ur-OgUgYyXk71zWCet71JUokh5qx5N3ZFK9XWbChKwDPN4BiN2vZLqli2nhL6l5SglFO0niNmD4jzbMsDjwx-G7_RgJx4-vtdWSUyAKw2N1a0tI";

	@BeforeClass
	public void beforeClass() throws FileNotFoundException {
		// PrintStream FileOutPutStream = new PrintStream(new
		// File("./logs/restassured.log"));
		RequestSpecBuilder requestspecbuilder = new RequestSpecBuilder().setBaseUri("https://api.spotify.com")
				.setBasePath("/v1").addHeader("Authorization", "Bearer " + access_token)
				.setContentType(ContentType.JSON).log(LogDetail.ALL);
		requestspecification = requestspecbuilder.build();
		ResponseSpecBuilder responsespecbuilder = new ResponseSpecBuilder().expectContentType(ContentType.JSON)
				.log(LogDetail.ALL);
		responsespecification = responsespecbuilder.build();
	}

	@Test
	public void ShouldBeAbleToCreatePlayList() {
		String payload = "{\r\n" + "\"name\":\"Playlist RESTNEW\",\r\n"
				+ "\"description\":\"New playlist description\",\r\n" + "\"public\":false\r\n" + "}";
		given(requestspecification).body(payload).when().post("users/31vvvjvgpaqnjrlq647sdu5ijmka/playlists").then()
				.spec(responsespecification).assertThat().statusCode(201)
				.body("name", Matchers.equalTo("Playlist RESTNEW"), "public", Matchers.equalTo(false));

	}

	@Test
	public void ShouldBeAbleToGetPlayList() {
		given(requestspecification).when().get("/playlists/07NykBIPZ09Xzvbnm804ND").then().spec(responsespecification)
				.assertThat().statusCode(200)
				.body("name", Matchers.equalTo("Playlist REST"), "public", Matchers.equalTo(false));

	}

	@Test
	public void ShouldNotBeAbleToCreatePlayListwithoutName() {
		String payload = "{\r\n" + "\"name\":\"\",\r\n" + "\"description\":\"New playlist description\",\r\n"
				+ "\"public\":false\r\n" + "}";
		given(requestspecification).body(payload).when().post("users/31vvvjvgpaqnjrlq647sdu5ijmka/playlists").then()
				.spec(responsespecification).assertThat().statusCode(400).body("error.message",
						Matchers.equalTo("Missing required field: name"), "error.status", Matchers.equalTo(400));
	}

	@Test
	public void ShouldNotBeAbleToCreatePlayListwithInvalidToken() {
		String payload = "{\r\n" + "\"name\":\"Playlist RESTNEW\",\r\n"
				+ "\"description\":\"New playlist description\",\r\n" + "\"public\":false\r\n" + "}";
		given().baseUri("https://api.spotify.com").basePath("/v1").header("Authorization", "Bearer" + "12345")
				.contentType(ContentType.JSON).log().all().body(payload).when()
				.post("users/31vvvjvgpaqnjrlq647sdu5ijmka/playlists").then().spec(responsespecification).assertThat()
				.statusCode(400).body("error.message", Matchers.equalTo("Only valid bearer authentication supported"));

	}

}
