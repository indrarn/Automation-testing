package Tugas5.maretkolak2022;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.response.Response;


// i'm sorry sir, i'have watched your video more than 3 times and until today day,  i still dont understan about pom for testing API, so this is the best i can give
public class login {
	
	String auth = "";
	String status = "";
	String kepo = "" ;
	
	@Test (priority = 1)
	public void kolakLogin () {
		
		RestAssured.baseURI="https://kolakproject.herokuapp.com";
		String login ="{\r\n" + 
				"    \"username\":\"kolakadalahfavorit\",\r\n" + 
				"    \"password\":\"kolakadalahfavorit\"\r\n" + 
				"}";
		Response responseLogin =  RestAssured.given().contentType("application/json").body(login).when().post("/login");
		auth = responseLogin.jsonPath().get("Authorization");
		System.out.println(auth);	

		
	}
	@Test (priority = 2)
	public void meStatus () {
		RestAssured.baseURI="https://kolakproject.herokuapp.com";
		Response responseStatus = RestAssured.given().contentType("application/json").header("Authorization", "Bearer " +auth).when().get("/me/status");
		kepo = responseStatus.jsonPath().get("username");
		System.out.println(kepo);
		assertEquals(responseStatus.statusCode(), 503);
	}
	
	@Test
	public void LoginFailed () {
		RestAssured.baseURI="https://kolakproject.herokuapp.com";
		String login ="{\r\n" + 
				"    \"username\":\"kolakwakwaw\",\r\n" + 
				"    \"password\":\"kolakadalahfavorit\"\r\n" + 
				"}";
		Response responseLogin = RestAssured.given().contentType("application/json").body(login).when().post("/login");
		status = responseLogin.jsonPath().get("status");
		System.out.println(status);
		assertEquals(responseLogin.statusCode(), 200);
		
	}
	
	@Test
	public void LoginJavaFaker() {
		
		Faker faker = new Faker();
		String username = faker.name().firstName();
		String email = faker.internet().safeEmailAddress();
		String authFaker = "";
		String statusFaker = "";
		RestAssured.baseURI="https://kolakproject.herokuapp.com";
		String loginFaker =("{\r\n" + 
				"    \"username\":\""+username+"\",\r\n" + 
				"    \"password\":\"kolakadalahfavorit\",\r\n" +            
				"    \"email\":\""+email+"\",\r\n" + 
				"    \"phonenumber\":\"080989999\"\r\n" + 
				"}");
		Response responseLogin = RestAssured.given().contentType("application/json").body(loginFaker).when().post("/login");
		authFaker = responseLogin.jsonPath().get("Authorization");
		statusFaker = responseLogin.jsonPath().get("status");
		System.out.println(authFaker);	
		System.out.println(statusFaker);
	}
	

}
