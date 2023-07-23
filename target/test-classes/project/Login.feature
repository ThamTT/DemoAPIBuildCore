Feature: Login Project

  @Post @Login
  Scenario: Login with valid username and password
    Given Send Api Login with username and password "valid"
    Then Verify Login success with status code is 200

  @Post @Login
  Scenario: Login with invalid username and password
    Given Send Api Login with username and password "invalid"
    Then Verify Login success with status code is 401