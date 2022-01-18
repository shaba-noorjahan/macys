package com.belk.feature.definition;

import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.macys.macysdemo.Common;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchProductDef {

	static Logger log = Logger.getLogger(SearchProductDef.class.getName());

	@Given("I am on home page")
	public void step1() {
		Common.BrowserLaunch("https://www.belk.com//");

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
    	WebElement webElement = Common.locatorTagName("input").get(0);
		Common.search(search, webElement);
		Common.wait(5);
    }

//	@And("I search for product {string}")
//	public void step3(String search) {
//		WebElement webElement = Common.locatorTagName("input").get(0);
//		Common.search(search, webElement);
//		Common.wait(5);
//	}

	@Then("I land on product detail page")
	public void step4() {
		WebElement webElement = Common.locatorXpath("//*[@id=\"search-result-items\"]/li[2]");
		Common.wait(10);
		webElement.findElements(By.tagName("a")).stream()
				.filter(tag -> tag.getAttribute("class").equalsIgnoreCase("product-link"))

				.findFirst().get().sendKeys(Keys.RETURN);
		Common.wait(5);

	}

}
