

Feature: Home page to Product detail page

  
  Scenario:  Landing on product detail page
    Given I am on home page
    And I close the pop up
    When I search for product <searchTearm>
    And I land on product detail page 1
    Then I scroll to the bottom
   
	Examples:
	|searchTearm|
	#|turmeric powder|
	|kids dress|
	#|mens dress|

 