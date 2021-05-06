package test;

import org.testng.annotations.Test;

import base.Utils;

import org.testng.annotations.BeforeMethod;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

public class MuaHang extends Utils {
	private WebDriver driver;
	private By listProductBy, addToCartBy, continueShoppingBy;
	private By processCheckoutBy, checkoutBy, deliveryCheckboxBy;
	private By priceBy, totalPriceBy, paymentBy, completeMessBy;
	private float totalPrice;

	private By homeBy, plusBy, deleteProdBy, closeMessBy;

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", "C://Users//LQA//Downloads//setting//chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://automationpractice.com/index.php");
		driver.manage().window().maximize();

		initField();
	}

	private void initField() {
		listProductBy = By.xpath("//ul[@id=\"homefeatured\"]/li");
		addToCartBy = By.xpath("//*[@id=\"add_to_cart\"]/button");
		continueShoppingBy = By.xpath("//div[@class=\"button-container\"]//span[@title=\"Continue shopping\"]");
		processCheckoutBy = By.xpath("//div[@class=\"button-container\"]//a[@title=\"Proceed to checkout\"]");
		priceBy = By.xpath("//*[@id=\"our_price_display\"]");
		totalPriceBy = By.xpath("//*[@id=\"total_product\"]");
		checkoutBy = By.xpath("//p[@class=\"cart_navigation clearfix\"]//button");
		deliveryCheckboxBy = By.xpath("//*[@id=\"cgv\"]");
		paymentBy = By.xpath("//*[@title=\"Pay by bank wire\"]");
		completeMessBy = By.xpath("//p[@class=\"cheque-indent\"]");

		homeBy = By.xpath("//a[@title=\"My Store\"]");
		plusBy = By.xpath("//a[@title=\"Add\"]");
		deleteProdBy = By.xpath("//a[@title=\"Delete\"]");
		closeMessBy = By.xpath("//*[@title=\"Close\"]");
		// TODO Auto-generated method stub

	}

	@Test
	public void purchaseSuccess() { /* Mua hang thanh cong */
		List<WebElement> prodsElements = getProductList();
		totalPrice = 0;
		prodsElements.get(0).click();
		assert isElemenViseble(driver, addToCartBy, TIMEOUT_10000, "") : "Don't show product details!";
		purchaseContinue();
		purchaseContinue();
		purchaseCheckout();
		assert isElemenViseble(driver, totalPriceBy, TIMEOUT_10000, "") : "Don't show total price !";
		// check total price
		assert formatPrice(getText(driver, totalPriceBy)) == totalPrice : "Total price wrong !";
		checkoutforPayment();
	}

	private void checkoutforPayment() {
		click(driver, By.xpath("//p//a[@title=\"Proceed to checkout\"]"));
		new Login(driver).login(); // login
		click(driver, checkoutBy); // next
		click(driver, checkoutBy); // next

		assert isElemenViseble(driver, closeMessBy, TIMEOUT_10000, "") : "Close message do not show !";
		click(driver, closeMessBy);

		click(driver, deliveryCheckboxBy); // checkbox
		click(driver, checkoutBy); // next
		click(driver, paymentBy);// choose pay method
		click(driver, checkoutBy); // next
		assert getText(driver, completeMessBy).equals("Your order on My Store is complete.") : "Purchase failed !";
	}

	private void purchaseCheckout() {
		click(driver, addToCartBy);
		totalPrice += formatPrice(getText(driver, priceBy));
		assert isElemenViseble(driver, continueShoppingBy, TIMEOUT_10000, "") : "Don't show product details!";
		click(driver, processCheckoutBy);
	}

	private void purchaseContinue() {
		click(driver, addToCartBy);
		totalPrice += formatPrice(getText(driver, priceBy));
		assert isElemenViseble(driver, continueShoppingBy, TIMEOUT_10000, "") : "Don't show product details!";
		click(driver, continueShoppingBy);
		assert isElemenInviseble(driver, continueShoppingBy, TIMEOUT_10000, "") : "Still show product cart!";
	}

	private float formatPrice(String price) {
		return Float.valueOf(price.substring(0 + 1, price.length()));
	}

	private List<WebElement> getProductList() {
		return getListElement(driver, listProductBy);
	}

	@Test
	public void changePurchaseInfo() { /* Thay doi thong tin mua hang */
		// add 5 prods in to cart
		for (int i = 0; i < 4; i++) {
			List<WebElement> prodsElements = getProductList();
			prodsElements.get(i).click();
			purchaseContinue();
			click(driver, homeBy);
		}
		List<WebElement> prodsElements = getProductList();
		prodsElements.get(4).click();
		purchaseCheckout();

		// tang so luong tu 1 len 3
		List<WebElement> listAddEls = getListElement(driver, plusBy);
		listAddEls.get(0).click(); // ichiban me no item
		listAddEls.get(0).click();

		// xoa 1 san pham
		List<WebElement> listDelEles = getListElement(driver, deleteProdBy);
		listDelEles.get(3).click(); // sanban me no item
		checkoutforPayment();
	}

	@Test
	public void promotionPurchase() {/* Mua hang khuyen mai */
		List<WebElement> prodsElements = getProductList();
		for (WebElement p : prodsElements) {
			if (p.getText().contains("20%")) {
				p.click();
				break;
			}
		}
		purchaseCheckout();
		checkoutforPayment();
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}
