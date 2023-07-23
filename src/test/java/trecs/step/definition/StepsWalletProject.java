package trecs.step.definition;

import com.fasterxml.jackson.databind.node.ArrayNode;
import trecs.core.ApiBase;
import trecs.object.request.WalletProject;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;

import java.io.IOException;

import static trecs.step.definition.StepsLoginProject.tokenAccess;

public class StepsWalletProject {
  WalletProject walletProject = new WalletProject();
  ApiBase apiBase = new ApiBase();
  Response response;
  int status;
  int statusPost;
  public static String eventId;
  public static String eventIdWithdraw;
  int statusGetDepositEvent;
  int statusGetWithdraw;
  int statusPostWithdraw;
  int statusGetWithdrawEvent;
  int statusGetUSDTransfer;
  int statusPostTransferAmount;

  @When("Send Api get the whole history of deposit, order by created time")
  public void sendApiGetTheWholeHistoryOfDepositOrderByCreatedTime() throws IOException {
    response = walletProject.getTheWholeHistory();
    status = response.getStatusCode();
    ArrayNode records = (ArrayNode) (apiBase.getResponseJsonData(response).get("records"));
    walletProject.paymentIdWallet = records.get(0).get("paymentId").asText();
  }

  @Then("Verify response get the whole history of deposit, order with status code is {int}")
  public void verifyResponseGetTheWholeHistoryOfDepositOrderWithStatusCodeIs(int sttCode) {
    Assert.assertEquals(sttCode, status);
  }

  @When("Send Api post {string} to a payment account")
  public void sendApiPostToAPaymentAccount(String value) throws IOException {
    walletProject.postDataInputWalletPayment(value);
    response = walletProject.postDepositMoney();
    statusPost = response.getStatusCode();
    eventId = apiBase.getResponseJsonData(response).get("id").asText();
  }

  @Then("Verify Deposit money to a payment account success with status code is {int}")
  public void verifyDepositMoneyToAPaymentAccountSuccessWithStatusCodeIs(int sttCode) {
    Assert.assertEquals(sttCode, statusPost);
  }

  @When("Send Api get a deposit history event")
  public void sendApiGetADepositHistoryEvent() {
    response = walletProject.getADepositHistoryEvent();
    statusGetDepositEvent = response.getStatusCode();
  }

  @Then("Verify deposit history event success with status code is {int}")
  public void verifyDepositHistoryEventSuccessWithStatusCodeIs(int sttCode) {
    Assert.assertEquals(sttCode, statusGetDepositEvent);
  }

  @When("Send Api get the whole history of withdraw, order by created time")
  public void sendApiGetTheWholeHistoryOfWithdrawOrderByCreatedTime() throws IOException {
    response = walletProject.getTheWholeHistoryWithdraw();
    statusGetWithdraw = response.getStatusCode();
    ArrayNode records = (ArrayNode) (apiBase.getResponseJsonData(response).get("records"));
    walletProject.paymentIdWithdraw = records.get(0).get("paymentId").asText();
  }

  @Then("Verify response get the whole history of withdraw, order with status code is {int}")
  public void verifyResponseGetTheWholeHistoryOfWithdrawOrderWithStatusCodeIs(int sttCode) {
    Assert.assertEquals(sttCode, statusGetWithdraw);
  }

  @When("Send Api post {string} into a payment account")
  public void sendApiPostIntoAPaymentAccount(String value) throws IOException {
    walletProject.postDataInputWithdrawMoney(value);
    response = walletProject.postWithdrawMoney();
    statusPostWithdraw = response.getStatusCode();
    eventIdWithdraw = apiBase.getResponseJsonData(response).get("id").asText();
  }

  @Then("Verify Withdraw money into a payment account success with status code is {int}")
  public void verifyWithdrawMoneyIntoAPaymentAccountSuccessWithStatusCodeIs(int sttCode) {
    Assert.assertEquals(sttCode, statusGetWithdraw);
  }

  @When("Send Api get a withdraw history event")
  public void sendApiGetAWithdrawHistoryEvent() {
    response = walletProject.getAWithdrawHistoryEvent();
    statusGetWithdrawEvent = response.getStatusCode();
  }

  @Then("Verify withdraw history event success with status code is {int}")
  public void verifyWithdrawHistoryEventSuccessWithStatusCodeIs(int sttCode) {
    Assert.assertEquals(sttCode, statusGetWithdrawEvent);
  }

  @When("Send Api Get a list of all USD transfer with {string} token")
  public void sendApiGetAListOfAllUSDTransfer(String token) {
    if (token.equals("Yes")){
      response = walletProject.getAListOfAllUSDTransfer(tokenAccess);
      statusGetUSDTransfer = response.getStatusCode();
    }
    if (token.equals("No")){
      response = walletProject.getAListOfAllUSDTransfer("");
      statusGetUSDTransfer = response.getStatusCode();
    }
  }

  @Then("Verify response Get a list of all USD transfer success with status code is {int}")
  public void verifyResponseGetAListOfAllUSDTransferSuccessWithStatusCodeIs(int sttCode) {
    Assert.assertEquals(sttCode, statusGetUSDTransfer);
  }

  @When("Send Api Post {string} of USD to admin with {string} token")
  public void sendApiPostOfUSDToAdminWithToken(String value, String token) {
    walletProject.postDataInputTransferAmount(value);
    if (token.equals("Yes")) {
      response = walletProject.postTransferAmount(tokenAccess);
      statusPostTransferAmount = response.getStatusCode();
      }
    if (token.equals("No")) {
      response = walletProject.postTransferAmount("");
      statusPostTransferAmount = response.getStatusCode();
      }
    }

  @Then("Verify response Post Transfer an amount of USD to admin success with status code is {int}")
  public void verifyResponsePostTransferAnAmountOfUSDToAdminSuccessWithStatusCodeIsStatus(int sttCode) {
    Assert.assertEquals(sttCode, statusPostTransferAmount);
  }
}