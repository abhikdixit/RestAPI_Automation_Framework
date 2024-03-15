package com.Base64.tests;

import java.util.Base64;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Base64_API_Example {
	
    public static String username = "postman";
    public static String password = "password";
    public static String authorization;

    public static String encode(String str1, String str2) {
        return new String(Base64.getEncoder().encode((str1 + ":" + str2).getBytes()));
    }

    public static String getCode() {
        authorization = encode(username, password);
		return authorization;

    }

    @Test
    public void GET_GetDefaultCountry() {
    	 System.out.println("Code is =>  " + getCode());
        RestAssured.baseURI = "https://postman-echo.com";
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("authorization", "Basic " + getCode());
        Response response = httpRequest.request(Method.GET,"/basic-auth");

          // Now let us print the body of the message to see what response
        // we have recieved from the server
        String responseBody = response.getBody().asString();
        System.out.println("Response Body is =>  " + responseBody);
        // Status Code Validation
        int statusCode = response.getStatusCode();
        System.out.println("Status code is =>  " + statusCode);
        Assert.assertEquals(200, statusCode);

       
    }
}