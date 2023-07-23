Feature: Asset Project

  @Get @Asset
  Scenario Outline: Get all assets or approved assets (Auth)
    Given Send Api Login with username and password "valid"
    Then Verify Login success with status code is 200
    When Send api get asset with access token "<Auth>"
      | Key         | Value |
      | currentPage | 1     |
      | pageSize    | 10    |
      | approved    | true  |
    Then Verify response api Asset with status code is <Status>
    Examples:
      | Auth | Status |
      | Yes  | 200    |
      | No   | 401    |

  @Post @Asset
  Scenario Outline: Create an asset (Auth)
    Given Send Api Login with username and password "valid"
    Then Verify Login success with status code is 200
    When Send api post "createAsset" with token "<Auth>"
    Then Verify response api Asset with status code is <Status>
    Examples:
      | Auth   | Status |
      | Yes    | 200    |
      | No     | 401    |
      | Client | 400    |

  @Get @Asset
  Scenario Outline: Get a single asset (Auth)
    Given Send Api Login with username and password "valid"
    Then Verify Login success with status code is 200
    When Send api post "createAsset" with token "Yes"
    Then Verify response api Asset with status code is 200
    When Send api get create an asset with token "<Auth>"
    Then Verify response api Asset with status code is <Status>
    Examples:
      | Auth   | Status |
      | Yes    | 200    |
      | No     | 401    |
      | Client | 404    |

  @Delete @Asset
  Scenario Outline: Delete a single asset (Auth)
    Given Send Api Login with username and password "valid"
    Then Verify Login success with status code is 200
    When Send api get asset with access token "Yes"
      | Key         | Value |
      | currentPage | 1     |
      | pageSize    | 10    |
      | approved    | true  |
    Then Verify response api Asset with status code is 200
    When Send api delete create an asset with token "<Auth>"
    Then Verify response api Asset with status code is <Status>
    Examples:
      | Auth   | Status |
      | Yes    | 200    |