Feature: Rec Project

  @Get @Rec
  Scenario Outline: Get all available RECs, group by asset (Auth)
    Given Send Api Login with username and password "valid"
    Then Verify Login success with status code is 200
    When Send api get asset with access token "Yes"
      | Key         | Value |
      | currentPage | 1     |
      | pageSize    | 10    |
      | approved    | true  |
    And Get data for Rec
    When Send api get rec with access token "<Auth>"
      | Key         | Value       |
      | assetId     | key_id      |
      | countryCode | key_country |
      | type        | key_type    |
    Then Verify response api rec with status code is <Status>
    Examples:
      | Auth | Status |
      | Yes  | 200    |
      | No   | 401    |

  @Get @Rec
  Scenario Outline: Get available REC quantity for selling (Auth)
    Given Send Api Login with username and password "valid"
    Then Verify Login success with status code is 200
    When Send api get asset with access token "Yes"
      | Key         | Value |
      | currentPage | 1     |
      | pageSize    | 10    |
      | approved    | true  |
    And Get data for Rec
    When Send api get rec quantity with access token "<Auth>"
      | Key         | Value       |
      | assetId     | key_id      |
      | countryCode | key_country |
      | type        | key_type    |
      | vintageFrom | 2020-01     |
      | vintageTo   | 2020-01     |
    Then Verify response api rec with status code is <Status>
    Examples:
      | Auth | Status |
      | Yes  | 200    |
      | No   | 401    |

  @Get @Rec
  Scenario Outline: Get a list of all REC transfer (Auth)
    Given Send Api Login with username and password "valid"
    Then Verify Login success with status code is 200
    When  Send API Rec transfer "<Auth>"
    Then Verify response api rec with status code is <Status>
    Examples:
      | Auth | Status |
      | Yes  | 200    |
      | No   | 401    |

  @Post @Rec1
  Scenario Outline: Get REC group detail with REC tokens information (Auth)
    Given Send Api Login with username and password "valid"
    Then Verify Login success with status code is 200
    When  Get groupID from recs list
    When Send api get rec group detail with access token "<Auth>"
      | Key     | Value       |
      | groupId | key_groupId |
      | detail  | false       |
    Then Verify response api rec with status code is <Status>
    Examples:
      | Auth | Status |
      | Yes  | 200    |
      | No   | 401    |