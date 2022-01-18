

Feature: Home page to Product detail page

  
  Scenario:  Landing on product detail page
    Given I am on home page
    And I close the pop up
    And I search for product <searchTearm>
    Then I land on product detail page 
   
	Examples:
	|searchTearm|
	|womens dress|
	|kids dress|
	|mens dress|

 