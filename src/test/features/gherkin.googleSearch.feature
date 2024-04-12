Feature: Google Search
  As an internet user
  I want to use the search function on Google's homepage
  So that I can find relevant information quickly

  Background: The user is on Google's homepage
    Given the user opens Google's homepage in a web browser

  Scenario: Successful search with common keywords
    When the user enters "functionality test" into the search bar
    And the user clicks on the search button
    Then search results relevant to "functionality test" are displayed

  Scenario: Using search suggestion
    When the user starts typing "gherkin"
    Then search suggestions appear below the search bar
    When the user selects the first search suggestion
    And the user clicks on the search button
    Then search results relevant to the full suggested term are displayed

  Scenario: Failed search with a random string
    When the user enters "qweqweqwe123123" into the search bar
    And the user clicks on the search button
    Then a message stating no results were found is displayed
