package trecs.step.definition;

import trecs.core.ApiBase;
import trecs.object.request.LoginProject;
import trecs.object.request.RegisterProject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;

import java.io.IOException;

public class StepsRegisterProject {
  RegisterProject registerProject = new RegisterProject();
  LoginProject loginProject = new LoginProject();
  ApiBase apiBase = new ApiBase();
  Response response;
  int status;
  int statusRegister;
  public static String accountId;

  @Given("Send Api {string} a new user account")
  public void sendApiANewUserAccount(String value) throws IOException {
    loginProject.getDataInputCreateProject(value);
    response = registerProject.postAPIRegister();
    status = response.getStatusCode();
    accountId = apiBase.getResponseJsonData(response).get("accountId").asText();
  }

  @Then("Verify Register success with status code is {int}")
  public void verifyRegisterSuccessWithStatusCodeIs(int sttCode) {
    Assert.assertEquals(sttCode, status);
  }

  @Given("Send Api get account info of a register account")
  public void sendApiGetAccountInfoOfARegisterAccount() {
    registerProject.getAPIAccountInfo();
    statusRegister = response.getStatusCode();
  }

  @Then("Verify response api get account info with status code is {int}")
  public void verifyResponseApiGetAccountInfoWithStatusCodeIs(int sttCode) {
    Assert.assertEquals(sttCode, statusRegister);
  }

  @Given("Test get and put")
  public void testGetAndPut() throws IOException {
    loginProject.getDataInputCreateProjects();
//    response = registerProject.postAPIRegister();
//    status = response.getStatusCode();
//    accountId = apiBase.getResponseJsonData(response).get("accountId").asText();
  }
}