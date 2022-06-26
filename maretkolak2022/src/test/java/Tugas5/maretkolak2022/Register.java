package Tugas5.maretkolak2022;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.response.Response;

import io.restassured.RestAssured;


public class Register {
	

	
	@Test
	public void kolakRegisterSukses () {
		
		String status = "";
			
		RestAssured.baseURI = "https://kolakproject.herokuapp.com";
		String register = ("{\r\n" + 
				"    \"username\":\"kolakadalahfavorit921\",\r\n" + 
				"    \"password\":\"kolakadalahfavorit\",\r\n" + 
				"    \"email\":\"kolakadalahfavorit291@gmail.com\",\r\n" + 
				"    \"phonenumber\":\"080989999\"\r\n" + 
				"}");
		
		Response responseRegister = (Response)RestAssured.given().contentType("application/Json").body(register).when().post("/register");
		status = responseRegister.jsonPath().get("Status");
		System.out.println(status);
		Assert.assertEquals(status, "Created");
		
	
	}
	
	@Test
	public void kolakRegisterFailed () {
		
		RestAssured.baseURI = "https://kolakproject.herokuapp.com";
		String daftar = ("{\r\n" + 
				"    \"username\":\"kolakadalahfavorit\",\r\n" + 
				"    \"password\":\"kolakadalahfavorit\",\r\n" + 
				"    \"email\":\"kolakadalahfavorit@gmail.com\",\r\n" + 
				"    \"phonenumber\":\"080989999\"\r\n" + 
				"}");
		
		Response responseRegister = RestAssured.given().contentType("application/json").body(daftar).when().post("/register");
		String status = responseRegister.jsonPath().get("Status");
		Assert.assertEquals(status, "Failed");
		Assert.assertEquals(responseRegister.statusCode(), 400);
		
	
	}
	

	
	
	//@Test
	public void registerJavaFaker () {
		
		String status = "";
		
		Faker faker = new Faker();
		String username = faker.name().firstName();
		String email = faker.internet().safeEmailAddress();
		
		
		RestAssured.baseURI = "https://kolakproject.herokuapp.com";
		String register = ("{\r\n" + 
				"    \"username\":\""+username+"\",\r\n" + 
				"    \"password\":\"kolakadalahfavorit\",\r\n" +            
				"    \"email\":\""+email+"\",\r\n" + 
				"    \"phonenumber\":\"080989999\"\r\n" + 
				"}");
		
		Response responseRegister = RestAssured.given().contentType("application/json").body(register).when().post("/register");
		status = responseRegister.jsonPath().get("Status");
		Assert.assertEquals(responseRegister.statusCode(), 201);
		System.out.println(status);
				
		
	}


}
