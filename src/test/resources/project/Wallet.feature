Feature: Wallet Project

  @Get @WalletDeposit
  Scenario: Get the whole history of deposit, order by created time (Auth)
    Given Send Api Login with username and password "valid"
    Then Verify Login success with status code is 200
    When Send Api get the whole history of deposit, order by created time
    Then Verify response get the whole history of deposit, order with status code is 200
#    Post Deposit money to a payment account (Auth)
    When Send Api post "depositMoney" to a payment account
    Then Verify Deposit money to a payment account success with status code is 200
#    Get a deposit history event (Auth)
    When Send Api get a deposit history event
    Then Verify deposit history event success with status code is 200

  @Get @WalletWithdraw
  Scenario: Get the whole history of withdraw, order by created time (Auth)
    Given Send Api Login with username and password "valid"
    Then Verify Login success with status code is 200
    When Send Api get the whole history of withdraw, order by created time
    Then Verify response get the whole history of withdraw, order with status code is 200
#    Post Withdraw money to a payment account (Auth)
    When Send Api post "withdrawMoney" into a payment account
    Then Verify Withdraw money into a payment account success with status code is 200
#    Get a Withdraw history event (Auth)
    When Send Api get a withdraw history event
    Then Verify withdraw history event success with status code is 200

  @Get @WalletTransfer
  Scenario Outline: Get a list of all USD transfer (Auth)
    Given Send Api Login with username and password "valid"
    Then Verify Login success with status code is 200
    When Send Api Get a list of all USD transfer with "<Auth>" token
    Then Verify response Get a list of all USD transfer success with status code is <Status>
    Examples:
      | Auth | Status |
      | Yes  | 200    |
      | No   | 401    |

  @Post @WalletTransfer
  Scenario Outline: Transfer an amount of USD to admin (Auth)
    Given Send Api Login with username and password "valid"
    Then Verify Login success with status code is 200
    When Send Api Post "transferAnAmount" of USD to admin with "<Auth>" token
    Then Verify response Post Transfer an amount of USD to admin success with status code is <Status>
    Examples:
      | Auth | Status |
      | Yes  | 200    |
      | No   | 401    |