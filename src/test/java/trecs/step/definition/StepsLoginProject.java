package trecs.step.definition;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import trecs.core.ApiBase;
import trecs.object.request.LoginProject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;

import java.io.IOException;

public class StepsLoginProject {
  LoginProject loginProject = new LoginProject();
  ApiBase apiBase = new ApiBase();
  Response response;
  int status;
  public static String tokenAccess;

  @Then("Verify Create Project with {string} request send success with status code is {int}")
  public void verifyCreateProjectWithRequestSendSuccessWithStatusCodeIs(String titleTestCase, int sttCode) {
    Assert.assertEquals(String.format("Status code is not match, expected is %s but actual is %s ",
            sttCode, response.getStatusCode()),
            sttCode, response.getStatusCode());
  }

  @Then("Verify Create Project with {string} request send success with status code is {int} and no x-api-key")
  public void verifyCreateProjectWithRequestSendSuccessWithStatusCodeIsAndNoXApiKey(String titleTestCase, int sttCode) {
    Assert.assertEquals(String.format("Status code is not match, expected is %s but actual is %s ",
                sttCode, response.getStatusCode()), sttCode, response.getStatusCode());
  }

  @Given("Get {string} data input Create Project")
  public void getDataInputCreateProject(String titleTestCase) {
    loginProject.getDataInputCreateProject(titleTestCase);
  }

  @And("Verify Create Project body with {string}")
  public void verifyCreateProjectBodyWith(String titleTestCase) throws JsonProcessingException {
    String[] arr = {"name", "companyName", "contactEmail", "collectionMonthlyLimit", "mintMonthlyLimit"};
    String jsonString = response.asString();
    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode jsonApi = objectMapper.readTree(jsonString);
    JsonNode dataInput = LoginProject.dataInputCreateProject;
    for (String object : arr) {
      JsonNode api = jsonApi.get("data").get(object);
      JsonNode input = dataInput.get(object);
      Assert.assertEquals(String.format("%s Data Api and data input not match data api is %s but data input is %s", object, api, input), api, input);
    }
  }

  @Given("Send Api Login with username and password {string}")
  public void sendApiLoginWithUsernameAndPassword(String value) {
    loginProject.getDataInputCreateProject(value);
    response = loginProject.postAPILogin();
    status = response.getStatusCode();
    try {
      tokenAccess = apiBase.getResponseJsonData(response).get("accessToken").asText();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Then("Verify Login success with status code is {int}")
  public void verifyLoginSuccessBody(int sttCode) {
    Assert.assertEquals(sttCode, status);
  }
}
