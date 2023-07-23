package trecs.step.definition;

import com.fasterxml.jackson.databind.node.ArrayNode;
import trecs.object.request.RecProject;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import org.junit.Assert;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static trecs.step.definition.StepsAssetProject.response;
import static trecs.step.definition.StepsLoginProject.tokenAccess;

public class StepsRecProject {

    Map<String, String> mapParams =null;
    RecProject recProject = new RecProject();
    public static Response responses;
    int statusCode;
    String groupID;

    @And("Get data for Rec")
    public void getDataForRec() throws IOException {
        recProject.getDataForRec(response);
    }

    @When("Send api get rec with access token {string}")
    public void sendApiGetAssetWithAccessToken(String token, DataTable dataTable) {
        mapParams = new HashMap<>();
        if (token.equals("Yes")) {
            System.out.println("map = " + recProject.getDataTable(mapParams, dataTable));
            responses = recProject.sendAPIREC(tokenAccess, recProject.getDataTable(mapParams, dataTable));
            statusCode = responses.getStatusCode();
        }
        if (token.equals("No")) {
            responses = recProject.sendAPIREC("", recProject.getDataTable(mapParams, dataTable));
            statusCode = responses.getStatusCode();
        }
    }

    @When("Send api get rec quantity with access token {string}")
    public void sendApiGetQuantityWithAccessToken(String token, DataTable dataTable) {
        mapParams = new HashMap<>();
        if (token.equals("Yes")) {
            System.out.println("map = " + recProject.getDataTable(mapParams, dataTable));
            responses = recProject.sendAPIRECQuantity(tokenAccess, recProject.getDataTable(mapParams, dataTable));
            statusCode = responses.getStatusCode();
        }
        if (token.equals("No")) {
            responses = recProject.sendAPIRECQuantity("", recProject.getDataTable(mapParams, dataTable));
            statusCode = responses.getStatusCode();
        }
    }

    @Then("Verify response api rec with status code is {int}")
    public void verifyResponseApiAssetWithStatusCodeIsStatus(int sttCode) {
        Assert.assertEquals(sttCode, statusCode);
    }

    @When("Send API Rec transfer {string}")
    public void sendAPIRecTransfer(String token) {
        if (token.equals("Yes")){
            responses = recProject.sendAPIRecTransfer(tokenAccess);
            statusCode = responses.getStatusCode();
        }
        if (token.equals("No")){
            responses = recProject.sendAPIRecTransfer("");
            statusCode = responses.getStatusCode();
        }
    }

    @When("Get groupID from recs list")
    public void getGroupIDFromRecsList() throws IOException {
        responses = recProject.sendAPIGetGroupID(tokenAccess);
        ArrayNode records = (ArrayNode) (recProject.getResponseJsonData(response).get("groupId"));
        groupID = records.get(0).asText();
        System.out.println("GroupID = " + groupID);
        Serenity.setSessionVariable("groupID").to(groupID);
    }

    @When("Send api get rec group detail with access token {string}")
    public void sendApiGetRecGroupDetailWithAccessToken(String token, DataTable dataTable) {
        mapParams = new HashMap<>();
        if (token.equals("Yes")) {
            System.out.println("map = " + recProject.getDataTable(mapParams, dataTable));
            responses = recProject.sendAPIRECGroupDetail(tokenAccess, recProject.getDataTable(mapParams, dataTable));
            statusCode = responses.getStatusCode();
        }
        if (token.equals("No")) {
            responses = recProject.sendAPIRECGroupDetail("", recProject.getDataTable(mapParams, dataTable));
            statusCode = responses.getStatusCode();
        }
    }
}
