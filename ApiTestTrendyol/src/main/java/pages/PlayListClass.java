package pages;

import com.google.common.io.Resources;
import io.restassured.RestAssured;
import io.restassured.internal.http.HttpResponseException;
import io.restassured.response.Response;
import org.json.JSONObject;
import spec.RequestSpec;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsEqual.equalTo;


public class PlayListClass extends RequestSpec {

    public String createPlayList(String userId,String key,String value) throws IOException {

        URL file = Resources.getResource("playlist.json");//jsonı okunabilir hale getiriyor
        String playListJson = Resources.toString(file, Charset.defaultCharset());//içidenkileri stringe ceviriyor.
        JSONObject json = new JSONObject(playListJson);// jsonobject metodları için cevirdik.
        json.put(key, value);
        json.put("description", "deneme");
        json.put("public","true");
        Response response =
                given()
                        .spec(super.getRequestSpecification())
                        .body(json.toString())
                        .when()
                        .post("/users/{user_id}/playlists", userId)
                        .then()
                        .extract().response();
        //response.prettyPrint();
        assertThat("Status code kontrolu 201 olmali", response.getStatusCode(), equalTo(201));
        assertThat("id kontrolu ", response.getBody().jsonPath().getString("id"), not((equalTo(null))));
        return response.jsonPath().getString("id");
    }
    public void controlPlayListIsEmpty(String playListId){
        Response response =
                given()
                        .spec(super.getRequestSpecification())
                        .when()
                        .get("/playlists/{playlist_id}/tracks", playListId)
                        .then()
                        .statusCode(200)
                        .extract().response();
                         response.prettyPrint();
    }

        public void addPlayListTracks(String playListId, List<Object> tracks){
                  for(Object track: tracks)
                  {
                      given()
                            .spec(super.getRequestSpecification())
                            .queryParam("uris",track)
                            .when()
                            .post("/playlists/{playlist_id}/tracks",playListId)
                            .then()
                            .statusCode(201)
                            .extract().response();
    }}
        public void updatePlayLists(String playListId,String key,String value) throws IOException {

        URL file = Resources.getResource("updatePlaylist.json");//jsonı okunabilir hale getiriyor
            String updatePlayListJson = Resources.toString(file, Charset.defaultCharset());//içidenkileri stringe ceviriyor.
            JSONObject json = new JSONObject(updatePlayListJson);// jsonobject metodları için cevirdik.
            json.put(key,value);
            Response response=
                    given()
                            .spec(super.getRequestSpecification())
                            .body(json.toString())
                            .when()
                            .put("/playlists/{playlist_id}",playListId)
                            .then()
                            .extract().response();
            assertThat("Status code kontrolu 200 olmali", response.getStatusCode(), equalTo(200));

        }
        public void deletePlayLists(String playListId,Object deleteTracksID) throws IOException {
            URL file = Resources.getResource("delete.json");//jsonı okunabilir hale getiriyor
            String deletePlayListJson = Resources.toString(file, Charset.defaultCharset());//içidenkileri stringe ceviriyor.
            JSONObject deletejson = new JSONObject(deletePlayListJson);// jsonobject metodları için cevirdik.
            deletejson.put("uri",deleteTracksID);
        Response response=
                    given()
                            .spec(super.getRequestSpecification())
                            .body(deletejson.toString())
                            .when()
                            .delete("/playlists/{playlist_id}/tracks",playListId)
                            .then()
                            .statusCode(200)
                            .extract().response();
                            response.prettyPrint();


        }
    public void getPlayListThatDoesNotExist(String playListId) throws  IOException{
        given()
                .contentType("application/json; charset=UTF-8")
                .when()
                .get("/playlists/{playlist_id}/tracks",playListId)
                .then()
                .statusCode(404);
    }
    public void validatePlayListDeletion(String playListId) throws  IOException {
        try {
            getPlayListThatDoesNotExist(playListId);
        } catch (HttpResponseException ex) {
            assert ex.getStatusCode() == 404;
        }

    }



}
