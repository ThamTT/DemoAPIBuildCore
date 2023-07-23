package trecs.core;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ApiPayment {
  public static JsonNode dataInputAddPayment;
  public static JsonNode dataInputUpdatePayment;
  ApiBase apiBase = new ApiBase();

  public String randomBankAccountNumber(String bankNumber) {
    String currentAccount = System.getProperty("bankAccountNumber", "");
    return String.format("%s%s%s", bankNumber, currentAccount, System.currentTimeMillis());
  }

  public void getDataInputCreateProject(String testCaseTitle) {
    JsonNode dataObject = apiBase.readJsonFile("DataProject.json", testCaseTitle, "body");
    if (dataObject.hasNonNull("bankAccountNumber") == false || (dataObject.get("bankAccountNumber").textValue().equals(""))) {
      ((ObjectNode) dataObject).put("bankAccountNumber", randomBankAccountNumber(dataObject.get("bankAccountNumber").textValue()));
    }
    dataInputAddPayment = dataObject;
  }

  public void putDataInputUpdatePayment(String testCaseTitle) {
    dataInputUpdatePayment = apiBase.readJsonFile("DataProject.json", testCaseTitle, "body");
  }
}
