package Tugas3.swaglabs;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;


public class APITest {

	String token = "";
	
	@Test(priority=1)
	public void loginAPI() {
		
		RestAssured.baseURI = "https://api-staging-builder.engineer.ai";
		String payload = "{\"email\":\"testlabs@gmail.com\",\"password\":\"builder123\"}";
		
		Response responseLogin =  RestAssured.given().contentType("application/json").body(payload).when().post("/users/sign_in");
		token = responseLogin.jsonPath().get("user.authtoken");
		System.out.println(token);
		
		}
	
	
	@Test(priority=2)
	public void DasboardAPI () {
		RestAssured.baseURI = "https://api-staging-builder.engineer.ai";
		Response responseDashboard = RestAssured.given().contentType("application/json").header("authtoken", token).params("status","completed").when().get("/build_cards");
		assertEquals(responseDashboard.statusCode(),200);
		
	}
	
	@Test(priority=3)
	public void configAPI () {
		RestAssured.baseURI = "https://api-staging-builder.engineer.ai";
		Response configAPI = RestAssured.given().contentType("application/json").when().get("/configurations");
		assertEquals(configAPI.statusCode(),200);
		
	}	
	@Test
	public void ApiRegres() {
		
		String name ="";
		RestAssured.baseURI = "https://reqres.in";
		String payload2 = "{\"name\":\"morpheus\",\"job\":\"leader\"}";
		
		Response responseLogin2 = RestAssured.given().contentType("application/json").body(payload2).when().post("api/users");
		name = responseLogin2.jsonPath().get("name");
		String expectedName = "morpheus";
		
		Assert.assertEquals(responseLogin2.statusCode(),201);
		Assert.assertTrue(name.contains(expectedName));
		
	}
	
	
	
}
