package com.belk.feature.definition;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.macys.macysdemo.Common;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ShoppingBagDef {

	@Given("I am on PDP")
	public void step1() {
		Common.BrowserLaunch("https://www.belk.com/p/ronni-nicole-womens-sleeveless-tiered-stripe-babydoll-dress/1500172V217302.html?dwvar_1500172V217302_color=121370507141#q_redirect=womens+dress&start=2");
	    
	}
	
	 @When("I select the size\\/color\\/quantity")
	    public void step5(){
	    	WebElement 	webElement = Common.locatorId("va-size");
			webElement.click();
			Select s = new Select(webElement);
			s.selectByIndex(1);
			webElement.sendKeys(Keys.RETURN);	
			Common.wait(5);
	    }
	    @And("I click the add to Bag button")
	    public void step6(){
	    	try {
				Common.locatorXpath("//*[@id=\"add-to-cart\"]").click();
			} catch (Exception e) {

			}	
	    	Common.wait(5);
	    }
	    @Then("the product is added to my shopping Bag")
	    public void step7(){
	    	Common.locatorXpath("//*[@id=\"dialog-container\"]/div[2]/div[2]/div[3]/a[1]/sh-button/button")
			.sendKeys(Keys.RETURN);
	    	Common.wait(5);
	    }
	
	
	
}
