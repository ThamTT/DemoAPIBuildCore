package trecs.core;

import com.fasterxml.jackson.databind.JsonNode;

public class ApiProfile {
  public static JsonNode dataInputProfile;
  ApiBase apiBase = new ApiBase();

  public void postDataInputProfile(String testCaseTitle) {
    dataInputProfile = apiBase.readJsonFile("DataProject.json", testCaseTitle, "body");
  }
}
