Feature: Payment Project

  @Get @Payment
  Scenario: Get a list of payment accounts that can be used (Auth)
    Given Send Api Login with username and password "valid"
    Then Verify Login success with status code is 200
    When Send Api get a list of payment accounts that can be used
    Then Verify response list of payment with status code is 200

  @Post @Payment1
  Scenario: Create a single payment account (Bank Transfer type, USD currency) (Auth)
    Given Send Api Login with username and password "valid"
    Then Verify Login success with status code is 200
    When Send Api post "addPayment"
    Then Verify response create a single payment account with status code is 200
#    Get a single payment account (Auth)
    When Send Api get a payment account
    Then Verify response send Api get payment account with status code is 200
#    Update the status of a payment account (Disabled or Activated) (Auth)
    When Send Api put "updateStatusPayment" of a payment account
    Then Verify response put update the status of a payment account with status code is 200