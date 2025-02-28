Feature: login to saucedemo
	
	@testcase1
  Scenario: login to Saucedemo Website with valid data
    Given User is on Saucedemo Login Page
    When User enters Username and Password
      | Username | standard_user |
      | Password | secret_sauce  |
    Then User should see the catalog
	
  @testcase2
  Scenario: login to Saucedemo Website and Add item to the cart
    Given User is on Saucedemo Login Page
    When User enters Username and Password
      | Username | performance_glitch_user |
      | Password | secret_sauce  |
    Then User should see the catalog
    When User Add Single Goods to the Cart "Sauce Labs Backpack"
    When User Add Single Goods to the Cart "Sauce Labs Bike Light"
    When User Add Single Goods to the Cart "Sauce Labs Bolt T-Shirt"
    When User Add Single Goods to the Cart "Sauce Labs Fleece Jacket"
    When User Add Single Goods to the Cart "Sauce Labs Onesie"
    When User Add Single Goods to the Cart "Test.allTheThings() T-Shirt (Red)"
    When User Click to View the Cart
    When User Add the informations
      | First Name      | Heart |
      | Last Name       | Beats |
      | Zip/Postal Code | 10700 |
    When User are Checking Total Price
    Then User has ordered successful and back to home page

  @testcase3
  Scenario: login to Saucedemo Website and Remove item from the cart
    Given User is on Saucedemo Login Page
    When User enters Username and Password
      | Username | standard_user |
      | Password | secret_sauce  |
    Then User should see the catalog
    When User Add Single Goods to the Cart "Sauce Labs Backpack"
    When User Add Single Goods to the Cart "Sauce Labs Bike Light"
    When User Add Single Goods to the Cart "Sauce Labs Bolt T-Shirt"
    When User Add Single Goods to the Cart "Sauce Labs Fleece Jacket"
    When User Add Single Goods to the Cart "Sauce Labs Onesie"
    When User Add Single Goods to the Cart "Test.allTheThings() T-Shirt (Red)"
    When User Remove Single Good from the Cart "Sauce Labs Backpack"
    When User Remove Single Good from the Cart "Sauce Labs Bike Light"
    When User Remove Single Good from the Cart "Sauce Labs Bolt T-Shirt"
    When User Remove Single Good from the Cart "Sauce Labs Fleece Jacket"
    When User Remove Single Good from the Cart "Sauce Labs Onesie"
    When User Remove Single Good from the Cart "Test.allTheThings() T-Shirt (Red)"
    When User Click to View the Cart
    When User Add the informations
      | First Name      | Heart |
      | Last Name       | Beats |
      | Zip/Postal Code | 10700 |
    When User are Checking Total Price
    Then User has ordered successful and back to home page
