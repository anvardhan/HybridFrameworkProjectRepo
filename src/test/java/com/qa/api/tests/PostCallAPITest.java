package com.qa.api.tests;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.app.utils.CommonUtilities;
import com.qa.app.utils.RestClient;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import pojo.Booking;
import pojo.BookingDates;
import pojo.Users;


public class PostCallAPITest {

	@Test (enabled=false)
	public void createUserTest_gorestAPI() {

		//https://gorest.co.in/public/v1/users
		String baseURI = "https://gorest.co.in";
		String basePath = "/public/v1/users";
		String userToken = "Bearer 58af3f196ef94f76a95ea35b155455129942a88b98c2f25a98129a2400589bbf";

		String jsonBodyPayload = "{\n" +
				"	\"id\": 100085,\n" +
				"	\"name\": \"Anand85\",\n" +
				"	\"email\": \"anand85@kiehn.com\",\n"+
				"	\"gender\": \"male\",\n" +
				"	\"status\": \"active\"\n"+
				"}";

		//Post call to create new user
		Response response = RestClient.postWebServiceCall(baseURI, basePath, userToken, null, jsonBodyPayload, null, true);

		//Validation-->
		int statusCode = response.getStatusCode();
		System.out.println("Status Code - "+statusCode);		
		Assert.assertEquals(statusCode, 201);

		String statusLine = response.getStatusLine();
		System.out.println("statusLine: "+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 201 Created","Check for status line fetched correctly or not");

		String responseBody = response.getBody().asString();
		System.out.println("Response Body is-->\n"+responseBody);		

		JsonPath jsonPathEvaluator = response.jsonPath();		
		String meta = jsonPathEvaluator.get("meta");
		System.out.println("Meta: "+meta);

		String contentType = response.header("Content-Type");
		System.out.println("contentType is -->"+contentType);

		String serverType = response.header("Server");
		System.out.println("serverType is -->"+serverType);

		Assert.assertEquals(contentType.contains("json"), true);
		Assert.assertEquals(serverType.contains("nginx"), true);

		//setup get call and validate if user is created or setup database connectivity to check if data is created/inserted or not
		//Create Query Parameters
		Map<String, String> reqParamsMap = new HashMap<String, String>();
		reqParamsMap.put("name", "Anand85");
		reqParamsMap.put("status", "active");
		Response responseGet = RestClient.getWebServiceCall(baseURI, basePath, reqParamsMap, false);
		//Get status code	
		Assert.assertEquals(responseGet.getStatusCode(), 200, "Incorrect status code generated");
		System.out.println("Status Line: "+ responseGet.getStatusLine());
		//Get response body
		String strResponseBody = responseGet.getBody().asString();
		System.out.println("responseBody: \n"+strResponseBody);
	}

	@Test (enabled=false)
	public void createUserTest_UsePOJO1_forJsonPayload() throws StreamWriteException, DatabindException, IOException {

		//https://gorest.co.in/public/v1/users
		String baseURI = "https://gorest.co.in";
		String basePath = "/public/v1/users";
		String userToken = "Bearer 58af3f196ef94f76a95ea35b155455129942a88b98c2f25a98129a2400589bbf";

		//create payload - Use POJO (using Users class having JSON payload element)

		//a) Create Java object (POJO - Plain old java object)
		Users user = new Users(100102, "Anand102", "Anand102@gmail.com", "male", "active");

		//b) convert java object (POJO) to JSON using Jackson API (search for Jackson-databind maven library and add it in pom)		

		ObjectMapper mapper = new ObjectMapper(); // import com.fasterxml.jackson.databind.ObjectMapper;
		String userJson = null;
		try {
			userJson = mapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		//System.out.println("User JSON payload: "+userJson);

		//c)store JSON to somewhere if needed (not necessary step)-> src\test\resources\UserJSONPayload
		//mapper.writeValue(resultFile, value);
		int i = 0; //use date to append with name - Implement later
		//mapper.writeValue(new File("src\\test\\resources\\UserJSONPayload\\user.json"), user);
		mapper.writeValue(new File("src\\test\\resources\\payloads\\user_"+i+".json"), user);


		//Post the request and get the response.
		Response response = RestClient.postWebServiceCall(baseURI, basePath, userToken, null, userJson, null, true);

		//Validation-->
		int statusCode = response.getStatusCode();
		System.out.println("Status Code - "+statusCode);		
		Assert.assertEquals(statusCode, 201);

		String statusLine = response.getStatusLine();
		System.out.println("statusLine: "+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 201 Created","Check for status line fetched correctly or not");

		String responseBody = response.getBody().asString();
		System.out.println("Response Body is-->\n"+responseBody);		

		JsonPath jsonPathEvaluator = response.jsonPath();		
		//String meta = jsonPathEvaluator.get("meta");
		//System.out.println("Meta: "+meta);

		String contentType = response.header("Content-Type");
		System.out.println("contentType is -->"+contentType);

		String serverType = response.header("Server");
		System.out.println("serverType is -->"+serverType);

		Assert.assertEquals(contentType.contains("json"), true);
		Assert.assertEquals(serverType.contains("nginx"), true);

		//setup get call and validate if user is created or setup database connectivity to check if data is created/inserted or not

	}

	@Test (enabled=false)
	public void createUserTest_UsePOJO2_forJsonPayload() throws StreamWriteException, DatabindException, IOException {

		//Used reusable method to create json payload with pojo and mapper

		//https://gorest.co.in/public/v1/users
		String baseURI = "https://gorest.co.in";
		String basePath = "/public/v1/users";
		String userToken = "Bearer 58af3f196ef94f76a95ea35b155455129942a88b98c2f25a98129a2400589bbf";

		//create payload - Use POJO (using Users class having JSON payload element)
		//Create Java object (POJO - Plain old java object)

		Users user = new Users(1001023, "Anand103", "Anand105@gmail.com", "male", "active");

		String userJson = CommonUtilities.createJsonPayload(user);
		//System.out.println("User JSON payload: "+userJson);

		//Post the request and get the response.
		Response response = RestClient.postWebServiceCall(baseURI, basePath, userToken, null, userJson, null, true);

		//Validation-->
		int statusCode = response.getStatusCode();
		System.out.println("Status Code - "+statusCode);		
		Assert.assertEquals(statusCode, 201);

		String statusLine = response.getStatusLine();
		System.out.println("statusLine: "+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 201 Created","Check for status line fetched correctly or not");

		String responseBody = response.getBody().asString();
		System.out.println("Response Body is-->\n"+responseBody);		

		JsonPath jsonPathEvaluator = response.jsonPath();		
		//String meta = jsonPathEvaluator.get("meta");
		//System.out.println("Meta: "+meta);

		String contentType = response.header("Content-Type");
		System.out.println("contentType is -->"+contentType);

		String serverType = response.header("Server");
		System.out.println("serverType is -->"+serverType);

		Assert.assertEquals(contentType.contains("json"), true);
		Assert.assertEquals(serverType.contains("nginx"), true);

		//setup get call and validate if user is created or setup database connectivity to check if data is created/inserted or not

	}


	//use https://www.jsonschema2pojo.org/ to convert json payloads to pojo classes
	//use https://jsonformatter.org/ to format json

	//restful booker - post with pojo

	@Test(enabled=false)
	public void createNewBookings_restfulBooker_pojo() throws StreamWriteException, DatabindException, IOException {

		String baseURI = "https://restful-booker.herokuapp.com";
		String basePath = "/booking";

		//create json payload - Use POJO (using Booking and Bookingdates class having JSON payload element)
		//Create Java object (POJO - Plain old java object)

		BookingDates bd = new BookingDates("2023-04-21", "2023-04-30");
		Booking booking1 = new Booking("Anand", "Vardhan", 523, false, "Breakfast", bd);

		String booking1Json = CommonUtilities.createJsonPayload(booking1);
		System.out.println("Booking JSON payload: "+booking1Json);


		//Post the request and get the response.
		Response response = RestClient.postWebServiceCall(baseURI, basePath, null, null, booking1Json, null, true);

		//Validation-->
		int statusCode = response.getStatusCode();
		System.out.println("Status Code - "+statusCode);		
		Assert.assertEquals(statusCode, 200);

		String statusLine = response.getStatusLine();
		System.out.println("statusLine: "+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK","Check for status line fetched correctly or not");

		String responseBody = response.getBody().asString();
		System.out.println("Response Body is-->\n"+responseBody);



	}

	@Test(enabled=false)
	public void createUser_ReadJsonUsingJsonFile_gorest() throws StreamWriteException, DatabindException, IOException {

		String baseURI = "https://gorest.co.in";
		String basePath = "/public/v1/users";
		String userToken = "Bearer 58af3f196ef94f76a95ea35b155455129942a88b98c2f25a98129a2400589bbf";

		//use JSON payload from File

		File file = new File(".\\src\\test\\resources\\payloads\\userCreate.json");	

		//String userJson = "	"; //get it from file object
		//System.out.println("User JSON payload: "+userJson);

		//Post the request and get the response.
		Response response = RestClient.postWebServiceCall(baseURI, basePath, userToken, null, null, file, true);

		//Validation-->
		int statusCode = response.getStatusCode();
		System.out.println("Status Code - "+statusCode);		
		Assert.assertEquals(statusCode, 201);

		String statusLine = response.getStatusLine();
		System.out.println("statusLine: "+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 201 Created","Check for status line fetched correctly or not");

		System.out.println(response.getBody().asString());

	}

	@Test(enabled=false)
	public void createUser_ReadJsonUsingJsonFileNoUpdate_gorest() throws StreamWriteException, DatabindException, IOException {

		String baseURI = "https://gorest.co.in";
		String basePath = "/public/v1/users";
		String userToken = "Bearer 58af3f196ef94f76a95ea35b155455129942a88b98c2f25a98129a2400589bbf";

		//use JSON payload from File

		File file = new File(".\\src\\test\\resources\\payloads\\userCreate.json");	

		//String userJson = "	"; //get it from file object
		//System.out.println("User JSON payload: "+userJson);

		//Post the request and get the response.
		Response response = RestClient.postWebServiceCall(baseURI, basePath, userToken, null, null, file, true);

		//Validation-->
		int statusCode = response.getStatusCode();
		System.out.println("Status Code - "+statusCode);		
		Assert.assertEquals(statusCode, 201);

		String statusLine = response.getStatusLine();
		System.out.println("statusLine: "+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 201 Created","Check for status line fetched correctly or not");

		System.out.println(response.getBody().asString());

	}

	@Test(enabled=false)
	public void createUser_ReadJsonUsingJsonFileAndUpdate_gorest_Parameterized_WithoutJackson() throws StreamWriteException, DatabindException, IOException {

		String baseURI = "https://gorest.co.in";
		String basePath = "/public/v1/users";
		String userToken = "Bearer 58af3f196ef94f76a95ea35b155455129942a88b98c2f25a98129a2400589bbf";

		//use JSON payload from File

		String file = ".\\src\\test\\resources\\payloads\\userSampleCreate.json";	

		String userUpdatedJson; //get it from file object

		userUpdatedJson = new String(Files.readAllBytes(Paths.get(file))); 

		String name = "Anand59";
		String email = "anand59@gmail.com";		
		userUpdatedJson = userUpdatedJson.replace("NAME", name).replace("EMAIL", email);


		//Post the request and get the response.
		Response response = RestClient.postWebServiceCall(baseURI, basePath, userToken, null, userUpdatedJson, null, true);

		//Validation-->
		int statusCode = response.getStatusCode();
		System.out.println("Status Code - "+statusCode);		
		Assert.assertEquals(statusCode, 201);

		String statusLine = response.getStatusLine();
		System.out.println("statusLine: "+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 201 Created","Check for status line fetched correctly or not");

		System.out.println(response.getBody().asString());


		//Iteration2
		userUpdatedJson = new String(Files.readAllBytes(Paths.get(file))); 

		name = "Anand60";
		email = "anand60@gmail.com";		
		userUpdatedJson = userUpdatedJson.replace("NAME", name).replace("EMAIL", email);


		//Post the request and get the response.
		response = RestClient.postWebServiceCall(baseURI, basePath, userToken, null, userUpdatedJson, null, true);

		//Validation-->
		statusCode = response.getStatusCode();
		System.out.println("Status Code - "+statusCode);		
		Assert.assertEquals(statusCode, 201);

		statusLine = response.getStatusLine();
		System.out.println("statusLine: "+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 201 Created","Check for status line fetched correctly or not");

		System.out.println(response.getBody().asString());

	}



}
