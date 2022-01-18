package com.macys.macysdemo;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.belk.bean.Customer;
import io.cucumber.messages.internal.com.google.gson.Gson;

public class Common {

	static WebDriver driver;
	
	static Logger log = Logger.getLogger(Common.class.getName());

	public static WebDriver BrowserLaunch(String url) {

		System.setProperty("webdriver.chrome.driver", "chromedriver");
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();

		driver.get(url);
		log.info("Opened the website "+url);
		return driver;
		
	}

	public static WebElement locatorId(String id) {
		log.info("Find element using Id "+id);
		return driver.findElement(By.id(id));
	}

	public static void search(String searchTerm, WebElement webElement) {
		webElement.sendKeys(searchTerm);
		webElement.sendKeys(Keys.RETURN);
		log.info("Find searchterm "+searchTerm);

	}

	public static void wait(int num) {
		driver.manage().timeouts().implicitlyWait(num, TimeUnit.SECONDS);
		
	}

	public static WebElement locatorName(String name) {
		log.info("find by locatorName "+name);
		return driver.findElement(By.name(name));
		
	}

	public static List<WebElement> locatorTagName(String name) {
		log.info("find by tagNAme "+name);
		return driver.findElements(By.tagName(name));
		
	}

	public static WebElement locatorXpath(String Xpath) {
		log.info("find by Xpath "+Xpath);
		return driver.findElement(By.xpath(Xpath));
	}

	public static Customer readjson(String filepath, int index) {
		JSONParser parser = new JSONParser();
		
		try {
			JSONArray customerArray = (JSONArray) parser.parse(new FileReader(filepath));
			JSONObject person = (JSONObject) customerArray.get(index - 1);
			Customer customer = new Gson().fromJson(person.toJSONString(), Customer.class);
			log.info("found customer from position "+index);
			return customer;
		}

		catch (IOException e) {
			log.info("File IOException occured while reading json ");
			return null;
		}catch (ParseException e) {
			log.info("Exception occured while parseing the json file");
			return null;
		}
	}
	
	public static JSONObject readjsonWithoutPOJO(String filepath, int index) {
		JSONParser parser = new JSONParser();
		
		try {
			JSONArray customerArray = (JSONArray) parser.parse(new FileReader(filepath));
			JSONObject person = (JSONObject) customerArray.get(0);
			System.out.println("first name "+person.get("firstName"));
			System.out.println("last name "+person.get("lastName"));
			JSONObject address = (JSONObject) person.get("address");
			System.out.println("street name "+address.get("streetAddress"));
			System.out.println("postCode "+address.get("postCode"));
			JSONObject phone = (JSONObject) person.get("phoneNumber");
			System.out.println("mob "+phone.get("mob"));
			return person;
		}

		catch (IOException e) {
			return null;
		}catch (ParseException e) {
			return null;
		}
	}

}
