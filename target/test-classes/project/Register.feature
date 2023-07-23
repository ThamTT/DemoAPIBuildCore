Feature: Register Project

  @Post @Register
  Scenario: Register a new user account
    Given Send Api "register" a new user account
    Then Verify Register success with status code is 200

  @Get @Register
  Scenario: Get account info of a register account
    Given Send Api "register" a new user account
    Then Verify Register success with status code is 200
    When Send Api get account info of a register account
    Then Verify response api get account info with status code is 200

  @Post @Registers
  Scenario: Register a new user account
    Given Test get and put