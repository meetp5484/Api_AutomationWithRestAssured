package org.example;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isEmptyString;

public class reqresWeb {


    @BeforeMethod
    public void setupReqRes(){
        baseURI = "https://reqres.in/api";
    }
    @Test(priority = 1)
    public void reqresPOSTMethod() {
        String reqbody = "{\"name\": \"morpheus\",\"job\": \"leader\"}";
        given().contentType("application/json")
                .body(reqbody)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .header("Content-Type", "application/json; charset=utf-8");
//                .body("name", equalTo("morpheus"))
//                .body("job", equalTo("leader"));
    }

    @Test(priority = 2)
    public void testGETOneDetail(){
        given().get("/users/2").then()
                .statusCode(200)
                .header("Content-Type", "application/json; charset=utf-8")
//                .body("data.id",equalTo(2))
//                .body("data.email", equalTo("janet.weaver@reqres.in"))
                .log().all();

    }
    @Test(priority = 1)
    public void reqresPUTMethod() {
        String reqbody = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}";
        given().contentType("application/json")
                .body(reqbody)
                .when()
                .post("/users/2")
                .then()
                .statusCode(200)
                .header("Content-Type", "application/json; charset=utf-8");
//                .header("status", equalTo("200 OK"))
//                .body("name", equalTo("morpheus"))
//                .body("job", equalTo("zion resident"));
    }
    @Test
    public void reqresDELETEMethod(){
        given().delete("users/2")
                .then()
                .statusCode(204)
                .header("Content-Length", equalTo("0"));
//                .body(isEmptyString());


    }
}
