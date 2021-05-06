package test;

import org.testng.annotations.Test;

import base.Utils;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;

public class ContactUs extends Utils {
	private WebDriver driver;

	private By contactUsLoc, subHeadSelectLoc, messageInputLoc, emailInputLoc, orderInputLoc;
	private By successMessLoc, sendButtonLoc, chooseFileLoc;

	@Test
	public void contactUs() {
		click(driver, contactUsLoc);
		click(driver, subHeadSelectLoc);
		selectByIndex(driver, subHeadSelectLoc, 1);
		sendKey(driver, emailInputLoc, "trungphongptit@gmail.com");
		sendKey(driver, orderInputLoc, "abc ccc");
		sendKey(driver, messageInputLoc, "Test message");
		sendKey(driver, chooseFileLoc, "C:\\Users\\LQA\\Downloads\\apache-maven-3.8.1-bin.zip");
		click(driver, sendButtonLoc);
		explicitWaitVisible(driver, successMessLoc, TIMEOUT_10000);
		assert getText(driver, successMessLoc).equals("Your message has been successfully sent to our team.")
				: "Send message error !";
	}

//	@BeforeMethod
//	@Parameters("browser")
//	public void beforeMethod(String browser) {
//		if (browser.equalsIgnoreCase("firefox")) {
//			System.setProperty("webdriver.gecko.driver", "C://Users//LQA//Downloads//setting//geckodriver.exe");
//			driver = new FirefoxDriver();
//		} else {
//			System.setProperty("webdriver.chrome.driver", "C://Users//LQA//Downloads//setting//chromedriver.exe");
//			driver = new ChromeDriver();
//		}
//		driver.get("http://automationpractice.com/index.php");
//
//		initField();
//	}

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", "C://Users//LQA//Downloads//setting//chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://automationpractice.com/index.php");

		initField();
	}

	private void initField() {
		contactUsLoc = By.xpath("//*[@id=\"contact-link\"]");
		subHeadSelectLoc = By.xpath("//*[@id=\"id_contact\"]");
		emailInputLoc = By.xpath("//*[@id=\"email\"]");
		orderInputLoc = By.xpath("//*[@id=\"id_order\"]");
		chooseFileLoc = By.xpath("//*[@id=\"fileUpload\"]");
		sendButtonLoc = By.xpath("//*[@id=\"submitMessage\"]");
		messageInputLoc = By.xpath("//*[@id=\"message\"]");
		successMessLoc = By.xpath("//*[@class=\"alert alert-success\"]");
		// TODO Auto-generated method stub

	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}
