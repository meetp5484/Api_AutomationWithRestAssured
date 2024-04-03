package org.example;


import io.restassured.http.ContentType;
import org.testng.annotations.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

public class BasicTest {
    @BeforeMethod
    public void setup() {
        baseURI = "https://automationexercise.com/api";
    }

    @Test(priority = 2)
    public void APIGetProductsList() {
        given().get("/productsList").then().statusCode(200)
//                .body("products[0].id", equalTo(1))
                .header("Content-Type", "text/html; charset=utf-8")
                .time(lessThan(2000L)).log().all();
    }
    @Test(priority = 2)
    public void ApiPOSTProductList() {

        given().
                post("/productsList")
                .then().statusCode(405)
                .header("Content-Type", "text/html; charset=utf-8")
                .header("Connection", "keep-alive")
                .header("status", "200 OK")
//                .body("data.items", notNullValue())
                .log().all();

    }

    @Test(priority = 2)
    public void apiGETBrandsList() {
        given().get("/brandsList")
                .then()
                .statusCode(200)
                .header("Content-Type", "text/html; charset=utf-8")
                .header("Connection", "keep-alive")
//                .body("brands[0].id", equalTo(1))
//                .body("brands[0].brand", equalTo("H&M"))
                .log().all();

    }

    @Test(priority = 1)
    public void apiPUTBrandsList() {

        given().contentType("application/json") // Set content type as JSON
                .when()
                .put("/brandsList")
                .then()
                .statusCode(405)
                .header("Content-Type", "text/html; charset=utf-8")
                .header("Connection", "keep-alive")
//                .body("brands[0].id", equalTo(1))
//                .body("brands[0].brand", equalTo("H&M"))
                .log().all();
    }

    @Test(priority = 1)
    public void apiPOSTSearchProductWithParameter() {
        String reqBody = "{\\\"search_product\\\": \\\"tshirt\\\"}";
        given().contentType("application/json")
                .body(reqBody)
                .when()
                .post("/searchProduct")
                .then()
                .statusCode(200)
                .header("Content-Type", "text/html; charset=utf-8")
                .time(lessThan(2000L))
                .header("status", equalTo("200 OK"));
//                .body("products[0].id" ,equalTo("2"));                ;
    }

    @Test(priority = 1)
    public void apiPOSTSearchProductWithoutParameter() {
        String reqBody = "";

        given().contentType("application/json")
//                .body(reqBody)    without paramter
                .when()
                .post("/searchProduct")
                .then()
                .statusCode(400);
//                .body("responseCode", equalTo(400))
//                .body("message", equalTo("Bad request, search_product parameter is missing in POST request"));

    }

    @Test(priority = 1)
    public void verifyLoginWithDetails() {
        String reqBody = "{\\\"email\\\": \\\"meetp5484@gmail.com\\\", \\\"password\\\": \\\"Computer@1\\\"}";
        given().contentType("application/json")
                .body(reqBody)
                .when()
                .post("verifyLogin")
                .then()
                .statusCode(200)
                .header("Content-Type", "text/html; charset=utf-8")
                .header("status", equalTo("200 OK"));
//                .body("message", equalTo("User Created"));
    }

    @Test(priority = 2)
    public void verifyLoginWithoutEmail() {
        String reqbody = "{\"password\": \"computer@123\"}";

        given().contentType("application/json")
                .when()
                .post("verifyLogin")
                .then()
                .statusCode(400)
                .header("Content-Type", "text/html; charset=utf-8")
                .header("status", "400 Bed Request");
//                .body("responseCode", equalTo(400))
//                .body("message", equalTo("Bad request, email or password parameter is missing in POST request."));
    }


}



