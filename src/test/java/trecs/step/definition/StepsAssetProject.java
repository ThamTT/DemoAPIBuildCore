
package trecs.step.definition;

import com.fasterxml.jackson.databind.node.ArrayNode;
import trecs.core.ApiAsset;
import trecs.core.ApiBase;
import trecs.object.request.AssetProject;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import org.junit.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static trecs.step.definition.StepsLoginProject.tokenAccess;

public class StepsAssetProject {
  AssetProject assetProject = new AssetProject();
  ApiAsset apiAsset = new ApiAsset();
  ApiBase apiBase = new ApiBase();
  Map<String, String> mapParams =null;
  public static Response response;
  int statusCode;
  String id="";
  public static String assetID;
  public static String idAsset;

  @When("Send api get asset with access token {string}")
  public void sendApiGetAssetWithAccessToken(String token, DataTable dataTable) throws Exception {
    mapParams =  new HashMap<>();
    if (token.equals("Yes")){
      List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
      for (Map<String, String> stringStringMap : list) {
        String value = stringStringMap.get("Value").toString();
        if(value.contains("key")){
          value = Serenity.sessionVariableCalled(value.split("_")[1]);
        }
        mapParams.put(stringStringMap.get("Key"), value);
      }
      System.out.println("map = " + mapParams);
      response = assetProject.sendAPIAsset(tokenAccess, mapParams);
      statusCode = response.getStatusCode();
      ArrayNode records = (ArrayNode) (apiBase.getResponseJsonData(response).get("records"));
      idAsset = records.get(0).get("id").asText();
    }
    if (token.equals("No")){
      List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
      Map<String, String> mapParams = new HashMap<>();

      for (Map<String, String> stringStringMap : list) {
        String value = stringStringMap.get("Value").toString();
        if(value.contains("key")){
          value = Serenity.sessionVariableCalled(value.split("_")[1]);
        }
        mapParams.put(stringStringMap.get("Key"), value);
      }
      response = assetProject.sendAPIAsset("", mapParams);
      statusCode = response.getStatusCode();
    }
  }

  @Then("Verify response api Asset with status code is {int}")
  public void verifyResponseApiAssetWithStatusCodeIsStatus(int sttCode) {
    Assert.assertEquals(sttCode, statusCode);
  }

  @And("Send api post {string} with token {string}")
  public void sendApiWithToken(String value, String token) throws Exception{
    apiAsset.dataInputCreateAsset(value);
    if (token.equals("Yes")){
      response = assetProject.postAPICreateAsset(tokenAccess);
      statusCode = response.getStatusCode();
      assetID = apiBase.getResponseJsonData(response).get("id").asText();
    }
    if (token.equals("No")){
      response = assetProject.postAPICreateAsset("");
      statusCode = response.getStatusCode();
    }
    if (token.equals("Client")){
      apiAsset.postDataInputCreateAssetInvalid();
      response = assetProject.postAPICreateAssetInvalid(tokenAccess);
      statusCode = response.getStatusCode();
    }
  }

  @And("Send api get create an asset with token {string}")
  public void sendApiCreateAnAssetWithToken(String token) {
    if (token.equals("Yes")){
      response = assetProject.getAPICreateAsset(tokenAccess);
      statusCode = response.getStatusCode();
    }
    if (token.equals("No")){
      response = assetProject.getAPICreateAsset("");
      statusCode = response.getStatusCode();
    }
    if (token.equals("Client")){
      response = assetProject.getAPICreateAssetInvalid(tokenAccess);
      statusCode = response.getStatusCode();
    }
  }

  @When("Send api delete create an asset with token {string}")
  public void sendApiDeleteCreateAnAssetWithToken(String token) {
    response = assetProject.deleteAPIAsset(tokenAccess);
    statusCode = response.getStatusCode();
  }
}