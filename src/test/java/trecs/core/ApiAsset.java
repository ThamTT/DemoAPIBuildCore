package trecs.core;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ApiAsset {
  public static JsonNode dataInputAddAsset;
  public static JsonNode dataInputCreateAssetInvalid;
  ApiBase apiBase = new ApiBase();
  public String assetId;

  public void dataInputCreateAsset(String testCaseTitle) {
    JsonNode dataObject = apiBase.readJsonFile("DataProject.json", testCaseTitle, "body");
    if (dataObject.hasNonNull("plantName") == false
        || (dataObject.get("plantName").textValue().equals(""))) {
      ((ObjectNode) dataObject)
          .put("plantName", apiBase.randomPlantName(dataObject.get("plantName").textValue()));
    }
    dataInputAddAsset = dataObject;
  }

  public void postDataInputCreateAssetInvalid() {
    dataInputCreateAssetInvalid =
        apiBase.readJsonFile("DataProject.json", "createAssetInvalid", "body");
  }
}
