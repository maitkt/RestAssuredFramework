package sportfy.oauth2.test;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.spotify.oauth2.commons.SpecBuilder;
import com.spotify.oauth2.commons.API01.PlayListClass;
import com.spotify.oauth2.pojo.Error;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.DataLoader;
import com.spotify.oauth2.utils.FakerUtils;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Features;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
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
import io.restassured.response.Response;

@Epic("Playlist epic")
@Feature("Plalist Feature")
public class PlaylistTests {

	String invalid_token = "BQDo2NLgXY6SlRM1uRqFrrPD3zdh938uMswnqcG8ArQ2AJrgkFfE3X5qP9jNBtvJKNYHzSsiyI48E_75WrQFZcddTq6s9RHmLc53d6IOeSI_HFFOgc8r2iDicxidgdGnnnn4VNBJpWlWPhPKkru3FObMHLRAtjH_FsBiWTDhIA938y5bN2HzdS1hWqKjMNco-AkigSK4iClywcscQIaJvd7MQgE_2ScFf8UpKojIQfVQVps6vwjAeS97qE8Rj6mLRqXSgZPySoVBNyrs7SKS";

	@Step
	public Playlist playListBuilder(String name, String description, boolean mypublic) {
		return Playlist.builder().Name(name).Description(description).Mypublic(mypublic).build();

	}

	@Step
	public void assertPlayListEqual(Playlist requestPlayList, Playlist responseplaylist) {
		assertThat(responseplaylist.getName(), Matchers.equalTo(requestPlayList.getName()));
		assertThat(responseplaylist.getDescription(), Matchers.equalTo(requestPlayList.getDescription()));
		assertThat(responseplaylist.getMypublic(), Matchers.equalTo(requestPlayList.getMypublic()));

	}

	@Step
	public void assertStatusCode(int actulStatusCode, int expectedStatusCode) {
		assertThat(actulStatusCode, Matchers.equalTo(expectedStatusCode));
	};

	@Step
	public void assertError(com.spotify.oauth2.pojo.Error responseErr, int expectedStatusCode, String Expectedmsg) {
		assertThat(responseErr.getGetError().getStatus(), Matchers.equalTo(expectedStatusCode));
		assertThat(responseErr.getGetError().getMessage(), Matchers.equalTo(Expectedmsg));

	}

	//// **************////

	private void assertThat(String message, String errmsg) {
		// TODO Auto-generated method stub

	}

	private void assertThat(int status, int expectedStatusCode) {
		// TODO Auto-generated method stub

	}

	private void assertThat(int actulStatusCode, Matcher<Integer> matcher) {
		// TODO Auto-generated method stub

	}

	///// *************/////

	@Story("Story01- for creatng the plylit")
	@Description("To test whether the user is able to create the playlist")
	@Test(description = "Test for the create playlist	")
	public void ShouldBeAbleToCreatePlayList() {
		Playlist requestPlayList = playListBuilder(FakerUtils.generateName(), FakerUtils.generateDescription(), false);

		Response response = PlayListClass.post(requestPlayList);
		// assertThat(response.statusCode(), equals(200));
		Playlist responseplaylist = response.as(Playlist.class);
		assertStatusCode(response.getStatusCode(), 200);
		assertPlayListEqual(response.as(Playlist.class), requestPlayList);

	}

	private void assertThat(String description, Matcher<String> equalTo) {
		// TODO Auto-generated method stub

	}

	private void assertThat(boolean b, Matcher<Boolean> matcher) {
		// TODO Auto-generated method stub

	}

	@Story("Tory02 -  To retrieve the playlist")
	@Test
	public void ShouldBeAbleToGetPlayList() {
		Playlist requestPlayList = playListBuilder("updatedname", "updateddecription", false);
		Response response = PlayListClass.Get(DataLoader.getInstance().getPlayListId());
		Playlist responsePlaylist = response.as(Playlist.class);
		assertStatusCode(response.getStatusCode(), 200);
		// assertPlayListEqual(response.as(Playlist.class),requestPlayList);

	}

	@Story("Story01- for creatng the plylit")
	@Test
	public void ShouldNotBeAbleToCreatePlayListwithoutName() {
		Playlist requestPlayList = playListBuilder("", FakerUtils.generateDescription(), false);
		Response response = PlayListClass.post(requestPlayList);
		assertStatusCode(response.getStatusCode(), 400);
		assertError(response.as(Error.class).getGetError().getMessage(), 400, "Missing required field:name");

	}

	private void assertError(String message, int expectedStatusCode, String expectedmsg) {
		// TODO Auto-generated method stub

	}

	@Story("Story01- for creatng the plylit")
	@Test
	public void ShouldNotBeAbleToCreatePlayListwithInvalidToken() {
		Playlist requestPlayList = playListBuilder(FakerUtils.generateName(), FakerUtils.generateDescription(), false);
		Response response = PlayListClass.post_token(invalid_token, requestPlayList);
		assertStatusCode(response.getStatusCode(), 401);
		assertError(response.as(Error.class).getGetError().getMessage(), 401, "Invalid access token");

	}

}
