@addressCheck
Feature: Address checks
  AS A Verivox developer
  I WANT TO find city and street names for a particular German postcode
  SO THAT I can help customers select their address details easily

  Scenario Outline: Find the cities for a given postcode
    Given the address checking service endpoint
     When I request the cities for postcode "<postcode>"
     Then I should receive a response with "<city>"

    Examples: 
      | postcode | city                             |
      |    10409 | Berlin                           |
      |    77716 | Fischerbach, Haslach, Hofstetten |

  Scenario Outline: Find the streets for a given postcode
    Given the address checking service endpoint
     When I request the streets for "<city>" postcode "<postcode>"
     Then I should receive a response with "<count>" streets
      And street names with special characters and dashes displayed correctly "<streets>"

    Examples: 
      | postcode | city        | count | streets                             |
      |    10409 | Berlin      |    29 | Küselstr,Preußstr,Erich-Weinert-Str |
      |    77716 | Fischerbach |    33 | Gemsbühl,Fritz-Ullmann-Weg          |
