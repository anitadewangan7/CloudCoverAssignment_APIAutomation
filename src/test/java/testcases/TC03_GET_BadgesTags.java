package testcases;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC03_GET_BadgesTags {
Response response;
	
	@BeforeTest
	public void setup() {
		//specify base URI 
		RestAssured.baseURI="https://api.stackexchange.com";	
		
		//Request Object 
		RequestSpecification httpRequest = RestAssured.given();
		
		//Response Object 
	    response = httpRequest
	    		.queryParam("order", "desc")
				.queryParam("sort","rank")
				.queryParam("site","stackoverflow")
				.get("2.2/badges/tags");				

	}
	
	@Test(priority=1)
	public void verifyStatusCodeAndStatusLine() {
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode,200);
		
		String statusLine = response.getStatusLine();
		Assert.assertEquals(statusLine,"HTTP/1.1 200 OK");		
		
		
	}

	
	@Test(priority=2)
	public void verifyTagbasedBadge() {
		
		ArrayList<LinkedHashMap> items = response.jsonPath().get("items");
		for(int i=0;i<items.size();i++)
		{
			LinkedHashMap<String,String> map = items.get(i);
			Assert.assertEquals(map.get("badge_type"), "tag_based");
		}
		
	}

}
