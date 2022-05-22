package requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import utilities.SetProperties;

import static io.restassured.RestAssured.given;

public class PostRequest {
    private JSONObject jsonObject = new JSONObject();
    private SetProperties properties = new SetProperties();

    private String validateToken_path = "/authentication/token/validate_with_login";
    private String createSession_path = "/authentication/session/new";
    private String list_path = "/list";

    private String request_token;
    private String session_id;
    private String list_id;



    public Response validateToken(String request_token){

        jsonObject
                .put("username",properties.getUser())
                .put("password",properties.getPassword())
                .put("request_token",request_token);

        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("api_key",properties.getApi_key())
                .body(jsonObject.toString())
                .when()
                .post(properties.getBase_url() + validateToken_path)
                .then()
                .statusCode(200)
                .extract().response();

        //set request token
        setRequest_token(response.jsonPath().getString("request_token"));
        return response;

    }

    public Response createSession(String request_token){

        jsonObject
                .put("request_token",request_token);

        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("api_key",properties.getApi_key())
                .body(jsonObject.toString())
                .when()
                .post(properties.getBase_url() + createSession_path)
                .then()
                .statusCode(200)
                .extract()
                .response();

        //set session id
        setSession_id(response.jsonPath().getString("session_id"));
        return response;

    }

    public Response createList(String session_id,String listName, String listDescription, String listLanguage){

        jsonObject
                .put("name",listName)
                .put("description",listDescription)
                .put("language",listLanguage);
        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("api_key",properties.getApi_key())
                .queryParam("session_id",session_id)
                .body(jsonObject.toString())
                .when()
                .post(properties.getBase_url() + list_path)
                .then()
                .statusCode(201)
                .extract().response();

        //set session id
        setList_id(response.jsonPath().getString("list_id"));

        return response;

    }

    public Response addMovie(String session_id,int idMovie, String idList){

        jsonObject
                .put("media_id",idMovie);

        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("api_key",properties.getApi_key())
                .queryParam("session_id",session_id)
                .body(jsonObject.toString())
                .when()
                .post(properties.getBase_url() + list_path + "/"+ idList + "/add_item")
                .then()
                .statusCode(201)
                .extract().response();

        return response;

    }

    public Response deleteMovie(String session_id, int idMovie, String idList){

        jsonObject
                .put("media_id",idMovie);
        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("api_key",properties.getApi_key())
                .queryParam("session_id",session_id)
                .body(jsonObject.toString())
                .when()
                .post(properties.getBase_url()+ list_path + "/"+ idList +"/remove_item")
                .then()
                .statusCode(200)
                .extract().response();

        return response;

    }

    public String getRequest_token() {
        return request_token;
    }

    public void setRequest_token(String request_token) {
        this.request_token = request_token;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public String getList_id() {
        return list_id;
    }

    public void setList_id(String list_id) {
        this.list_id = list_id;
    }



}
