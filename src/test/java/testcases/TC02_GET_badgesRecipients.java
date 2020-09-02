package testcases;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC02_GET_badgesRecipients {
	Response response;
	
	@BeforeTest
	public void setup() {
		//specify base URI 
		RestAssured.baseURI="https://api.stackexchange.com";	
		
		//Request Object 
		RequestSpecification httpRequest = RestAssured.given();
		
		//Response Object 
	    response = httpRequest.queryParam("site", "stackoverflow").get("2.2/badges/recipients");				

	}
	
	@Test(priority=1)
	public void verifyStatusCodeAndStatusLine() {
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode,200);
		
		String statusLine = response.getStatusLine();
		Assert.assertEquals(statusLine,"HTTP/1.1 200 OK");	
		
		
		
	}
	
	@Test(priority=2)
	public void verifyItemsNotNull()
	{
		 
		ArrayList<String> items = response.jsonPath().get("items");
		Assert.assertNotNull(items);
	}

}
