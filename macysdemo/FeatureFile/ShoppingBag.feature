
Feature: Adding a product to cart

  
  Scenario: Opening the PDP and adding product to the cart
     Given I am on PDP  
     And I close the pop up
     When  I select the size/color/quantity 
     And   I click the add to Bag button
     Then  the product is added to my shopping Bag
    
    
  