package api.endPoints;

import static io.restassured.RestAssured.given;
import api.payLoad.user;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class userEndPoints {

	public static Response createUser(user payload) {
		Response response = given().accept(ContentType.JSON).contentType(ContentType.JSON).body(payload)

				.when().post(Routes.post_url);

		return response;
	}

	public static Response getUser(String username) {
		Response response = given().accept(ContentType.JSON).pathParam("username", username)

				.when().get(Routes.get_url);

		return response;
	}

	public static Response updateUser(String username, user payload) {
		Response response = given().accept(ContentType.JSON).contentType(ContentType.JSON)
				.pathParam("username", username).body(payload)

				.when().put(Routes.put_url);

		return response;
	}

	public static Response deleteUser(String username) {
		Response response = given().accept(ContentType.JSON).pathParam("username", username)

				.when().delete(Routes.delete_url);

		return response;
	}
}
