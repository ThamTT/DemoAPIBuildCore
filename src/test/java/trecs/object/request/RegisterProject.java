package trecs.object.request;

import trecs.core.ApiBase;
import trecs.core.ApiEndPoint;
import io.restassured.response.Response;

import static trecs.object.request.LoginProject.dataInputCreateProject;
import static trecs.step.definition.StepsRegisterProject.accountId;

public class RegisterProject extends ApiBase {

  public Response postAPIRegister() {
    return sendRequest(ApiEndPoint.URL_REGISTER, "POST", dataInputCreateProject, "yes");
  }

  public void getAPIAccountInfo() {
    sendRequest(ApiEndPoint.URL_REGISTER + "/" + accountId, "GET", null, "yes");
  }
}
