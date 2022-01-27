package com.macys.macysdemo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

	public static Properties p;

	static List<Customer> customers;

	public static WebDriver BrowserLaunch(String url) {

		System.setProperty("webdriver.chrome.driver", "chromedriver");
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();

		driver.get(url);
		log.info("Opened the website " + url);
		return driver;

	}

	public static void ReadProperty() {
		p = new Properties();
		try {
			p.load(new FileInputStream("Resources/data.properties"));
		} catch (FileNotFoundException e) {
			
			log.error("property file not found");
		} catch (IOException e) {
			
			log.error("Exception while reading property file");
		}
	}

	public static WebElement locatorId(String id) {
		try {
			log.info("Find element using Id " + id);
			return driver.findElement(By.id(id));
		} catch (Exception e) {
			log.error("Error occured in Find element using Id "+id);
			return null;
		}
	}

	public static void search(String searchTerm, WebElement webElement) {
		webElement.sendKeys(searchTerm);
		webElement.sendKeys(Keys.RETURN);
		log.info("Find searchterm " + searchTerm);

	}

	public static void wait(int num) {
		driver.manage().timeouts().implicitlyWait(num, TimeUnit.SECONDS);

	}

	public static WebElement locatorName(String name) {
		try {
			log.info("find by locatorName " + name);
			return driver.findElement(By.name(name));

		} catch (Exception e) {
			log.error("Exception occured for String Name");
			return null;
		}
	}

	public static List<WebElement> locatorTagName(String name) {
		log.info("find by tagNAme " + name);
		return driver.findElements(By.tagName(name));

	}

	public static WebElement locatorXpath(String Xpath) {
		try {
			log.info("find by Xpath " + Xpath);
			return driver.findElement(By.xpath(Xpath));
		} catch (Exception e) {
			log.error("Given Xpath does not excist");
			return null;

		}
	}

	public static void readjson(String filepath) {
		JSONParser parser = new JSONParser();

		try {
			JSONArray customerArray = (JSONArray) parser.parse(new FileReader(filepath));
			customers = new ArrayList<>();
			for (int i = 0; i < customerArray.size(); i++) {
				JSONObject person = (JSONObject) customerArray.get(i);
				customers.add(new Gson().fromJson(person.toJSONString(), Customer.class));
			}

			log.info("found custoreturn customer");
		}

		catch (IOException e) {
			log.error("File IOException occured while reading json ");

		} catch (ParseException e) {
			log.error("Exception occured while parseing the json file");

		}
	}

	public static Customer getCustomer(int c) {
		if (null != customers && c <= customers.size() && c >= 0) {
			return customers.get(c - 1);
		} else {
			log.error("provided customer index is more than the given list");
			return null;
		}
	}

	public static JSONObject readjsonWithoutPOJO(String filepath, int index) {
		JSONParser parser = new JSONParser();

		try {
			JSONArray customerArray = (JSONArray) parser.parse(new FileReader(filepath));
			JSONObject person = (JSONObject) customerArray.get(0);

			JSONObject address = (JSONObject) person.get("address");

			JSONObject phone = (JSONObject) person.get("phoneNumber");

			return person;
		}

		catch (IOException e) {
			return null;
		} catch (ParseException e) {
			return null;
		}
	}

	public static void scrollDown() {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

	}

}
