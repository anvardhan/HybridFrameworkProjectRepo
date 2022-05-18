package com.qa.api.tests;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.app.utils.CommonUtilities;
import com.qa.app.utils.RestClient;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import pojo.Address_pojoexample1;
import pojo.Booking;
import pojo.BookingDates;
import pojo.UserData_pojoexample1;
import pojo.UserData_pojoexample2;
import pojo.Users;


public class JsonPayloadExamples {

	//Examples from below
	//1 - Using POJO - https://www.youtube.com/watch?v=IRZuCxzXG1o&list=PL-a9eJ2NZlbT0Hoo_Hj43utwgq2VusPyN&index=48
	//2 - Using Map - https://www.youtube.com/watch?v=4NfbbHQtGmo&list=PL-a9eJ2NZlbT0Hoo_Hj43utwgq2VusPyN&index=34
	//3 - Read Json payload file and no update in file
	//4 - Read sample Json file and update values at run time - https://www.youtube.com/watch?v=118aTRfEmeY&list=PL-a9eJ2NZlbT0Hoo_Hj43utwgq2VusPyN&index=58
	//5 - Read Nested Json file and update content - without Jackson

	//1- Pojo

	//Create class for each curly bracket content if they contain separate info. 
	//Each smallest unit should be created 1st and then merge it to outer layer


	//Example1:
	/*{
		  "firstname" : "Anand",
		  "lastname" : "Vardhan",
		  "professional" : "Software Engineer",
		  "phone" : "5463746574",
		  "status" : "Active",
		  "available" : true,
		  "address" : {
		    "houseNo" : 202,
		    "city" : "Chicago",
		    "state" : "Illinois",
		    "zip" : 65898,
		    "country" : "USA",
		    "street1" : "86 Chicago dr"
		  }
		}*/
	//Create json payload having single address with multiple attribute and here address is json object which is 
	//nested to parent json object - Two class required	
	@Test(enabled=false) 
	public void createJsonPayload_NestedJsonObject() throws StreamWriteException, DatabindException, IOException {

		//Child Pojo Class that process small unit which is Address
		Address_pojoexample1 address = new Address_pojoexample1(202, "86 Chicago dr", "Chicago", "Illinois", 65898, "USA");		

		//Parent Pojo class that combines all unites		
		UserData_pojoexample1 ud = new UserData_pojoexample1("Anand", "Vardhan", "Software Engineer", "5463746574", 
				"Active", true, address);


		String userJson_SingleAddress = CommonUtilities.createJsonPayload(ud);
		System.out.println("User info JSON payload: \n"+userJson_SingleAddress);		

	}


	//Example2:
	/*{
		  "firstname": "Anand",
		  "lastname": "Vardhan",
		  "professional": "Software Engineer",
		  "phone": "5463746574",
		  "status": "Active",
		  "available": true,
		  "address": [
		    {
		      "houseNo": 202,
		      "city": "Chicago",
		      "state": "Illinois",
		      "zip": 65898,
		      "country": "USA",
		      "street1": "86 Chicago dr"
		    },
		    {
		      "houseNo": 528,
		      "city": "Downers Grove",
		      "state": "Illinois",
		      "zip": 65898,
		      "country": "95865",
		      "street1": "584 Downers Grove"
		    }
		  ]
		}*/
	//Create json payload having json array with multiple entry	of address. address is nested json object and multiple 
	//address objects are stored in json array
	@Test(enabled=false) 
	public void createJsonPayload_NestedJsonObjectWithJsonArray() throws StreamWriteException, DatabindException, IOException {

		//Child Pojo Class that process small unit which is Address
		Address_pojoexample1 address1 = new Address_pojoexample1(202, "86 Chicago dr", "Chicago", "Illinois", 65898, "USA");		
		Address_pojoexample1 address2 = new Address_pojoexample1(528, "584 Downers Grove", "Downers Grove", "Illinois", 65898, "95865");

		//combine address into list as address is of type json Array in payload.
		List<Address_pojoexample1> allAddress = new ArrayList<>();
		allAddress.add(address1);
		allAddress.add(address2);

		//Parent Pojo class that combines all unites		
		UserData_pojoexample2 ud = new UserData_pojoexample2("Anand", "Vardhan", "Software Engineer", "5463746574", 
				"Active", true, allAddress);


		String userJson = CommonUtilities.createJsonPayload(ud);
		System.out.println("User info JSON payload: \n"+userJson);		

	}


	//Example3:
	/*[
	  {
	    "firstname": "Anand",
	    "lastname": "Vardhan",
	    "professional": "Software Engineer",
	    "phone": "5463746574",
	    "status": "Active",
	    "available": true,
	    "address": [
	      {
	        "houseNo": 202,
	        "city": "Chicago",
	        "state": "Illinois",
	        "zip": 65898,
	        "country": "USA",
	        "street1": "86 Chicago dr"
	      },
	      {
	        "houseNo": 528,
	        "city": "Downers Grove",
	        "state": "Illinois",
	        "zip": 65898,
	        "country": "95865",
	        "street1": "584 Downers Grove"
	      }
	    ]
	  },
	  {
	    "firstname": "Anand",
	    "lastname": "Vardhan",
	    "professional": "Software Engineer",
	    "phone": "5463746574",
	    "status": "Active",
	    "available": true,
	    "address": [
	      {
	        "houseNo": 202,
	        "city": "Chicago",
	        "state": "Illinois",
	        "zip": 65898,
	        "country": "USA",
	        "street1": "86 Chicago dr"
	      },
	      {
	        "houseNo": 528,
	        "city": "Downers Grove",
	        "state": "Illinois",
	        "zip": 65898,
	        "country": "95865",
	        "street1": "584 Downers Grove"
	      }
	    ]
	  }
	]*/

	//Create json payload having json array which contains multiple json objects.
	@Test(enabled=false) 
	public void createJsonPayload_JsonObjectsInsideParentJsonArray() throws StreamWriteException, DatabindException, IOException {

		//Child Pojo Class that process small unit (address) for 1st json object in the parent class
		Address_pojoexample1 address1 = new Address_pojoexample1(202, "86 Chicago dr", "Chicago", "Illinois", 65898, "USA");		
		Address_pojoexample1 address2 = new Address_pojoexample1(528, "584 Downers Grove", "Downers Grove", "Illinois", 65898, "95865");

		//combine address into list as address is of type json Array in payload.
		List<Address_pojoexample1> allAddress1 = new ArrayList<>();
		allAddress1.add(address1);
		allAddress1.add(address2);


		//Child Pojo Class that process small unit (address) for 2nd json object in the parent class
		Address_pojoexample1 address3 = new Address_pojoexample1(202, "86 Chicago dr", "Chicago", "Illinois", 65898, "USA");		
		Address_pojoexample1 address4 = new Address_pojoexample1(528, "584 Downers Grove", "Downers Grove", "Illinois", 65898, "95865");

		//combine address into list as address is of type json Array in payload.
		List<Address_pojoexample1> allAddress2 = new ArrayList<>();
		allAddress2.add(address3);
		allAddress2.add(address4);

		//Parent Pojo class that contains 1st json object
		UserData_pojoexample2 ud1 = new UserData_pojoexample2("Anand", "Vardhan", "Software Engineer", "5463746574", 
				"Active", true, allAddress1);

		//Parent Pojo class that contains 2nd json object
		UserData_pojoexample2 ud2 = new UserData_pojoexample2("Anand1", "Vardhan1", "Software Engineer1", "6463746574", 
				"InActive", false, allAddress2);

		//combine both json object into json Array
		List<UserData_pojoexample2> ud = new ArrayList<>();
		ud.add(ud1);
		ud.add(ud2);




		String userJson = CommonUtilities.createJsonPayload(ud);
		System.out.println("User info JSON payload: \n"+userJson);		

	}



	//Example4 - Read simple json at run time and update content - use Jackson

	@Test(enabled=false) 
	public void createJsonPayload_ReadJsonAndUpdateValue() throws StreamWriteException, DatabindException, IOException {

		//use JSON payload from File

		File file = new File(".\\src\\test\\resources\\payloads\\userCreate.json");	

		ObjectMapper mapper = new ObjectMapper();
		Map<String,Object> user = mapper.readValue(file, new TypeReference<Map<String,Object>>() {});

		System.out.println(user.get("name"));
		System.out.println(user.get("email"));

		String userJson_Sample = CommonUtilities.createJsonPayload(user);
		System.out.println("User info JSON payload: \n"+userJson_Sample);

		user.put("name", "Anand59");
		user.put("email", "anand58@gmail.com");

		String userJson_updated = CommonUtilities.createJsonPayload(user);
		System.out.println("User info JSON payload: \n"+userJson_updated);

	}


	//Example5 - Read Nested json at run time and update content - Without Jackson
	//Sample JSON
	/*{
		"firstname": "Anand",
		"lastname": "Vardhan",
		"professional": "Software Engineer",
		"phone": "5463746574",
		"status": "Active",
		"available": true,
		"address": {
			"houseNo": 202,
			"city": "Chicago",
			"state": "Illinois",
			"zip": 65898,
			"country": "USA",
			"street1": "86 Chicago dr"
		}
	}*/
	
	//Sample JSON - Make it generalize and have variables for values of attributes, which we can replace during run time 
	/*{
		"firstname": "Anand",
		"lastname": "LASTNAME", //Here LASTNAME is variable for value of lastname attribute
		"professional": "Software Engineer",
		"phone": "5463746574",
		"status": "Active",
		"available": true,
		"address": {
			"houseNo": 202,
			"city": "CITY",
			"state": "STATE",
			"zip": 65898,
			"country": "USA",
			"street1": "86 Chicago dr"
		}
	}*/

	@Test(enabled=true) 
	public void createJsonPayload_ReadNestedJsonAndUpdateValue_withoutJacksonAPI() throws StreamWriteException, DatabindException, IOException {

		//use JSON payload from sample File - Read sample json and replace variables during run time

		//File file = new File(".\\src\\test\\resources\\payloads\\userNestedData.json");	

		String file = ".\\src\\test\\resources\\payloads\\userNestedSampleData.json";

		String updatedJsonString;  
		// we use the get() method of Paths to get the file data  
		// we use readAllBytes() method of Files to read byted data from the files  
		updatedJsonString = new String(Files.readAllBytes(Paths.get(file)));  
		//System.out.println(sampleJsonString);		
		String lastName = "Vardhan";
		String city = "Chicago";
		String state = "Illinois";
		
		updatedJsonString = updatedJsonString.replace("LASTNAME", lastName).replace("CITY", city).replace("STATE", state);
		
		System.out.println(updatedJsonString);
		
		updatedJsonString = new String(Files.readAllBytes(Paths.get(file)));
		lastName = "Robert";
		city = "Downers Grove";
		state = "Illinois";
		
		updatedJsonString = updatedJsonString.replace("LASTNAME", lastName).replace("CITY", city).replace("STATE", state);
		
		System.out.println(updatedJsonString);
		
		

		//String userUpdatedJson = CommonUtilities.createJsonPayload(ud);
		//System.out.println("User info JSON payload: \n"+userUpdatedJson);



	}
}
