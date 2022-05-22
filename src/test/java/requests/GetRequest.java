package requests;

import io.restassured.response.Response;
import utilities.SetProperties;
import static io.restassured.RestAssured.given;

public class GetRequest {

    private String token_path = "/authentication/token/new";
    private SetProperties properties = new SetProperties();
    private String request_token;


    public Response generateToken(){

        Response response = given()
                .queryParam("api_key",properties.getApi_key())
                .when()
                .get(properties.getBase_url()+ token_path)
                .then()
                .statusCode(200)
                .extract().
                response();

        setRequest_token(response.jsonPath().getString("request_token"));
        return response;
    }

    public String getRequest_token() {
        return request_token;
    }

    public void setRequest_token(String request_token) {
        this.request_token = request_token;
    }
}
