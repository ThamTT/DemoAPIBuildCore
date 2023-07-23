package trecs.object.request;

import com.fasterxml.jackson.databind.node.ArrayNode;
import trecs.core.ApiBase;
import trecs.core.ApiEndPoint;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;

import java.io.IOException;
import java.util.Map;

public class RecProject extends ApiBase {
    public static String id = "";
    public static String assetType = "";
    public static String countryCode = "";

    public void getDataForRec(Response response) throws IOException {
        ArrayNode records = null;
        records = (ArrayNode) (getResponseJsonData(response).get("records"));
        if (records != null) {
            id = records.get(0).get("id").asText();
            assetType = records.get(0).get("assetType").asText();
            countryCode = records.get(0).get("countryInfo").get("countryCode").asText();

            Serenity.setSessionVariable("id").to(id);
            Serenity.setSessionVariable("country").to(countryCode);
            Serenity.setSessionVariable("type").to(assetType);
        }
    }

    public Response sendAPIREC(String token, Map<String, String> params) {
        return sendRequestUsingTokenAndHaveParam(
                ApiEndPoint.URL_REC, "GET", null, "yes", token, params);
    }

    public Response sendAPIRECQuantity(String token, Map<String, String> params) {
        return sendRequestUsingTokenAndHaveParam(
                ApiEndPoint.URL_REC + "/count", "GET", null, "yes", token, params);
    }

    public Response sendAPIRecTransfer(String token) {
        return sendRequestWithToken(
                ApiEndPoint.URL_REC + "/transfer", "GET", null, "yes", token);
    }

    public Response sendAPIGetGroupID(String token) {
        return sendRequestWithToken(
                ApiEndPoint.URL_REC, "GET", null, "yes", token);
    }

    public Response sendAPIRECGroupDetail(String token, Map<String, String> params) {
        return sendRequestUsingTokenAndHaveParam(
                ApiEndPoint.URL_REC + "/" + Serenity.sessionVariableCalled("groupId "), "GET", null, "yes", token, params);
    }
}
