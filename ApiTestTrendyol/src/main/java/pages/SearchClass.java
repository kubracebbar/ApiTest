package pages;

import io.restassured.internal.RestAssuredResponseImpl;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;
import spec.RequestSpec;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class SearchClass extends RequestSpec {

    public List searchSomething(){
Response response=
        given()
                .spec(super.getRequestSpecification())
                .queryParam("q","London Grammar")
                .queryParam("type","track")
                .queryParam("limit","3")
                .when()
                .get("/search")
                .then()
                .extract().response();
       // assertThat("Status code kontrolu 200 olmali", response.getStatusCode(), equalTo(200));

      //  response.prettyPrint();
        List<Object> a  =((RestAssuredResponseImpl) response).response().path("tracks.items.uri");
   // System.out.println(a);
//        System.out.println(a.get(0));
//        System.out.println(a.get(1));
//        System.out.println(a.get(2));
       return a;

    }
    public String searchArtist(){

        Response response=
                given()
                        .spec(super.getRequestSpecification())
                        .queryParam("q","London Grammar")
                        .queryParam("type","track")
                        .queryParam("limit","3")
                        .when()
                        .get("/search")
                        .then()
                        .extract().response();
        String b  =((RestAssuredResponseImpl) response).response().path("artists.items.id");
        return b;
    }





}
