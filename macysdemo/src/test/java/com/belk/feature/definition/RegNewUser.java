package com.belk.feature.definition;

import java.util.logging.Logger;

import com.belk.bean.Customer;
import com.macys.macysdemo.Common;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

public class RegNewUser {
	static Logger log = Logger.getLogger(RegNewUser.class.getName());
//	@Given("I am on home page")
//		public void step1() {
//			Common.BrowserLaunch("https://www.belk.com/p/ronni-nicole-womens-sleeveless-tiered-stripe-babydoll-dress/1500172V217302.html?dwvar_1500172V217302_color=121370507141#q_redirect=womens+dress&start=2");
//		    
//	}
 
	@When("I click on the my account")
	public void step2() {
		Common.BrowserLaunch("https://www.belk.com/create-account-registry");

	}

	@And("enter the details of the user {int}")
	public void step3(int index) {

		Customer customer = Common.readjson("Resources/customer.json", index);
		if (null != customer) {
			Common.locatorId("dwfrm_profile_customer_firstname").sendKeys(customer.getFirstName());

			Common.locatorId("dwfrm_profile_customer_lastname").sendKeys(customer.getLastName());

			Common.locatorId("dwfrm_profile_customer_phone").sendKeys(customer.getPhoneNumber().getMob());

			Common.locatorName("dwfrm_profile_customer_email").sendKeys(customer.getEmailAddress());

			Common.locatorId("reg-pwd-inp").sendKeys("mystiquex");

			// Common.locatorId("dwfrm_profile_login_passwordconfirm_d0ehnlxwlklp").sendKeys("mystiquex");
		} else {

			log.info("Customer not found");
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
