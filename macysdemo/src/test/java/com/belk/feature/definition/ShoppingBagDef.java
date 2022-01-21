package com.belk.feature.definition;

import java.util.logging.Logger;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.macys.macysdemo.Common;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class ShoppingBagDef {
	static Logger log = Logger.getLogger(ShoppingBagDef.class.getName());

	@Given("I am on PDP")
	public void step1() {
		Common.ReadProperty();
		Common.BrowserLaunch(Common.p.getProperty("pdp_url"));
		// Common.BrowserLaunch("https://www.belk.com/p/ronni-nicole-womens-sleeveless-tiered-stripe-babydoll-dress/1500172V217302.html?dwvar_1500172V217302_color=121370507141#q_redirect=womens+dress&start=2");

	}

	@When("I select the size\\/color\\/quantity {int}")
	public void step5(int index) {

		WebElement webElement = Common.locatorId("va-size");
		if (null != webElement) {
			webElement.click();
			Select s = new Select(webElement);
			if (s.getOptions().size() >= index) {
				s.selectByIndex(index);
				webElement.sendKeys(Keys.RETURN);
			} else {
				log.info("Given index is not valid");
			}
			Common.wait(5);
		} else {
			log.info("size not found");
		}
	}

	@And("I click the add to Bag button")
	public void step6() {
		try {
			Common.locatorXpath("//*[@id=\"add-to-cart\"]").click();
		} catch (Exception e) {
			log.info("Exception occured while adding to bag");
		}
		Common.wait(5);
	}

	@Then("the product is added to my shopping Bag")
	public void step7() {
		try {
			Common.locatorXpath("//*[@id=\"dialog-container\"]/div[2]/div[2]/div[3]/a[1]/sh-button/button")
					.sendKeys(Keys.RETURN);
			Common.wait(5);
		} catch (Exception e) {
			log.info("Exception occured while adding to cart");
		}

	}
	@And ("I check the price")
	public void step8() {
	String price=Common.locatorXpath("//*[@id=\"primary\"]/div[3]/div[1]/div/table/tbody/tr[2]/td[2]").getText();
		Assert.assertEquals("$20.40", price);
			
		
	}

}
