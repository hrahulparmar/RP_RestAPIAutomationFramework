package api.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endPoints.userEndPoints;
import api.payLoad.user;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class UserTestDD {
	
	

	@Test(priority = 1, dataProvider = "AllData", dataProviderClass = DataProviders.class)
	public void testCreateUser(String userId, String username, String firstname, String lastname, String email,
			String password, String phone) {
		user userPayload= new user();
		userPayload.setId(Integer.parseInt(userId));
		userPayload.setUsername(username);
		userPayload.setFirstName(firstname);
		userPayload.setLastName(lastname);
		userPayload.setEmail(email);
		userPayload.setPassword(password);
		userPayload.setPhone(phone);
		
		Response response = userEndPoints.createUser(userPayload);
		// log response
		response.then().log().all();

		// validation
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 2,dataProvider = "UserNamesData", dataProviderClass = DataProviders.class)
	public void testDeleteUser(String username) {
		user userPayload= new user();
		userPayload.setUsername(username);
		Response response = userEndPoints.deleteUser(userPayload.getUsername());
		// log response
		response.then().log().all();

		// validation
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
