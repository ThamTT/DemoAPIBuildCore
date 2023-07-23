Feature: Profile Project

  @Get @Profile
  Scenario Outline: Get user profile (Auth)
    Given Send Api Login with username and password "changePass"
    Then Verify Login success with status code is 200
    And Send api get user profile with access token "<Auth>"
    Then Verify response api success with status code is <Status>
    Examples:
      | Auth | Status |
      | Yes  | 200    |
      | No   | 401    |

  @Post @Profile
  Scenario Outline: Update user profile. Email, password, country, and company name change will REQUIRE ADMIN APPROVAL
    Given Send Api Login with username and password "changePass"
    Then Verify Login success with status code is 200
    And Send api post "updateUserProfile" with access token "<Auth>"
    Then Verify response api success with status code is <Status>
    Examples:
      | Auth | Status |
      | Yes  | 200    |
      | No   | 401    |

  @Get @Profile
  Scenario Outline: Get profile balance
    Given Send Api Login with username and password "changePass"
    Then Verify Login success with status code is 200
    And Send api get profile balance with access token "<Auth>"
    Then Verify response api success with status code is <Status>
    Examples:
      | Auth | Status |
      | Yes  | 200    |
      | No   | 401    |