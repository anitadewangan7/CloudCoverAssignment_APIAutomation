package testcases;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC01_GET_BadgesIDsAPI {
	RequestSpecification httpRequest;
	 Response response;
	 
	@BeforeTest
	public void getBadegsDetailsforIds() {
		
		//specify base URI 
			RestAssured.baseURI="https://api.stackexchange.com";

		//Request Object 
		
		
	}
	
	@Test(dataProvider="TestData")
	public void verifyStatusCode(String data)
	{
		httpRequest = RestAssured.given();		
		response = httpRequest
				.pathParam("ids",data)
				.queryParam("order", "desc")
				.queryParam("sort","rank")
				.queryParam("site","stackoverflow")
				.get("2.2/badges/{ids}");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode,200);

		
	}
	
	@DataProvider(name="TestData")
	public Object[] getIds() {
		
		Object testdata[] = {"12","13;14"};
		return testdata;
		
	}
	
	

}
