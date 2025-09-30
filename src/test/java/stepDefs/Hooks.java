package stepDefs;

import io.cucumber.java.Before;

import java.io.FileNotFoundException;

public class Hooks {

    @Before
    public void addPlaceHook() throws FileNotFoundException {
        if (StepDefinitions.placeID == null) {
            StepDefinitions sd = new StepDefinitions();
            System.out.println("Inside the before hook");
            sd.add_place_payload("Mani", "French-IN", "1st Block");
            sd.user_calls_with_http_request("AddPlaceAPI", "POST");
            sd.validateIfThePlaceIsAddedSuccessfullyWithRequest("GetPlaceAPI", "Mani");
            sd.the_api_call_is_successfully_with_status_code(200);
        }
    }
}
