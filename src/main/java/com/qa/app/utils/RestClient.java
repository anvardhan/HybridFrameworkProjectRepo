package com.qa.app.utils;


import java.io.File;
import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class RestClient {

	//HTTP Methods - GET, POST, PUT, DELETE
	//Rest Assured

	/**
	 * This method is used to call GET API.
	 * @param baseURI - baseURI of the request
	 * @param basePath - basePath of the request
	 * @param tokenID - provide null if "tokenID" not required
	 * @param reqParamsMap - provide "null" if reqParamsMap not required	 * 
	 * @param log - provide "true" if log required to be generated else provide "false"
	 * @return - This method is returning Response object from the Get call
	 * 
	 */
	public static Response getWebServiceCall(String baseURI, String basePath, String tokenID, 
			Map<String,String> reqParamsMap, boolean log) {

		//Set baseURI
		if(setBaseURI(baseURI)) {
			//Create request - Prepare request with query params, request headres, content-type and token if required
			RequestSpecification request = createGetRequest(reqParamsMap, tokenID, null, log);		
			//Execute Request and Generate Response
			return getResponse("GET", request, basePath);
		}

		return null;
	}

	/**
	 * This method is used to call GET API.
	 * @param baseURI - baseURI of the request
	 * @param basePath - basePath of the request
	 * @param tokenID - provide null if "tokenID" not required
	 * @param reqParamsMap - provide "null" if reqParamsMap not required
	 * @param clientID - provide "null" if clinetID is not required in request header
	 * @param log - provide "true" if log required to be generated else provide "false"
	 * @return - This method is returning Response object from the Get call
	 * 
	 */
	public static Response getWebServiceCall(String baseURI, String basePath, String tokenID, 
			Map<String,String> reqParamsMap, String clientID, boolean log) {

		//Set baseURI
		if(setBaseURI(baseURI)) {
			//Create request - Prepare request with query params, request headres, content-type and token if required
			RequestSpecification request = createGetRequest(reqParamsMap, tokenID, clientID, log);		
			//Execute Request and Generate Response
			return getResponse("GET", request, basePath);
		}

		return null;
	}

	/**
	 * This method is used to call GET API.
	 * @param baseURI - baseURI of the request
	 * @param basePath - basePath of the request	 * 
	 * @param reqParamsMap - provide "null" if reqParamsMap not required	 *
	 * @param log - provide "true" if log required to be generated else provide "false"
	 * @return - This method is returning Response object from the Get call
	 * 
	 */
	public static Response getWebServiceCall(String baseURI, String basePath, 
			Map<String,String> reqParamsMap, boolean log) {

		//Set baseURI
		if(setBaseURI(baseURI)) {
			//Create request - Prepare request with query params, request headres, content-type and token if required
			RequestSpecification request = createGetRequest(reqParamsMap, null, null, log);		
			//Execute Request and Generate Response
			return getResponse("GET", request, basePath);
		}

		return null;
	}


	public static Response postWebServiceCall(String baseURI, String basePath, String tokenID, 
			Map<String,String> reqParamsMap, String payLoad, File file, boolean log) {

		//Set baseURI
		if(setBaseURI(baseURI)) {
			//Create request - Prepare request with query params, request headres, content-type and token if required
			RequestSpecification request = createPostRequest(payLoad, file, reqParamsMap, tokenID, log);		

			//Execute Request and Generate Response
			return getResponse("POST", request, basePath);
		}

		return null;
	}


	private static boolean setBaseURI(String baseURI) {

		if(baseURI==null || baseURI.isEmpty()) {
			System.out.println("base URI is null/blank.. Please pass correct base URI...");
			return false;
		}

		try {
			RestAssured.baseURI = baseURI;
			return true;
		} catch(Exception e) {
			System.out.println("Exception occured while assiging the baseURI with Rest Assured...");
			return false;
		}

	}

	private static Response getResponse(String httpMethod, RequestSpecification request, String basePath) {

		Response response = null;

		switch (httpMethod) {
		case "GET":
			response = request.get(basePath);
			System.out.println("API Status Code: "+response.getStatusCode());
			break;

		case "POST":
			response = request.post(basePath);
			System.out.println("API Status Code: "+response.getStatusCode());
			break;

		default:
			break;
		}


		return response;
	}



	private static RequestSpecification createGetRequest(Map<String, String> reqParamsMap, String tokenID, 
			String clientID, boolean log) {

		RequestSpecification request = null;		

		if(log) {
			request = RestAssured.given().log().all();
		} else {
			request = RestAssured.given();
		}

		//request.queryParam("name", "Anand");
		//request.queryParam("status","active");		
		//request.params("name", "Anand","status","active");

		if(reqParamsMap != null)
			request.queryParams(reqParamsMap);


		//Add required request headers (ClientID) and content-Type
		//request.header("Accept","*/*","Accept-Encoding", "gzip, deflate, br","Connection", "keep-alive");
		//request.header("Content-Type", "application/json");

		if(clientID != null)
			request.header("client_id",clientID);

		request.contentType(ContentType.JSON);

		//Add token
		if(tokenID != null)
			request.header("Authorization", "Bearer "+tokenID);


		return request;

	}



	private static RequestSpecification createPostRequest(String payLoad, File file, Map<String, String> reqParamsMap,
			String tokenID, boolean log) {

		RequestSpecification request = null;		

		if(log) {
			request = RestAssured.given().log().all();
		} else {
			request = RestAssured.given();
		}

		//Add payLoad
		if(payLoad != null) {
			request.body(payLoad);
		} else if (file != null) {
			request.body(file);
		}

		//request.queryParam("name", "Anand");
		//request.queryParam("status","active");		
		//request.params("name", "Anand","status","active");

		if(reqParamsMap != null)
			request.queryParams(reqParamsMap);


		//Add required request headers (ClientID) and content-Type
		//request.header("Accept","*/*","Accept-Encoding", "gzip, deflate, br","Connection", "keep-alive");
		//request.header("Content-Type", "application/json");
		request.contentType(ContentType.JSON);

		//Add token
		if(tokenID != null)
			request.header("Authorization", tokenID);

		return request;
	}


	/*From API Document - Generate Token For OAuth2
	 * POST /token	
	The endpoint used for requesting an access token, using either the authorization_code or client_credentials grant type.

	http://coop.apps.symfonycasts.com/token
	This accepts the following POST fields:

	client_id
	client_secret
	grant_type Either client_credentials or authorization_code
	redirect_uri (authorization_code only) Must match redirect_uri from the original /authorize call
	code (authorization_code only) The authorization code */

	/**
	 * This method returns user tokenID
	 * @param clientId
	 * @param clientSecret
	 * @param grantType
	 * @param accessTokenUrl
	 * @return tokenID
	 */
	public static String getUserToken_Oatuh2(String clientId, String clientSecret, 
			String grantType, String accessTokenUrl) {

		/*String clientId = "MyAPITestApp";
		String clientSecret = "8d74547833f8e989ed03532a13e5e3b9";
		String grantType = "client_credentials";
		String accessTokenUrl = "http://coop.apps.symfonycasts.com/token"; */

		RequestSpecification request = RestAssured.given();

		//Below values must match as per the API Document
		request.formParam("client_id", clientId);
		request.formParam("client_secret", clientSecret);
		request.formParam("grant_type", grantType);

		Response response = request.post(accessTokenUrl);

		String tokenID = response.jsonPath().get("access_token");
		System.out.println("Token ID using OAuth2 = "+tokenID);

		return tokenID;

	}

	/**
	 * This method returns user tokenID for ImagurApp
	 * @param clientId
	 * @param clientSecret
	 * @param grantType
	 * @param accessTokenUrl
	 * @param refreshToken
	 * @return tokenID
	 */
	public static String getImagurAppToken_Oatuh2(String clientId, String clientSecret, 
			String grantType, String accessTokenUrl, String refreshToken) {

		RequestSpecification request = RestAssured.given();

		//Below values must match as per the API Document
		request.formParam("client_id", clientId);
		request.formParam("client_secret", clientSecret);
		request.formParam("grant_type", grantType);
		request.formParam("refresh_token", refreshToken);

		Response response = request.post(accessTokenUrl);

		String tokenID = response.jsonPath().get("access_token");
		System.out.println("Token ID using OAuth2 = "+tokenID);

		return tokenID;

	}

	/**
	 * This method returns user tokenID for ImagurApp - 2nd Approach
	 * @param clientId
	 * @param clientSecret
	 * @param grantType
	 * @param accessTokenUrl
	 * @param refreshToken
	 * @return tokenID
	 */
	public static String getImagurAppToken_Oatuh2_BDD(String clientId, String clientSecret, 
			String grantType, String accessTokenUrl, String refreshToken) {

		Map<String, String> formParams = new HashMap<String, String>();
		formParams.put("refresh_token", refreshToken);
		formParams.put("client_id", clientId);
		formParams.put("client_secret", clientSecret);
		formParams.put("grant_type", grantType);

		String tokenID = 
				given()
					.formParams(formParams)
				.when()
					.post(accessTokenUrl)
				.then()
					.extract().jsonPath().getString("access_token");		
		
		System.out.println("Token ID using OAuth2 = "+tokenID);
		return tokenID;

	}
	
	/**
	 * This method returns user tokenID for ImagurApp - 2nd Approach
	 * @param clientId
	 * @param clientSecret
	 * @param grantType
	 * @param accessTokenUrl
	 * @param refreshToken
	 * @return getAccessToken_entireJSONMap - Entire Json Map
	 */
	public static Map<Object, Object> getAccessToken_entireJSONMap(String clientId, String clientSecret, 
			String grantType, String accessTokenUrl, String refreshToken) {

		Map<String, String> formParams = new HashMap<String, String>();
		formParams.put("refresh_token", refreshToken);
		formParams.put("client_id", clientId);
		formParams.put("client_secret", clientSecret);
		formParams.put("grant_type", grantType);

		JsonPath tokenJson = 
				given()
					.formParams(formParams)
				.when()
					.post(accessTokenUrl)
				.then()
					.extract().jsonPath();		

		return tokenJson.getMap("");

	}



}
