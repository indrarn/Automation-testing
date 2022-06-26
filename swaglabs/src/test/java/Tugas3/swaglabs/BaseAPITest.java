package Tugas3.swaglabs;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeMethod;

import Tugas3.swaglabs.pages.DataUtility;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseAPITest {
	
	RequestSpecification commonJsonSpec = new RequestSpecBuilder().setBaseUri("https://api-staging-builder.engineer.ai")
			.setContentType(ContentType.JSON).build().log().all();

	RequestSpecification loginJsonSpec;
	String token;

	@BeforeMethod
	public void login() {
		// String payload =
		// "{\"email\":\"testlabs@gmail.com\",\"password\":\"builder123\"}";

		String payload = DataUtility.getDataFromExcel("Payloads", "LoginPayload");

		Response responseLogin = given().spec(commonJsonSpec).body(payload).when().post("/users/sign_in");

		token = responseLogin.jsonPath().get("user.authtoken");

		loginJsonSpec = new RequestSpecBuilder().setBaseUri("https://api-staging-builder.engineer.ai")
				.setContentType(ContentType.JSON).addHeader("authtoken", token).build().log().all();

	}
}
