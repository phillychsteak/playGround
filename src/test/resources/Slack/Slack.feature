Feature: Slack Automation

  Scenario: Sending Message
    Given user goes to slack.com page and loggs in "kadyrovrakhim@gmail.com" and "123fuckoff789"
    When user sending messages
    Then user verifies messages are sent and delivered