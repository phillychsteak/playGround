Feature: Amazone.com links

  Scenario: Print out all links
    When user goes to amazon.com
    Then print out  links
    Then print out only working links