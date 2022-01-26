package com.macys.macysdemo;

import org.apache.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Mainpage {
	
	static Logger log = Logger.getLogger(Mainpage.class.getName());


	public static void main(String[] args) {
		Common.BrowserLaunch("https://www.belk.com//");

		Common.wait(5);
		try {
			Common.locatorXpath("//*[@id=\"bx-close-inside-1418784\"]").click();
		} catch (Exception e) {
			log.error("Exception occured while launching browser");

		}

		WebElement webElement = Common.locatorTagName("input").get(0);
		Common.search("womens dress", webElement);
		Common.wait(5);

		webElement = Common.locatorXpath("//*[@id=\"search-result-items\"]/li[2]");

		Common.wait(10);
		webElement.findElements(By.tagName("a")).stream()
				.filter(tag -> tag.getAttribute("class").equalsIgnoreCase("product-link"))

				.findFirst().get().sendKeys(Keys.RETURN);

		Common.wait(5);
		webElement = Common.locatorId("va-size");
		webElement.click();
		Select s = new Select(webElement);
		s.selectByIndex(1);
		webElement.sendKeys(Keys.RETURN);
		Common.wait(5);
		try {
			Common.locatorXpath("//*[@id=\"add-to-cart\"]").click();
		} catch (Exception e) {
			log.error("Exception occured while selecting size");

		}
		Common.wait(5);

		Common.locatorXpath("//*[@id=\"dialog-container\"]/div[2]/div[2]/div[3]/a[1]/sh-button/button")
				.sendKeys(Keys.RETURN);
		
		
		Common.wait(5);
		
		
		
		
	}

}
