package test;

import org.testng.annotations.Test;

import base.Utils;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;

public class CreateAccount extends Utils { // can check email
	private WebDriver driver;
	private By signInLocator, inputEmailLoc, createAccButtonLoc, createAccTextLoc, errorEmailTextLoc;
	private By personalInfoLoc, genderButtonLoc, firstNameInputLoc, lastNameInputLoc, passwordInputLoc;
	private By frtnameAddressInputLoc, lstnameAddressInputLoc, addressInputLoc, cityInputLoc;
	private By stateSelectLoc, zipCodeInputLoc, countryInputLoc, mobileInputLoc, assignAddressInputLoc;
	private By registerButtonLoc, createAccSuccessTextLoc;

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", "C://Users//LQA//Downloads//setting//chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://automationpractice.com/index.php");

		initField();
	}

	private void initField() {
		signInLocator = By.xpath("//*[@class=\"header_user_info\"]");
		inputEmailLoc = By.xpath("//*[@id=\"email_create\"]");
		createAccButtonLoc = By.xpath("//*[@id=\"SubmitCreate\"]");
		createAccTextLoc = By.xpath("//*[text()=\"Create an account\"]");
		errorEmailTextLoc = By.xpath("//*[@id=\"create_account_error\"]");
		//
		personalInfoLoc = By.xpath("//*[text()=\"Your personal information\"]");
		genderButtonLoc = By.xpath("//*[@id=\"id_gender1\"]");
		firstNameInputLoc = By.xpath("//*[@id=\"customer_firstname\"]");
		lastNameInputLoc = By.xpath("//*[@id=\"customer_lastname\"]");
		passwordInputLoc = By.xpath("//*[@id=\"passwd\"]");
		frtnameAddressInputLoc = By.xpath("//*[@id=\"firstname\"]");
		lstnameAddressInputLoc = By.xpath("//*[@id=\"lastname\"]");
		addressInputLoc = By.xpath("//*[@id=\"address1\"]");
		cityInputLoc = By.xpath("//*[@id=\"city\"]");
		stateSelectLoc = By.xpath("//*[@id=\"id_state\"]");
		zipCodeInputLoc = By.xpath("//*[@id=\"postcode\"]");
		countryInputLoc = By.xpath("//*[@id=\"id_country\"]");
		mobileInputLoc = By.xpath("//*[@id=\"phone_mobile\"]");
		assignAddressInputLoc = By.xpath("//*[@id=\"alias\"]");
		registerButtonLoc = By.xpath("//*[@id=\"submitAccount\"]");
		createAccSuccessTextLoc = By.xpath("//*[@class=\"info-account\"]");
		// TODO Auto-generated method stub

	}

	@Test
	public void createAccountFail() {
		click(driver, signInLocator);
		explicitWaitVisible(driver, createAccTextLoc, TIMEOUT_10000);
		sendKey(driver, inputEmailLoc, "abc");
		click(driver, createAccButtonLoc);
		assertEquals(isElemenViseble(driver, errorEmailTextLoc, TIMEOUT_10000, ""), true);
	}

	@Test
	public void createAccountSuccess() {
		click(driver, signInLocator);
		explicitWaitVisible(driver, createAccTextLoc, TIMEOUT_10000);
		sendKey(driver, inputEmailLoc, "phuthanhphat@ymail.com"); // can thay mot email moi moi lan chay
		click(driver, createAccButtonLoc);
		isElemenViseble(driver, personalInfoLoc, TIMEOUT_10000, "You need change new email before test !");
		click(driver, genderButtonLoc);
		sendKey(driver, firstNameInputLoc, "T");
		sendKey(driver, lastNameInputLoc, "T");
		sendKey(driver, passwordInputLoc, "T12345678");
		sendKey(driver, frtnameAddressInputLoc, "T");
		sendKey(driver, lstnameAddressInputLoc, "T");
		sendKey(driver, addressInputLoc, "T");
		sendKey(driver, cityInputLoc, "T");
		selectByIndex(driver, stateSelectLoc, 1);
		sendKey(driver, zipCodeInputLoc, "10001");
		selectByIndex(driver, countryInputLoc, 1);
		sendKey(driver, mobileInputLoc, "214212345");
		sendKey(driver, assignAddressInputLoc, "acc@delta.com.vn");
		click(driver, registerButtonLoc);
		assertEquals(isElemenViseble(driver, createAccSuccessTextLoc, TIMEOUT_10000, ""), true);
	}

	@AfterMethod
	public void afterMethod() {
		driver.close();
	}
}
