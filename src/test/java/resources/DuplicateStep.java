package resources;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.junit.runner.RunWith;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.RequestBody;
import resources.TestBase;
@RunWith(Cucumber.class)
public class DuplicateStep {

    @Given("^API Request payload is ready $")
    public void api_request_payload_is_ready() throws Throwable {
        throw new PendingException();
    }

    @When("^\"([^\"]*)\" is invoked with \"([^\"]*)\" method$")
    public void something_is_invoked_with_something_method(String strArg1, String strArg2) throws Throwable {
        throw new PendingException();
    }

    @Then("^Response has Status as \"([^\"]*)\"$")
    public void response_has_status_as_something(String strArg1) throws Throwable {
        throw new PendingException();
    }

    @And("^Response has issue with \"([^\"]*)\" as \"([^\"]*)\"$")
    public void response_has_issue_with_something_as_something(String strArg1, String strArg2) throws Throwable {
        throw new PendingException();
    }

}