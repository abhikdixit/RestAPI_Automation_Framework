package com.Base64.tests;

import java.util.Base64;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Base64_Example {
	
    public static String username = "postman";
    public static String password = "password";

    public static String encode(String str1, String str2) {
        return new String(Base64.getEncoder().encode((str1 + ":" + str2).getBytes()));
    }

    public static Response getCode() {
        String authorization = encode(username, password);

        return
                given()
                        .header("authorization", "Basic " + authorization)
                        .contentType(ContentType.ANY)
                        .get("/basic-auth")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();
    }

    public static String parseForOAuth2Code(Response response) {
        return response.jsonPath().toString();
    }

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "https://postman-echo.com";
    }

    @Test
    public void iShouldGetCode() {
        Response response = getCode();
        String code = parseForOAuth2Code(response);
        System.out.println(code);
        //Assert.assertNotNull(code);
    }
}