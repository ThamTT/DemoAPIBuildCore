package trecs.step.definition;

import trecs.core.ApiBase;
import trecs.core.ApiProfile;
import trecs.object.request.ProfileProject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;

import static trecs.step.definition.StepsLoginProject.tokenAccess;

public class StepsProfileProject {
  ProfileProject profileProject = new ProfileProject();
  ApiProfile apiProfile = new ApiProfile();
  ApiBase apiBase = new ApiBase();
  Response response;
  public static String eventId;
  public static String eventIdWithdraw;
  int statusCode;

  @When("Send api get user profile with access token {string}")
  public void sendApiGetUserProfile(String token) {
      if (token.equals("Yes")){
        response = profileProject.getUserProfile(tokenAccess);
        statusCode = response.getStatusCode();
      }
      if (token.equals("No")){
        response = profileProject.getUserProfile("");
        statusCode = response.getStatusCode();
      }
  }

  @Then("Verify response api success with status code is {int}")
  public void verifyResponseApiGetUserProfileSuccessWithStatusCodeIsStatus(int sttCode) {
    Assert.assertEquals(sttCode, statusCode);
  }

  @And("Send api post {string} with access token {string}")
  public void sendApiPostWithAccessToken(String value, String token) {
    apiProfile.postDataInputProfile(value);
    if (token.equals("Yes")){
      response = profileProject.postUpdateUserProfile(tokenAccess);
      statusCode = response.getStatusCode();
    }
    if (token.equals("No")){
      response = profileProject.postUpdateUserProfile("");
      statusCode = response.getStatusCode();
    }
  }

  @And("Send api get profile balance with access token {string}")
  public void sendApiGetProfileBalanceWithAccessToken(String token) {
    if (token.equals("Yes")){
      response = profileProject.getProfileBalance(tokenAccess);
      statusCode = response.getStatusCode();
    }
    if (token.equals("No")){
      response = profileProject.getProfileBalance("");
      statusCode = response.getStatusCode();
    }
  }
}