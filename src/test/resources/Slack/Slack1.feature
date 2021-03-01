Feature: Slack Automation

  Scenario: Slack messages
    When Send message to slack via POST request
    Then  Verify the message via GET request