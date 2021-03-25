package pages;

import io.restassured.response.Response;
import spec.RequestSpec;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class ArtistClass extends RequestSpec {
// İstenilen ek caselerden biri
    public void getArtist(String artistID) {
        Response response=
                given()
                        .spec(super.getRequestSpecification())
                        .when()
                        .get("/artists/{id}",artistID)
                        .then()
                        .extract().response();
        assertThat("Status code kontrolu 200 olmali", response.getStatusCode(), equalTo(200));
        response.prettyPrint();






    }

}
