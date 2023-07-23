package trecs.step.definition;

import trecs.core.ApiBase;
import trecs.core.ApiPayment;
import trecs.object.request.PaymentProject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;

import java.io.IOException;

public class StepsPaymentProject {
  PaymentProject paymentProject = new PaymentProject();
  ApiPayment apiPayment = new ApiPayment();
  ApiBase apiBase = new ApiBase();
  Response response;
  int status;
  int statusPostPayment;
  int statusGetAPayment;
  int statusPutPayment;
  public static String paymentId;

  @Given("Send Api get a list of payment accounts that can be used")
  public void sendApiANewUserAccount() {
    response = paymentProject.getAListOfPayment();
    status = response.getStatusCode();
  }

  @Then("Verify response list of payment with status code is {int}")
  public void verifyResponseListOfPaymentWithStatusCodeIs(int sttCode) {
    Assert.assertEquals(sttCode, status);
  }

  @When("Send Api post {string}")
  public void sendApiPost(String value) throws IOException {
    apiPayment.getDataInputCreateProject(value);
    response = paymentProject.postAddPayment();
    statusPostPayment = response.getStatusCode();
    paymentId = apiBase.getResponseJsonData(response).get("id").asText();
  }

  @Then("Verify response create a single payment account with status code is {int}")
  public void verifyResponseCreateASinglePaymentAccountWithStatusCodeIs(int sttCode) {
    Assert.assertEquals(sttCode, statusPostPayment);
  }

  @When("Send Api get a payment account")
  public void sendApiGetPaymentAccount() {
    response = paymentProject.getPaymentAdd();
    statusGetAPayment = response.getStatusCode();
  }

  @Then("Verify response send Api get payment account with status code is {int}")
  public void verifyResponseSendApiGetPaymentAccountWithStatusCodeIs(int sttCode) {
    Assert.assertEquals(sttCode, statusGetAPayment);
  }

  @When("Send Api put {string} of a payment account")
  public void sendApiPutOfAPaymentAccount(String value) {
    apiPayment.putDataInputUpdatePayment(value);
    response = paymentProject.putPaymentAdd();
    statusPutPayment = response.getStatusCode();
  }

  @Then("Verify response put update the status of a payment account with status code is {int}")
  public void verifyResponsePutUpdateTheStatusOfAPaymentAccountWithStatusCodeIs(int sttCode) {
    Assert.assertEquals(sttCode, statusPutPayment);
  }
}