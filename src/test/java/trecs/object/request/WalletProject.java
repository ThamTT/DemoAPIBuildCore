package trecs.object.request;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import trecs.core.ApiBase;
import trecs.core.ApiEndPoint;
import io.restassured.response.Response;

import static trecs.step.definition.StepsLoginProject.tokenAccess;
import static trecs.step.definition.StepsWalletProject.eventId;
import static trecs.step.definition.StepsWalletProject.eventIdWithdraw;

public class WalletProject extends ApiBase {
  public static JsonNode dataInputWalletPayment;
  public static JsonNode dataInputWithdrawMoney;
  public static JsonNode dataInputTransferAmount;
  public String paymentIdWallet;
  public String paymentIdWithdraw;

  ApiBase apiBase = new ApiBase();

  public Response getTheWholeHistory() {
    return sendRequestWithToken(ApiEndPoint.URL_GET_WALLET, "GET", null, "yes", tokenAccess);
  }

  public void postDataInputWalletPayment(String testCaseTitle) {
    dataInputWalletPayment = apiBase.readJsonFile("DataProject.json", testCaseTitle, "body");
    ((ObjectNode) dataInputWalletPayment).put("paymentId", paymentIdWallet);
  }

  public Response postDepositMoney() {
    return sendRequestWithToken(ApiEndPoint.URL_GET_WALLET, "POST", dataInputWalletPayment, "yes", tokenAccess);
  }

  public Response getADepositHistoryEvent() {
    return sendRequestWithToken(ApiEndPoint.URL_GET_WALLET_DEPOSIT + "/" + eventId, "GET", null, "yes", tokenAccess);
  }

  public Response getTheWholeHistoryWithdraw() {
    return sendRequestWithToken(ApiEndPoint.URL_GET_WALLET_WITHDRAW, "GET", null, "yes", tokenAccess);
  }

  public void postDataInputWithdrawMoney(String testCaseTitle) {
    dataInputWithdrawMoney = apiBase.readJsonFile("DataProject.json", testCaseTitle, "body");
    ((ObjectNode) dataInputWithdrawMoney).put("paymentId", paymentIdWithdraw);
  }

  public Response postWithdrawMoney() {
    return sendRequestWithToken(ApiEndPoint.URL_POST_WALLET_WITHDRAW, "POST", dataInputWithdrawMoney, "yes", tokenAccess);
  }

  public Response getAWithdrawHistoryEvent() {
    return sendRequestWithToken(ApiEndPoint.URL_POST_WALLET_WITHDRAW + "/" + eventIdWithdraw, "GET", null, "yes", tokenAccess);
  }

  public Response getAListOfAllUSDTransfer(String token) {
    return sendRequestWithToken(ApiEndPoint.URL_GET_WALLET_TRANSFER, "GET", null, "yes", token);
  }

  public void postDataInputTransferAmount(String testCaseTitle) {
    dataInputTransferAmount = apiBase.readJsonFile("DataProject.json", testCaseTitle, "body");
  }

  public Response postTransferAmount(String token) {
    return sendRequestWithToken(ApiEndPoint.URL_GET_WALLET_TRANSFER, "POST", dataInputTransferAmount, "yes", token);
  }
}
