package trecs.object.request;

import trecs.core.ApiBase;
import trecs.core.ApiEndPoint;
import io.restassured.response.Response;

import static trecs.core.ApiPayment.*;
import static trecs.step.definition.StepsLoginProject.tokenAccess;
import static trecs.step.definition.StepsPaymentProject.paymentId;

public class PaymentProject extends ApiBase {

  public Response getAListOfPayment() {
    return sendRequestWithToken(ApiEndPoint.URL_GET_PAYMENT, "GET", null, "yes", tokenAccess);
  }

  public Response postAddPayment() {
    return sendRequestWithToken(ApiEndPoint.URL_PAYMENT, "POST", dataInputAddPayment, "yes", tokenAccess);
  }
  public Response getPaymentAdd() {
    return sendRequestWithToken(ApiEndPoint.URL_PAYMENT + "/" + paymentId, "GET", null, "yes", tokenAccess);
  }

  public Response putPaymentAdd() {
    return sendRequestWithToken(ApiEndPoint.URL_PAYMENT + "/" + paymentId, "PUT", dataInputUpdatePayment, "yes", tokenAccess);
  }
}
