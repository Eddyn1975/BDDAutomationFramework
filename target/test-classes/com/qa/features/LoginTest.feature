@Login
Feature: Login Test Feature
         As a user, I want a login page so that only authentic user can have access

@Smoke @US-100 @Epic-1000
Scenario: valid user should be able to login
Given user logs with valid username "demo@techfios.com" and password "abc123"
When 	user clicks on Signin button
Then 	The Dashboard page should be displayed

@Regression @US-101 @Epic-1001 @skip_scenario
Scenario Outline: Invalid user should not be able to login
    Given user logs with invalid "<username>" or "<password>"
    When 	user clicks on Signin button
    Then the Dashboard should not be displayed
    Examples:

|username						|password	|
|demo1@techfios.com	|abc123		|
|demo@techfios.com	|abc124		|
|demo1@techfios.com	|abc124		|
|demo@techfios.com	|					|
|										|abc123		|
|demo1@techfios.com	|					|
|										|abc1234	|
|										|					|
