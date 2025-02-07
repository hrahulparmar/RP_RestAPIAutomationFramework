package api.testCases;

import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.apache.logging.log4j.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endPoints.userEndPoints;
import api.payLoad.user;
import io.restassured.response.Response;

public class userTest {

	Faker faker;
	user userPayload;
	public static Logger logger;

	@BeforeClass
	public void generateTestData() {
		faker = new Faker();
		userPayload = new user();
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		logger = LogManager.getLogger("RestAssuredFramework");
	}

	@Test(priority = 1)
	public void testCreateUser() {
		Response response = userEndPoints.createUser(userPayload);
		// log response
		response.then().log().all();

		// validation
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//log
		logger.info("Create user executed");
	}

	@Test(priority = 2)
	public void testGetUserData() {
		Response response = userEndPoints.getUser(userPayload.getUsername());
		// log response
		response.then().log().all();

		// validation
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//log
		logger.info("Get user Data");
	}

	@Test(priority = 3)
	public void testUpdateUser() {
		userPayload.setFirstName(faker.name().firstName());
		Response response = userEndPoints.updateUser(userPayload.getUsername(), userPayload);
		// log response
		response.then().log().all();

		// validation
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//read user data to check if first name is update
		Response responsePostUpdate= userEndPoints.getUser(userPayload.getFirstName());
		responsePostUpdate.then().log().all();
		//log
		logger.info("Update user Data");
	}

	@Test(priority = 4)
	public void testDeleteUser() {
		Response response = userEndPoints.deleteUser(userPayload.getUsername());
		// log response
		response.then().log().all();

		// validation
		Assert.assertEquals(response.getStatusCode(), 200);
		//log
		logger.info("Delete user Data");
	}
}
