package org.example;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class reqresWeb {


    @BeforeMethod
    public void setupReqRes(){

        baseURI baseUri = new baseURI();
        baseUri.setUPReqres();

    }
    @Test(priority = 1)
    public void reqresPOSTMethod() {
        String reqBody = "{\"name\": \"morpheus\",\"job\": \"leader\"}";
        given()
                .body(reqBody)
                .contentType(ContentType.XML)
                .accept(ContentType.XML)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .header("Content-Type", "application/json; charset=utf-8")
//                .body("user.name", equalTo("morpheus"))
//                .body("user.job", equalTo("leader"))
                .log().all();

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
        String reqbody = "<user>" +
                "<name>morpheus</name>" +
                "<job>zion resident</job>" +
                "</user>";
        given().contentType(ContentType.XML)
                .body(reqbody)
                .when()
                .post("/users/2")
                .then()
                .statusCode(201)
                .header("Content-Type", "application/json; charset=utf-8")
                .body(not(isEmptyString()))
                ;
    }

    @Test(priority = 1)
    public void reqresPATCHMethod() {
        String reqbody = "{\n" +
                "    \"job\": \"zion resident\"\n" +
                "}";
        given().contentType("application/json")
                .body(reqbody)
                .when()
                .patch("/users/2")
                .then()
                .statusCode(200)
                .header("Content-Type", "application/json; charset=utf-8")
                .body("job", equalTo("zion resident"));
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
