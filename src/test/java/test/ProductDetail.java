package test;

import org.testng.annotations.Test;

import base.Utils;

import org.testng.annotations.BeforeMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

public class ProductDetail extends Utils {
	private WebDriver driver;
	private By listImageProductBy, bigpicBy, viewLargeBy, closeBy, nameProduct;

	private By quantityInputBy, addtoCartButtonBy, nullQuantityBy;
	private By addToCartWithQuantity;

	private By logoBy, loginBy, writeReviewBy, reviewTitleBy, reviewContentBy, submitBy;

	private By sendToFrdBy, nameFrdBy, emailFrdBy, sendEmailBy;
	
	private By twitterId, twitterPass, loginTw, shareTwBy;

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", "C://Users//LQA//Downloads//setting//chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://automationpractice.com/index.php");
		driver.manage().window().maximize();
		
		initField();
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

	private void initField() {
		listImageProductBy = By.xpath("//ul[@id=\"homefeatured\"]//a[@class=\"product_img_link\"]/img");
		bigpicBy = By.xpath("//*[@id=\"bigpic\"]");
		viewLargeBy = By.xpath("//*[@class=\"span_link no-print\"]");
		closeBy = By.xpath("//*[@title=\"Close\"]");
		nameProduct = By.xpath("//*[@class=\"child\"]");

		quantityInputBy = By.xpath("//form//input[@id=\"quantity_wanted\"]");
		addtoCartButtonBy = By.xpath("//p[@id=\"add_to_cart\"]/button");
		nullQuantityBy = By.xpath("//*[@title=\"Close\"]");
		addToCartWithQuantity = By.xpath("//div//i[@class=\"icon-ok\"]");

		loginBy = By.xpath("//div[@class=\"header_user_info\"]");
		logoBy = By.xpath("//*[@id=\"header_logo\"]");
		writeReviewBy = By.xpath("//a[@class=\"open-comment-form\"]");
		reviewTitleBy = By.xpath("//*[@id=\"comment_title\"]");
		reviewContentBy = By.xpath("//*[@id=\"content\"]");
		submitBy = By.xpath("//*[@id=\"submitNewMessage\"]");

		sendToFrdBy = By.xpath("//*[@id=\"send_friend_button\"]");
		nameFrdBy = By.xpath("//*[@id=\"friend_name\"]");
		emailFrdBy = By.xpath("//*[@id=\"friend_email\"]");
		sendEmailBy = By.xpath("//*[@id=\"sendEmail\"]");
		
		shareTwBy = By.xpath("//button[@class=\"btn btn-default btn-twitter\"]");
		twitterId =  By.xpath("//input[@name='session[username_or_email]']");
		twitterPass = By.xpath("//input[@name='session[password]']");
		loginTw = By.xpath("//*[@id=\"layers\"]/div[2]/div/div/div/div/div/div[2]/div[2]/div/div[2]/div[2]/div/span/span/span");
		
	}

	@Test
	public void viewLargeImage() {
		clickProduct();
		click(driver, bigpicBy);
		assert isElemenViseble(driver, closeBy, TIMEOUT_10000, "") : "Click image -> Zoom image failed !";
		assert isElemenViseble(driver, nameProduct, TIMEOUT_10000, "") : "Product name don't show below image !";
		click(driver, closeBy);
		click(driver, viewLargeBy);
		assert isElemenViseble(driver, closeBy, TIMEOUT_10000, "") : "Click viewlarge -> Zoom image failed !";
		assert isElemenViseble(driver, nameProduct, TIMEOUT_10000, "") : "Product name don't show below image !";
		click(driver, closeBy);
	}

	@Test
	public void nullQuantity() {
		clickProduct();
		explicitWaitVisible(driver, quantityInputBy, TIMEOUT_10000); // luc pass luc fail
		clearTextAndAddNew(driver, quantityInputBy, "0");
		click(driver, addtoCartButtonBy);
		assert isElemenViseble(driver, nullQuantityBy, TIMEOUT_10000, "") : "Null quantity failed !";
		click(driver, nullQuantityBy);
	}

	@Test
	public void quantity() {
		nullQuantity();
		clearTextAndAddNew(driver, quantityInputBy, "1");
		click(driver, addtoCartButtonBy);
		assert isElemenViseble(driver, addToCartWithQuantity, TIMEOUT_10000, "")
				: "Add product with quantity > 0 wrong !";
	}

	@Test
	public void shareTwitter() {
		clickProduct();
		click(driver, shareTwBy);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		explicitWaitVisible(driver,twitterId, TIMEOUT_10000);
		sendKey(driver,twitterId, "phongtt.lqa@gmail.com");
		sendKey(driver, twitterPass, "123456@Abc");
		click(driver, loginTw);
		pause(TIMEOUT_10000);
		driver.close();
		driver.switchTo().window(tabs.get(0));
	}
	

	@Test
	public void writeComment() {
		click(driver, loginBy);
		new Login(driver).login();
		click(driver, logoBy);
		clickProduct();
		click(driver, writeReviewBy);
		sendKey(driver, reviewTitleBy, "Title Test");
		sendKey(driver, reviewContentBy, "Comment Test");
		click(driver, submitBy);
		assert isElemenInviseble(driver, submitBy, TIMEOUT_10000, "write review error !");
	}

	@Test
	public void sendToFrend() {
		clickProduct();
		click(driver, sendToFrdBy);
		sendKey(driver, nameFrdBy, "Test Name");
		sendKey(driver, emailFrdBy, "info@ctctrans.vn");
		click(driver, sendEmailBy);
		assert isElemenInviseble(driver, sendEmailBy, TIMEOUT_10000, "Send email Frend error !");
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

	private List<WebElement> getProductList() {
		return getListElement(driver, listImageProductBy);
	}

	private void clickProduct() {
		List<WebElement> images = getProductList();
		images.get(0).click();
	}

}
