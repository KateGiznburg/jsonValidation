# We have a JSON file with a list of events in it
# Lets pretend that we've got this JSON file as a response of API
# The goal is to test the following features using Java and RestAssured possibilities

Feature: JSON Validation

  Scenario: 1. A default provider ("d") validation

    Given Extracted JSON file with all events as a response from API
    When Verify if there is a default provider 'd' for each event
    Then Print its value 'P' and id 'ID' in the following format 'P-ID'
    And Print the name of the teams ("Nm") in the format "Team1: {1st team} | Team2: {2nd team}

  Scenario: 2. An overall status of the match ("Epr") validation

    Given Extracted JSON file with all events as a response from API
    When Check an overall status of the match ("Epr"), if it is 0 for each event
    Then None of the "Tr1", "Tr2", "Trh1" and "Trh2" should be present

  Scenario: 3. Validation if the match status for each event is int and withing the array [0,1,2,3,4,5,6,7]

    Given Extracted JSON file with all events as a response from API
    When Verify that the status of the match ("Epr") is an int for each event
    Then The status of the match is withing the array [0,1,2,3,4,5,6,7]