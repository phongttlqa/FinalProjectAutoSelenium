package test;

import org.testng.annotations.Test;
import base.Utils;

import org.testng.annotations.BeforeMethod;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

public class NewsLetter extends Utils { // can check email
	private WebDriver driver;
	private By newLetterInputLoc, successMessLoc, submitNewsletterLoc;

	@Test
	public void newsletter() {
		sendKey(driver,newLetterInputLoc, "xuanhieu@xuanhieugroup.com.vn"); // can thay mot email moi moi lan chay
		click(driver,submitNewsletterLoc);
		isElemenViseble(driver, successMessLoc, TIMEOUT_10000, "You need change new email before test !");
		assert getText(driver,successMessLoc)
				.equals("Newsletter : You have successfully subscribed to this newsletter.") : "News Letter Error";
	}

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", "C://Users//LQA//Downloads//setting//chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://automationpractice.com/index.php");
		newLetterInputLoc = By.xpath("//*[@id=\"newsletter-input\"]");
		successMessLoc = By.xpath("//*[@class=\"alert alert-success\"]");
		submitNewsletterLoc = By.xpath("//*[@name=\"submitNewsletter\"]");
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}
