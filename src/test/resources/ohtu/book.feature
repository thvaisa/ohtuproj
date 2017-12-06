Feature: A new book can be created
  Scenario: book creation is successful
    Given page is opened
    When  name "test" and tag "abc" are entered
    Then a new book with name "test" is added

  Scenario: book editing is successful
    Given page is opened
    When  name "test" and tag "abc" are entered
    Then a new book with name "test" is added
    Then a new book with name "test" is opened
    Then the edit button is clicked
    Then name "test21" is entered
    Then the book is saved with name "test21"

  Scenario: book deleting is successful
    Given page is opened
    When  name "test" and tag "abc" are entered
    Then a new book with name "test" is added
    Then a new book with name "test" is opened
    Then the delete button is clicked
    Then a book with name "test" is not visible

