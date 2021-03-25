package pages;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import spec.RequestSpec;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsEqual.equalTo;

public class UserClass extends RequestSpec {

    public String getUser(){
        Response response=
                given()
                        .spec(super.getRequestSpecification())
                        .when()
                        .get("/me")
                        .then()
                        .extract().response();
        assertThat("Status code kontrolu 200 olmali", response.getStatusCode(), equalTo(200));
        assertThat("id kontrolu ", response.getBody().jsonPath().getString("id"), not((equalTo(null))));
       return response.getBody().jsonPath().getString("id");
    }
    public void getUserProfile(String userId){

        Response response=
                given()
                       .spec(super.getRequestSpecification())
                        .when()
                        .get("/users/{user_id}",userId)
                        .then()
                        .extract().response();
        assertThat("Status code kontrolu 200 olmali", response.getStatusCode(), equalTo(200));
    }



}
