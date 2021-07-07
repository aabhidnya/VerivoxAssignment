@dsltariff
Feature: DSL Tariffs
  AS A Verivox user
  I WANT TO use the DSL calculator and tariff search pages
  SO THAT I can select the best available internet tariff for my needs

  Background: Visit to the DSL calculator page
    Given that I can open Verivox site
     When I navigate to the DSL calculator page

  Scenario: Verify the DSL calculator
    When I enter "030" for my area code
     And I select the 100 Mbit/s bandwidth option
     And I click the Jetzt vergleichen button
    Then I should see a page that lists the available tariffs for my selection

  Scenario: Load multiple tariff result pages
    When I enter "030" for my area code
     And I select the 100 Mbit/s bandwidth option
     And I click the Jetzt vergleichen button
    Then I should see a page that lists the available tariffs for my selection
    When I display the tariff Result List page
    Then I should see the total number of available tariffs listed in the Ermittelte Tarife section
    When I scroll to the end of the Result List page
    Then I should see only the first 20 tariffs displayed
    When I click on the button labeled "20 weitere tarife laden"
    Then I should see the next 20 tariffs displayed
     And I can continue to load any additional tariffs until all tariffs have been displayed