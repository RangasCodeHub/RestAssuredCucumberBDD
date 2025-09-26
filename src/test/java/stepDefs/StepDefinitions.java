package stepDefs;

import io.cucumber.java.en.*;
import io.opentelemetry.api.common.Value;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utility;

import java.io.FileNotFoundException;

import static io.restassured.RestAssured.given;

public class StepDefinitions extends Utility {
    Response response;
    RequestSpecification reqSpec;
    ResponseSpecification resSpec;
    TestDataBuild testDataBuild = new TestDataBuild();

    @Given("Add Place Payload with {string} {string} {string}")
    public void add_place_payload(String name, String language, String address) throws FileNotFoundException {
        reqSpec = given().log().all().spec(getReqSpec()).body(testDataBuild.getAddPlace(name, language, address));
    }

    @When("user calls {string} with {string} Http request")
    public void user_calls_with_post_http_request(String resource, String httpMethod) {
        resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        APIResources resources = APIResources.valueOf(resource);
        if (httpMethod.equalsIgnoreCase("POST")) {
            response = reqSpec.when().post(resources.getResource());
        } else {
            response = reqSpec.when().get(resources.getResource());
        }
    }

    @Then("the API call is successfully with status code {int}")
    public void the_api_call_is_successfully_with_status_code(int int1) {
        Assert.assertEquals(response.getStatusCode(), int1);
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String expectedValue) {
        //.then().spec(resSpec).extract().response();
        String res = response.asString();
        Assert.assertEquals(new JsonPath(res).getString(keyValue), expectedValue);
    }
}
