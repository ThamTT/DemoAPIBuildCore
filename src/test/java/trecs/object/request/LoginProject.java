package trecs.object.request;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import trecs.core.ApiBase;
import trecs.core.ApiEndPoint;
import io.restassured.response.Response;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.util.List;

public class LoginProject extends ApiBase {
  public static JsonNode dataInputCreateProject;

  public void getDataInputCreateProject(String testCaseTitle) {
    JsonNode dataObject = readJsonFile("DataProject.json", testCaseTitle, "body");
    if (dataObject.hasNonNull("username") == false || (dataObject.get("username").textValue().equals(""))) {
      ((ObjectNode) dataObject).put("username", randomName(dataObject.get("username").textValue()));
    }
    dataInputCreateProject = dataObject;
  }

  public Response postAPILogin() {
    return sendRequest(ApiEndPoint.URL_LOGIN, "POST", dataInputCreateProject, "yes");
  }


  public void getDataInputCreateProjects() throws IOException {
    String pathFile = "/src/test/resources/data/%s/";
    String filePath =
            String.format(
                    "%s%s%s", System.getProperty("user.dir"), String.format(pathFile, env()), "DataProject.json");
    ObjectMapper mapper = new ObjectMapper();
    JsonNode node = null;
    JsonNode jsonNode = mapper.readTree(Paths.get(filePath).toFile());
    node = jsonNode.get("puts");

    List<String> arr = null;
      ((ObjectNode) node).put("recIdList", (BigDecimal) arr);
    dataInputCreateProject = node;
    System.out.println("data = " + dataInputCreateProject);
  }

}
