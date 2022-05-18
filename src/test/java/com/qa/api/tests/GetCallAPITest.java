package com.qa.api.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.app.utils.RestClient;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetCallAPITest {


	//Get call with/without query parameter
	@Test (enabled=false)
	public void validateGetRequest_gorestAPI() {

		String baseUri = "https://gorest.co.in";
		String basePath = "/public/v1/users";
		String userToken = "";
		//String userToken = "Bearer 58af3f196ef94f76a95ea35b155455129942a88b98c2f25a98129a2400589bbf";

		//Create Query Parameters
		Map<String, String> reqParamsMap = new HashMap<String, String>();
		reqParamsMap.put("name", "Anand V");
		reqParamsMap.put("status", "active");		

		//Generate Response - Get call
		//No Token - pass null for token
		Response response = RestClient.getWebServiceCall(baseUri, basePath, reqParamsMap, false);	
		//With Token, No parameter (pass null for parameter)
		//Response response = RestClient.getWebServiceCall(baseUri, basePath, userToken, null, true);
		//With Token and parameter
		//Response response = RestClient.getWebServiceCall(baseUri, basePath, userToken, reqParamsMap, true);

		//Get status code	
		Assert.assertEquals(response.getStatusCode(), 200, "Incorrect status code generated");
		System.out.println("Status Line: "+ response.getStatusLine());

		//Get response body
		String strResponseBody = response.getBody().asString();
		//String strResponseBody2 = response.asString();
		//String strResponseBodyPrettyFormat = response.prettyPrint();

		System.out.println("responseBody: \n"+strResponseBody);
		//System.out.println("responseBody: \n"+strResponseBody2);
		//System.out.println("response: \n"+strResponseBodyPrettyFormat);

		//JSON Response Validation

	}


	//Generate Token using a post call service and use generated token in another API calls -Post, Get
	@Test (enabled=false)
	public void validateGetRequest_restfulBookerAPI() {

		//Step1 - Generate Token using below post call API and then extract token and use it in Get call
		String baseUri = "https://restful-booker.herokuapp.com";
		String basePath = "/auth";

		String payLoad = "{\r\n" + 
				"    \"username\" : \"admin\",\r\n" + 
				"    \"password\" : \"password123\"\r\n" + 
				"}";

		//Generate Response - Post call		
		Response response = RestClient.postWebServiceCall(baseUri, basePath, null, null, payLoad, null, true);

		//Assert.assertEquals(response.getStatusCode(), 201, "Incorrect status code generated");
		System.out.println("Status Line: "+ response.getStatusLine());
		String strResponseBody = response.getBody().asString();
		System.out.println("responseBody: \n"+strResponseBody);

		JsonPath js = response.jsonPath();
		String token = js.get("token");
		System.out.println("Token ID: "+token);
		Assert.assertNotNull(token);

		String userToken = token;


		//Step2 - Use token and hit Post request

		basePath = "/booking";

		payLoad = "{\r\n" + 
				"    \"firstname\" : \"Anand\",\r\n" + 
				"    \"lastname\" : \"V4\",\r\n" + 
				"    \"totalprice\" : 168,\r\n" + 
				"    \"depositpaid\" : true,\r\n" + 
				"    \"bookingdates\" : {\r\n" + 
				"        \"checkin\" : \"2023-01-01\",\r\n" + 
				"        \"checkout\" : \"2023-01-01\"\r\n" + 
				"    },\r\n" + 
				"    \"additionalneeds\" : \"Breakfast\"\r\n" + 
				"}";

		//Generate Response - Post call		
		Response responseCreate = RestClient.postWebServiceCall(baseUri, basePath, userToken, null, payLoad, null, true);
		System.out.println(responseCreate.getBody().asString());

		JsonPath js1 = responseCreate.jsonPath();
		int bookindId = js1.getInt("bookingid"); //Ex-17
		System.out.println("Created New BookingID: "+bookindId);



		//Step3 - Get call using booking id	- Use token and hit Get request	
		String pathParameter = String.valueOf(bookindId);
		String basePathWithPathParameter = "/booking/"+pathParameter;
		//Ex - https://restful-booker.herokuapp.com/booking/17

		Response responseBookingId = RestClient.getWebServiceCall(baseUri, basePathWithPathParameter, userToken, 
				null, true);

		System.out.println(responseBookingId.getBody().asString());
		JsonPath js3 = responseBookingId.jsonPath();


		String firstName = js3.getString("firstname");
		System.out.println("First Name: "+firstName);
		Assert.assertEquals(firstName, "Anand", "Incorrect firstName created");


		//Step4 - Get call using FirstName and LastName	- Use token and hit Get request

		String basePathGet = "/booking";

		//https://restful-booker.herokuapp.com/booking?firstname=Anand&lastname=V
		//Create Query Parameters
		Map<String, String> reqParamsMap = new HashMap<String, String>();
		reqParamsMap.put("firstname", "Anand");
		reqParamsMap.put("lastname", "V4");

		//Without reqparams
		//Response responseGet = RestClient.getWebServiceCall(baseUriGet, basePathGet, userToken, null, true);
		//with requestparams
		Response responseGet = RestClient.getWebServiceCall(baseUri, basePathGet, userToken, reqParamsMap, true);

		System.out.println("Status code of Get call: "+responseGet.getStatusCode());
		System.out.println(responseGet.getBody().asString());

		JsonPath js2 = responseGet.jsonPath();
		int actualBookingId = js2.getInt("[0].bookingid");
		System.out.println("Booking Id: "+actualBookingId);
		Assert.assertEquals(actualBookingId, bookindId, "Incorrect booking id retrieved");

		//How to iterate Array using restassured json path??


	}


	//Generate Oauth2 Token and hit Get request
	//Example API - http://coop.apps.symfonycasts.com/api  (anvardhan551@gmail.com / anvardhan551)
	//Create App and get OAuth2 details
	//Redirect URI - http://naveenautomationlabs.com - NOT required


	//Generate Token For OAuth2
	/*From API Document
	 * POST /token	
	The endpoint used for requesting an access token, using either the authorization_code or client_credentials grant type.

	http://coop.apps.symfonycasts.com/token
	This accepts the following POST fields:

	client_id
	client_secret
	grant_type Either client_credentials or authorization_code
	redirect_uri (authorization_code only) Must match redirect_uri from the original /authorize call
	code (authorization_code only) The authorization code */

	/*String clientId = "MyAPITestApp";
	String clientSecret = "8d74547833f8e989ed03532a13e5e3b9";
	String grantType = "client_credentials";
	String accessTokenUrl = "http://coop.apps.symfonycasts.com/token"; */


	@Test(enabled=false)
	public void validateCoopAppsRetrieveUser_GetCall() {

		//Get API baseURL and BasePath
		String baseURI = "http://coop.apps.symfonycasts.com";
		String basePath = "/api/me";

		//Generate user Token
		String clientId = "MyAPITestApp";
		String clientSecret = "8d74547833f8e989ed03532a13e5e3b9";
		String grantType = "client_credentials";
		String accessTokenUrl = "http://coop.apps.symfonycasts.com/token";

		String tokenID = RestClient.getUserToken_Oatuh2(clientId, clientSecret, grantType, accessTokenUrl);

		//Get request and use token generated above

		Response response = RestClient.getWebServiceCall(baseURI, basePath, tokenID, null, true);
		System.out.println(response.statusLine());
		System.out.println(response.prettyPrint());


		//JSON Validation

	}

	@Test(enabled=false)
	public void validateCoopAppsRetrieveUser_GetCall_WithClientIDInHeader() {

		//Get API baseURL and BasePath
		String baseURI = "http://coop.apps.symfonycasts.com";
		String basePath = "/api/me";

		//Generate user Token
		String clientId = "MyAPITestApp";
		String clientSecret = "8d74547833f8e989ed03532a13e5e3b9";
		String grantType = "client_credentials";
		String accessTokenUrl = "http://coop.apps.symfonycasts.com/token";

		String tokenID = RestClient.getUserToken_Oatuh2(clientId, clientSecret, grantType, accessTokenUrl);

		//Get request and use token generated above

		Response response = RestClient.getWebServiceCall(baseURI, basePath, tokenID, null, clientId, true);
		System.out.println(response.statusLine());
		System.out.println(response.prettyPrint());


		//JSON Validation

	}


	//https://apidocs.imgur.com/
	//create an account - https://imgur.com/register?redirect=https%3A%2F%2Fapi.imgur.com%2Foauth2%2Faddclient
	//https://apidocs.imgur.com/#intro  - Process to Generate Token

	//Step1 - Register An Application and get clientid and client secret
	//username - anvardhan / a011235813
	//AppName - MyImgurApp / MyImgurApplication
	//Callbackurl - https://www.getpostman.com/oauth2/callback

	//clientid - b734c0a6d716389 / 329d07cd94f2064
	//clientsecret - e2c201b9cc996c895bd9ac17588247b7a86433f0 / d5516168200ccdf5a1ebfa366e0393cd5f37e587

	//Step2 - To generate Refresh token - Go to postman - open a empty Get request and Go to Authorization and select OAuth2.0
	//Then provide Token name as Imgur and Grant Type as Authorization code and we will see options as shown in process document
	//Provide info - Callback url, Auth Url, Access Token Url,  Client id, Client secret
	//refresh_token - 00ad832307b13ef96401e9a3b3a74fbecebe24f0

	//Step3 - To Generate Token of Imgur - Provide input into BODYformdata as below and use token as Authorization in header
	/*--form 'refresh_token="{{refreshToken}}"' \
	--form 'client_id="{{clientId}}"' \
	--form 'client_secret="{{clientSecret}}"' \
	--form 'grant_type="refresh_token"' */

	@Test(enabled=false)
	public void validateImagurAccountBlockStatus_GetCall() {

		//API - Account Block status
		//https://api.imgur.com/account/v1/{{username}}/block

		String userName="anvardhan";
		//Get API baseURL and BasePath
		String baseURI = "https://api.imgur.com";
		String basePath = "/account/v1/"+userName+"/block";

		//Generate user Token
		String clientId = "329d07cd94f2064";
		String clientSecret = "d5516168200ccdf5a1ebfa366e0393cd5f37e587";
		String grantType = "refresh_token";
		String accessTokenUrl = "https://api.imgur.com/oauth2/token";
		String refreshToken = "00ad832307b13ef96401e9a3b3a74fbecebe24f0";

		String tokenID = RestClient.getImagurAppToken_Oatuh2(clientId, clientSecret, grantType, accessTokenUrl, refreshToken);
		//System.out.println(tokenID);

		//Hit Get call

		Response response = RestClient.getWebServiceCall(baseURI, basePath, tokenID, null, true);

		System.out.println(response.getStatusLine());
		System.out.println(response.getBody().asString());

	}

	@Test(enabled=false)
	public void validateImagurAccountBlockStatus_GetCall_Approach2() {

		//API - Account Block status
		//https://api.imgur.com/account/v1/{{username}}/block

		String userName="anvardhan";
		//Get API baseURL and BasePath
		String baseURI = "https://api.imgur.com";
		String basePath = "/account/v1/"+userName+"/block";

		//Generate user Token
		String clientId = "329d07cd94f2064";
		String clientSecret = "d5516168200ccdf5a1ebfa366e0393cd5f37e587";
		String grantType = "refresh_token";
		String accessTokenUrl = "https://api.imgur.com/oauth2/token";
		String refreshToken = "00ad832307b13ef96401e9a3b3a74fbecebe24f0";

		//String tokenID = RestClient.getImagurAppToken_Oatuh2_BDD(clientId, clientSecret, grantType, accessTokenUrl, refreshToken);
		Map<Object, Object> tokenMap = RestClient.getAccessToken_entireJSONMap(clientId, clientSecret, grantType, accessTokenUrl, refreshToken);

		System.out.println(tokenMap.toString());

		//String tokenID = (String)tokenMap.get("access_token");
		String tokenID = tokenMap.get("access_token").toString();
		String refreshTokenID = (String)tokenMap.get("refresh_token");
		int accountID = (int)tokenMap.get("account_id");

		//System.out.println(tokenID);
		//System.out.println(refreshTokenID);
		//System.out.println(accountID);
		System.out.println("Token ID using OAuth2 = "+tokenID);

		//Hit Get call

		Response response = RestClient.getWebServiceCall(baseURI, basePath, tokenID, null, true);

		System.out.println(response.getStatusLine());
		System.out.println(response.getBody().asString());

	}
	
	@Test(enabled=false)
	public void validateImagurAccountBase_GetCall() {

		//API - Account Base
		//https://api.imgur.com/3/account/{{username}}

		String userName="anvardhan";
		//Get API baseURL and BasePath
		String baseURI = "https://api.imgur.com";
		String basePath = "/3/account/"+userName;

		//Generate user Token
		String clientId = "329d07cd94f2064";
		String clientSecret = "d5516168200ccdf5a1ebfa366e0393cd5f37e587";
		String grantType = "refresh_token";
		String accessTokenUrl = "https://api.imgur.com/oauth2/token";
		String refreshToken = "00ad832307b13ef96401e9a3b3a74fbecebe24f0";

		//String tokenID = RestClient.getImagurAppToken_Oatuh2(clientId, clientSecret, grantType, accessTokenUrl, refreshToken);
		String tokenID = RestClient.getImagurAppToken_Oatuh2_BDD(clientId, clientSecret, grantType, accessTokenUrl, refreshToken);
		//System.out.println(tokenID);

		//Hit Get call

		Response response = RestClient.getWebServiceCall(baseURI, basePath, tokenID, null, true);

		System.out.println(response.getStatusLine());
		System.out.println(response.getBody().asString());

	}
	
	@Test(enabled=true)
	public void validateImagurAccountImage_GetCall() {

		//API - Account Base
		//https://api.imgur.com/3/account/{{username}}

		String userName="anvardhan";
		//Get API baseURL and BasePath
		String baseURI = "https://api.imgur.com";
		String basePath = "3/account/me/images";

		//Generate user Token
		String clientId = "329d07cd94f2064";
		String clientSecret = "d5516168200ccdf5a1ebfa366e0393cd5f37e587";
		String grantType = "refresh_token";
		String accessTokenUrl = "https://api.imgur.com/oauth2/token";
		String refreshToken = "00ad832307b13ef96401e9a3b3a74fbecebe24f0";

		//String tokenID = RestClient.getImagurAppToken_Oatuh2(clientId, clientSecret, grantType, accessTokenUrl, refreshToken);
		String tokenID = RestClient.getImagurAppToken_Oatuh2_BDD(clientId, clientSecret, grantType, accessTokenUrl, refreshToken);
		//System.out.println(tokenID);

		//Hit Get call

		Response response = RestClient.getWebServiceCall(baseURI, basePath, tokenID, null, true);

		System.out.println(response.getStatusLine());
		System.out.println(response.getBody().asString());

	}







}
