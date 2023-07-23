package trecs.object.request;


import io.restassured.response.Response;
import trecs.core.ApiAsset;
import trecs.core.ApiBase;
import trecs.core.ApiEndPoint;
import trecs.step.definition.StepsAssetProject;

import java.util.Map;



public class AssetProject extends ApiBase {

  public Response sendAPIAsset(String token, Map<String, String> params) {
    return sendRequestUsingTokenAndHaveParam(
        ApiEndPoint.URL_ASSET, "GET", null, "yes", token, params);
  }

  public Response postAPICreateAsset(String token) {
    return sendRequestWithToken(ApiEndPoint.URL_ASSET, "POST", ApiAsset.dataInputAddAsset, "yes", token);
  }

  public Response postAPICreateAssetInvalid(String token) {
    return sendRequestWithToken(
        ApiEndPoint.URL_ASSET, "POST", ApiAsset.dataInputCreateAssetInvalid, "yes", token);
  }

  public Response getAPICreateAsset(String token) {
    return sendRequestWithToken(ApiEndPoint.URL_ASSET + "/" + StepsAssetProject.assetID, "GET", null, "yes", token);
  }

  public Response getAPICreateAssetInvalid(String token) {
    return sendRequestWithToken(
        ApiEndPoint.URL_ASSET + "/" + StepsAssetProject.assetID + "12345", "GET", null, "yes", token);
  }

  public Response deleteAPIAsset(String token) {
    return sendRequestWithToken(ApiEndPoint.URL_ASSET + "/" + StepsAssetProject.idAsset, "DELETE", null, "yes", token);
  }
}
