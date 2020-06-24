package jiraStepDefinitions;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;

import org.junit.runner.RunWith;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import jiraPOJO.AddCommentPOJO;
import resources.APIResources;
import resources.RequestBody;
import resources.TestBase;

@RunWith(Cucumber.class)
public class StepDefinitions extends TestBase {

	RequestSpecification req;
	private Response resp;
	private int statusCode;
	private static String createdJiraNum;
	private String issueType;
	private String APIResource;
	private static String pickedValue;

	// Create Issue Headers and Body
	@Given("^Request payload is ready with \"([^\"]*)\" and \"([^\"]*)\"$")
	public void request_payload_is_ready_with_something_and_something(String issueType, String summary)
			throws IOException {

		req = given().spec(requestSpecification("content-type", "application/json"))
				.body(RequestBody.createIssueBody(issueType, summary));
		this.issueType = issueType;
	}

	// Creating an issue
	@When("^\"([^\"]*)\" is invoked to create issue$")
	public void something_is_invoked_to_create_issue(String APIName) {

		resp = req.when().post(getResourceName(APIName)).then().spec(responseSpecification()).extract().response();

	}

	// Verify the Status code - Common step
	@Then("^Response has Statuscode as \"([^\"]*)\"$")
	public void response_has_something_as_something(String expectedStatus) {

		statusCode = resp.getStatusCode();
		Assert.assertEquals(statusCode, Integer.parseInt(expectedStatus));

	}

	// Verify the Issue creation
	@And("^Response has issue created with \"([^\"]*)\" containing \"([^\"]*)\"$")
	public void response_has_issue_with_something_as_something(String keyName, String expectedValue) {

		createdJiraNum = getKeyValue(resp, keyName);
		Assert.assertTrue(createdJiraNum.contains(expectedValue));
		switch (issueType) {
		case "10001":
			System.out.println("Created the story " + createdJiraNum);
			break;
		case "10004":
			System.out.println("Created the bug " + createdJiraNum);
			break;
		}

	}

	// Retrieve the Created Issue using GET
	@And("^\"([^\"]*)\" is invoked successfully with issueKey$")
	public void something_is_invoked_successfully_with_issuekey(String APIName) {

		APIResource = getResourceName(APIName) + createdJiraNum;
		resp = req.get(APIResource).then().spec(responseSpecification()).extract().response();
		String retrieveJiraNum = resp.getBody().jsonPath().get("key");
		Assert.assertEquals(retrieveJiraNum, createdJiraNum);
		String issueName = resp.getBody().jsonPath().get("fields.project.name");
		Assert.assertEquals(issueName, projectName);
		System.out.println("Created issue "+createdJiraNum+" retrieved successfully");
	}

	// Header and File creation for upload
	@Given("^Request payload is ready with attachment \"([^\"]*)\"$")
	public void request_payload_is_ready_with_attachment_something(String fileName) throws IOException {
		
		  
		  req = given().spec(requestSpecification("X-Atlassian-Token",
		  "no-check")).multiPart("file", new File("src/test/java/resources/" +
		  fileName)).pathParam("pathKey", "attachments");

	}

	// Verify the value against a particular key
	@And("^Response has \"([^\"]*)\" with value \"([^\"]*)\"$")
	public void response_has_something_with_value_something(String keyName, String expectedValue) {

		Assert.assertEquals(getKeyValue(resp, keyName), expectedValue);
		System.out.println("Response verified successfully");

	}

	// ModifyAPI invoked for different operations
	@When("^\"([^\"]*)\" is invoked with \"([^\"]*)\" method$")
	public void something_is_invoked_with_something_method(String APIName, String operation) {

		if (operation.equalsIgnoreCase("PUT")) {
			resp = req.when().put(getResourceName(APIName) + createdJiraNum).then().spec(responseSpecification())
					.extract().response();
		System.out.println("Jira modified successfully");}
		if (operation.equalsIgnoreCase("POST")) {
			resp = req.when().post(getResourceName(APIName) + createdJiraNum + "/{pathKey}").then()
					.spec(responseSpecification()).extract().response();
		System.out.println("Jira modified adding comment successfully"); }


	}

	// Request creation for adding a comment
	@Given("^Request payload is ready with the comment \"([^\"]*)\"$")
	public void request_payload_is_ready_with_the_comment_something(String comment) throws IOException {

		req = given().spec(
				(requestSpecification("content-type", "application/json")).body(RequestBody.addCommentBody(comment)))
				.pathParam("pathKey", "comment");
	}

	// Fetch a key value from a response - Common step
	@And("^\"([^\"]*)\" from \"([^\"]*)\" field is fetched successfully$")
	public void something_from_something_field_is_fetched_successfully(String toBePicked, String keyName) {

		pickedValue = getKeyValue(resp, keyName);

	}

	// Get operation using the pickedValue - Common step
	@And("^Details retrieved successfully using \"([^\"]*)\"$")
	public void details_retrieved_successfully_using_something(String toBePicked) throws IOException {
		resp = given().spec(requestSpecification("content-type", "application/json")).when().get(pickedValue).then()
				.spec(responseSpecification()).extract().response();
	}

	// Simple request with no body or query or path params - Common step
	@Given("^Basic Request payload is ready$")
	public void basic_request_payload_is_ready() throws IOException {
		req = given().spec(requestSpecification("content-type", "application/json"));

	}

	// Delete operation using the URL just picked - Common step
	@When("^DeleteIssueAPI is invoked to perform delete action$")
	public void deleteissueapi_is_invoked_to_perform_delete_action() {

		resp = req.delete(pickedValue).then().spec(responseSpecification()).extract().response();
		System.out.println("Jira modified by deleting comment successfully");


	}

}