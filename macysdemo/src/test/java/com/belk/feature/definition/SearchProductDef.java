package com.belk.feature.definition;

import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.macys.macysdemo.Common;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class SearchProductDef {

	static Logger log = Logger.getLogger(SearchProductDef.class.getName());

	@Given("I am on home page")
	public void step1() {
		Common.ReadProperty();
		Common.BrowserLaunch(Common.p.getProperty("homepage_url"));

		Common.wait(5);
	}

	@And("I close the pop up")
	public void step2() {
		try {
			Common.wait(5);
			Common.locatorXpath("//*[@id=\"bx-close-inside-1418784\"]").click();
		} catch (Exception e) {
			log.info("No pop up");
		}
		Common.wait(5);
	}
    @And("^I search for product ([^\"]*)$")
    public void step3(String search){
    	try {
    	WebElement webElement = Common.locatorTagName("input").get(0);
		Common.search(search, webElement);
		Common.wait(5);
    }
    	catch (Exception e) {
    		log.info("Exception occured when searching for product");
    	}
    }

//	@And("I search for product {string}")
//	public void step3(String search) {
//		WebElement webElement = Common.locatorTagName("input").get(0);
//		Common.search(search, webElement);
//		Common.wait(5);
//	}

	@Then("I land on product detail page {int}")
	public void step4(int index) {
		WebElement webElement = Common.locatorXpath("//*[@id=\"search-result-items\"]/li["+index+"]");
		if (null != webElement ) {
		Common.wait(10);
		webElement.findElements(By.tagName("a")).stream()
				.filter(tag -> tag.getAttribute("class").equalsIgnoreCase("product-link"))
				.findFirst().get().sendKeys(Keys.RETURN);
		Common.wait(5);
		}
		else {
			log.info("Exception occured in pdp");
		}
	}
   @Then ("I scroll to the bottom")
   public void step5() {
	  Common.scrollDown();
   }
   
}
