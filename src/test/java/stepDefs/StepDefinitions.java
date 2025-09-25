package stepDefs;

import io.cucumber.java.en.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import resources.TestDataBuild;

import static io.restassured.RestAssured.given;

public class StepDefinitions {
    Response response;
    RequestSpecification reqSpec;
    ResponseSpecification resSpec;
    TestDataBuild testDataBuild=new TestDataBuild();
    String baseURI = "https://rahulshettyacademy.com";
    @Given("Add Place Payload")
    public void add_place_payload() {
        RequestSpecification spec=new RequestSpecBuilder().setBaseUri(baseURI).addQueryParam("key","qaclick123").setContentType(ContentType.JSON).build();
        resSpec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        reqSpec=given().log().all().spec(spec).body(testDataBuild.getAddPlace());
    }
    @When("user calls {string} with POST Http request")
    public void user_calls_with_post_http_request(String string) {
        if(string.equalsIgnoreCase("AddPlaceAPI")) {
            response = reqSpec.when().post("/maps/api/place/add/json").then().spec(resSpec).extract().response();
        }
    }
    @Then("the API call is successfully with status code {int}")
    public void the_api_call_is_successfully_with_status_code(int int1) {
        Assert.assertEquals(response.getStatusCode(),int1);
    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String expectedValue) {
     String res=response.asString();
     Assert.assertEquals(new JsonPath(res).getString(keyValue),expectedValue);
    }
}
