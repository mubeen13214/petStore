/**
 * 
 */
package com.testPages;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import utilities.Log;
import utilities.TestBase;

public class Petstore extends TestBase {

	String url = "http://petstore.swagger.io/#/";

	String getPetURL="https://petstore.swagger.io/v2/pet/1";
	String deletePetUrl="https://petstore.swagger.io/v2/pet/1";
	String addPetUrl="https://petstore.swagger.io/v2/pet";
	String updatePetUrl="https://petstore.swagger.io/v2/pet/1";


	@Test(enabled = true, priority = 1)

	public void validateAddPet() throws FileNotFoundException, ParseException {

        @SuppressWarnings("deprecation")
		Object obj = new JSONParser().parse(new FileReader("C:\\Users\\Mmubeen\\git\\RestAPIAutomation\\src\\test\\java\\com\\testPages\\petdata.json")); 
		Response resp1 = RestAssured.given().contentType("application/json").body(obj.toString()).post(addPetUrl);
		String response=resp1.getBody().prettyPrint();
		Log.info("Pet details received from server is : "+response);
		Assert.assertTrue(resp1.getStatusCode()==200);
		System.out.println("Status code is  :" +resp1.getStatusCode());
		
	}
	
	@Test(enabled = true, priority = 2)

	public void validateGetPet() {

		Response resp1 = RestAssured.given().get(getPetURL);
		String response=resp1.getBody().prettyPrint();
		Log.info("Pet details received from server is : "+response);
		Assert.assertTrue(resp1.getStatusCode()==200);
		Log.info("Status code is  :" +resp1.then().extract().jsonPath().get("status").toString());
		
	}

	@Test(enabled = true, priority = 3)

	public void validateDeletePet() {

		Response resp1 = RestAssured.given().post(deletePetUrl);
		String response=resp1.getBody().prettyPrint();
		Log.info("Pet details received from server is : "+response);
		Assert.assertTrue(resp1.getStatusCode()==200);
		Log.info("Status code is  :" +resp1.getStatusCode());
		Log.info("Pet deleted successfully..");
		
	}

	@Test(enabled = true, priority = 4)

	public void validateUpdatePet() {

		Response resp1 = RestAssured.given().formParameter("name", "Mubeen").post(updatePetUrl);
		String response=resp1.getBody().prettyPrint();
		Log.info("Pet details received from server is : "+response);
		Assert.assertTrue(resp1.getStatusCode()==200);
		Log.info("Status code is  :" +resp1.getStatusCode());
		Log.info("Pet updated successfully..");
		
	}
	
}

