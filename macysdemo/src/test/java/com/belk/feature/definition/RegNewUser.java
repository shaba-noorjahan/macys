package com.belk.feature.definition;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import com.belk.bean.Customer;
import com.macys.macysdemo.Common;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

public class RegNewUser {
	static Logger log = Logger.getLogger(RegNewUser.class.getName());

	@When("I click on the my account")
	public void step2() {
		Common.ReadProperty();
		Common.BrowserLaunch(Common.p.getProperty("myaccount_url"));
		// Common.BrowserLaunch("https://www.belk.com/create-account-registry");
		// log.logp(Level., sourceClass, sourceMethod, msg);

	}

	@And("enter the details of the user {int}")
	public void step3(int index) {

		Common.readjson("Resources/customer.json");
		Customer customer = Common.getCustomer(index);
		try {

			Common.locatorId("dwfrm_profile_customer_firstname").sendKeys(customer.getFirstName());

			Common.locatorId("dwfrm_profile_customer_lastname").sendKeys(customer.getLastName());

			Common.locatorId("dwfrm_profile_customer_phone").sendKeys(customer.getPhoneNumber().getMob());

			Common.locatorName("dwfrm_profile_customer_email").sendKeys(customer.getEmailAddress());

			Common.locatorId("reg-pwd-inp").sendKeys("mystiquex");

		} catch (Exception e) {

			log.error("Exception occured while writing data");
		}
	}

//	@And("dynamic value")
//	public void step4(DataTable dynamicData) {
//		List<Map<String, String>> data = dynamicData.asMaps(String.class, String.class);
//
//		System.out.println(" value is " + data.get(0).get("test"));
//		System.out.println(" value2 is " + data.get(0).get("test2"));
//		System.out.println("   value " + data);
//	}
//
//
//	@Test
//	@When("test dynamic value {string} and {string}")
//	public void test_dynamic_value_and(String string, String string2) {
//		System.out.println(":::::::::; valueasdfasdf  is " + string + " " + string2);
//	}
}
