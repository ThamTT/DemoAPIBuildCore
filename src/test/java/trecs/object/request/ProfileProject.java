package trecs.object.request;

import trecs.core.ApiBase;
import trecs.core.ApiEndPoint;
import io.restassured.response.Response;

import static trecs.core.ApiProfile.dataInputProfile;

public class ProfileProject extends ApiBase {

  public Response getUserProfile(String token) {
    return sendRequestWithToken(ApiEndPoint.URL_PROFILE, "GET", null, "yes", token);
  }

  public Response postUpdateUserProfile(String token) {
    return sendRequestWithToken(ApiEndPoint.URL_PROFILE, "POST", dataInputProfile, "yes", token);
  }

  public Response getProfileBalance(String token) {
    return sendRequestWithToken(ApiEndPoint.URL_PROFILE_BALANCE, "GET", null, "yes", token);
  }
}
