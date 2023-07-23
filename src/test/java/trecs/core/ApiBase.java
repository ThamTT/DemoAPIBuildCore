package trecs.core;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;
import org.junit.Assert;

import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApiBase {
  private static final Logger LOGGER = Logger.getLogger(ApiBase.class.getName());
  protected static final ObjectMapper objectMapper = new ObjectMapper();
  private static final String ENVIRONMENT = "environment";
  public static EnvironmentVariables environmentVariables =
          SystemEnvironmentVariables.createEnvironmentVariables();
  private static final EnvironmentSpecificConfiguration environmentConf = EnvironmentSpecificConfiguration.from(environmentVariables);
  private String pathFile = "/src/test/resources/data/%s/";

  public ApiBase() {
    try {
      environmentConf.getProperty(ENVIRONMENT);
    } catch (Exception e) {
      Assert.fail(String.format("No environment is specified in serenity.conf file. Error: %s", e));
    }
  }

  public static String env() {
    return environmentConf.getProperty(ENVIRONMENT);
  }

  public static String restApiUrl() {
    return environmentConf.getProperty("rest.api.url");
  }

  public static String restApiKey() {
    return environmentConf.getProperty("x-api-key");
  }

  public Map<?, ?> getResponseBody(Response response, String data) {
    String jsonString = response.asString();
    return JsonPath.from(jsonString).get(data);
  }

  public Response sendRequest(String endpoint, String method, Object obj, String apiKey) {
    if (env().equalsIgnoreCase("local")) {
      RestAssured.baseURI = restApiUrl();
    }
    RequestSpecification httpRequest = SerenityRest.given();
    if (apiKey.equalsIgnoreCase("yes")) {
      httpRequest.header("Content-Type", "application/json");
      httpRequest.header("x-api-key", restApiKey());
    } else {
      httpRequest.header("Content-Type", "application/json");
    }

    switch (method.toUpperCase()) {
      case "GET":
        return httpRequest.get(endpoint);
      case "POST":
        return httpRequest.body(obj).post(endpoint);
      case "DELETE":
        return httpRequest.delete(endpoint);
      case "PUT":
        return httpRequest.body(obj).put(endpoint);
      default:
        Assert.fail("Please input Method");
        return null;
    }
  }

  public JsonNode readJsonFile(String fileName, String rootObject, String object) {
    String filePath =
            String.format(
                    "%s%s%s", System.getProperty("user.dir"), String.format(pathFile, env()), fileName);
    JsonNode node = null;
    try {
      ObjectMapper mapper = new ObjectMapper();
      JsonNode jsonNode = mapper.readTree(Paths.get(filePath).toFile());
      node = jsonNode.get(rootObject).get(object);
    } catch (Exception error) {
      LOGGER.log(Level.WARNING, error.toString());
    }
    return node;
  }

  public String randomName(String username) {
    String currentUser = System.getProperty("username", "test");
    return String.format("auto%s-%s-%s", username, currentUser, System.currentTimeMillis());
  }

  public String convertDate(String date) {
    SimpleDateFormat sdf = new SimpleDateFormat(Const.DATE_INPUT_FORMAT);
    Date d = new Date();
    try {
      d = sdf.parse(date);
    } catch (Exception error) {
      LOGGER.log(Level.WARNING, error.toString());
    }
    sdf.applyPattern(Const.DATE_RESPONSE_FORMAT);
    return sdf.format(d);
  }

  public JsonNode getResponseJsonData(Response response) throws IOException {
    if (response != null) return objectMapper.readTree(response.asString());
    else {
      return null;
    }
  }

  public Response sendRequestWithToken(String endpoint, String method, Object obj, String apiKey, String tokenAccess) {
    if (env().equalsIgnoreCase("local")) {
      RestAssured.baseURI = restApiUrl();
    }
    RequestSpecification httpRequest = SerenityRest.given();
    if (apiKey.equalsIgnoreCase("yes")) {
      httpRequest.header("Content-Type", "application/json");
      httpRequest.header("x-api-key", restApiKey());
      httpRequest.header("Authorization", tokenAccess);
    } else {
      httpRequest.header("Content-Type", "application/json");
      httpRequest.header("Authorization", tokenAccess);
    }

    switch (method.toUpperCase()) {
      case "GET":
        return httpRequest.get(endpoint);
      case "POST":
        return httpRequest.body(obj).post(endpoint);
      case "DELETE":
        return httpRequest.delete(endpoint);
      case "PUT":
        return httpRequest.body(obj).put(endpoint);
      default:
        Assert.fail("Please input Method");
        return null;
    }
  }

  public Response sendRequestUsingTokenAndHaveParam(String endpoint, String method, Object obj, String apiKey, String authorizationHeader, Map<String, String> params) {

    List<String> results = new ArrayList<>();
    params.forEach((k, v) -> results.add(k.trim() + "=" + v.trim()));
    System.out.println("result = " + String.join("&", results));
    endpoint = endpoint + "?" + String.join("&", results);
    if (env().equalsIgnoreCase("local")) {
      RestAssured.baseURI = restApiUrl();
    }

    RequestSpecification httpRequest = SerenityRest.given();
    if (apiKey.equalsIgnoreCase("yes")) {
      httpRequest.header("Content-Type", "application/json");
      httpRequest.header("x-api-key", restApiKey());
      httpRequest.header("Authorization", authorizationHeader);
    } else {
      httpRequest.header("Content-Type", "application/json");
      httpRequest.header("Authorization", authorizationHeader);
    }

    switch (method.toUpperCase()) {
      case "GET":
        return httpRequest.get(endpoint);
      case "POST":
        return httpRequest.body(obj).post(endpoint);
      case "DELETE":
        return httpRequest.delete(endpoint);
      case "PUT":
        return httpRequest.body(obj).put(endpoint);
      default:
        Assert.fail("Please input Method");
        return null;
    }
  }

  public String randomPlantName(String plantName) {
    String currentName = System.getProperty("plantName", "plantName");
    return String.format("auto%s-%s-%s", plantName, currentName, System.currentTimeMillis());
  }

  public Map<String, String> getDataTable(Map<String, String> map, DataTable dataTable) {
    List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
    for (Map<String, String> stringStringMap : list) {
      String value = stringStringMap.get("Value").toString();
      if(value.contains("key")){
        value = Serenity.sessionVariableCalled(value.split("_")[1]);
      }
      map.put(stringStringMap.get("Key"), value);
    }
    return map;
  }
}
