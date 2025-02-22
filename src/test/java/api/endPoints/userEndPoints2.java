package api.endPoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payLoad.user;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class userEndPoints2 {
	
	static ResourceBundle getURL() {
		ResourceBundle routes= ResourceBundle.getBundle("Routes"); //load Routes.properties
		return routes;
	}

	public static Response createUser(user payload) {
		
		String post_url= getURL().getString("post_url");
		Response response = given().accept(ContentType.JSON).contentType(ContentType.JSON).body(payload)

				.when().post(post_url);

		return response;
	}

	public static Response getUser(String username) {
		String get_url= getURL().getString("get_url");
		Response response = given().accept(ContentType.JSON).pathParam("username", username)

				.when().get(get_url);

		return response;
	}

	public static Response updateUser(String username, user payload) {
		String update_url= getURL().getString("put_url");
		Response response = given().accept(ContentType.JSON).contentType(ContentType.JSON)
				.pathParam("username", username).body(payload)

				.when().put(update_url);

		return response;
	}

	public static Response deleteUser(String username) {
		String delete_url= getURL().getString("delete_url");
		Response response = given().accept(ContentType.JSON).pathParam("username", username)

				.when().delete(delete_url);

		return response;
	}
}
