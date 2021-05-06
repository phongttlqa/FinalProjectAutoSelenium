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

public class Search extends Utils {
	private WebDriver driver;
	private By searchLoc, hintResultSearch;
	private By searchButtonLoc, resultSearch;
	private By counterProduct;
	private By noResults, price;
	private String textSearch = "dress", textSearchIncorrect = "dresSSss";

	@Test
	public void checkDefaultSearchText() {
		assert getTextByAttribute(driver, searchLoc, "placeholder").equals("Search")
				: "Don't show default search text !";
		sendKey(driver, searchLoc, textSearch);// input text search
		assert !getTextByAttribute(driver, searchLoc, "value").contains("Search") : "Still show default search text !";
		clearText(driver, searchLoc);
		assert getTextByAttribute(driver, searchLoc, "placeholder").equals("Search")
				: "Don't show default search text !";
		assert !getTextByAttribute(driver, searchLoc, "value").contains(textSearch) : "Don't delete search text !";
	}

	@Test
	public void checkHintSearch() {
		sendKey(driver, searchLoc, textSearch);
		explicitWaitVisible(driver, hintResultSearch, TIMEOUT_10000);
		String hint = getText(driver, By.xpath("//*[@class=\"ac_results\"]/ul/li"));
		System.out.println(hint);
		assert hint.contains("dress") || hint.contains("Dress") : "Hint wrong !";
	}

	@Test
	public void checkNameProduct() {
		sendKey(driver, searchLoc, textSearch);
		explicitWaitVisible(driver, hintResultSearch, TIMEOUT_10000);
		String hint = getHint(textSearch); // get hints
		search(hint);
		List<WebElement> products = getListElement(driver, By.xpath("//*[@itemprop=\"name\"]/a"));
		assert products.size() > 0 : "Not product results !";
		String resultString = products.get(0).getText();
		assert resultString.contains(hint) || hint.contains(resultString) : "Product results wrong!";
	}

	@Test
	public void checkProductNumber() {
		sendKey(driver, searchLoc, textSearch);
		explicitWaitVisible(driver, hintResultSearch, TIMEOUT_10000);
		search(getHint(getHint(textSearch)));
		int count = getListElement(driver, By.xpath("//*[@class=\"product_list grid row\"]/li")).size();
		assert Integer.valueOf(getText(driver, counterProduct).substring(0, 1)) == count : "Count product wrong !";

	}

	@Test
	public void checkPrice() {
		sendKey(driver, searchLoc, textSearch);
		explicitWaitVisible(driver, hintResultSearch, TIMEOUT_10000);
		search(getHint(getHint(textSearch)));
		List<WebElement> prods = getListElement(driver, price);
		assert prods.get(1).getText().contains("$") : "Don't show price !";
	}

	@Test
	public void inputTextSearchIncorrect() {
		sendKey(driver, searchLoc, textSearchIncorrect);
		click(driver, searchButtonLoc);
		explicitWaitVisible(driver, noResults, TIMEOUT_10000);
		assert getText(driver, noResults).equals("No results were found for your search \"dresSSss\"")
				: "Case input incorrect text search : Wrong !";
	}

	private String getHint(String s) {
		List<WebElement> elements = getListElement(driver, By.xpath("//*[@class=\"ac_results\"]/ul/li"));
		boolean flag = false;
		String text = null;
		for (WebElement e : elements) {
			if (e.getText().contains(s) || e.getText().contains("Dress")) {
				text = e.getText();
				flag = true;
				break;
			}
		}
		assert flag == true : "Not fit hint !";
		return text;
	}

	public void search(String s) {
		clearText(driver, searchLoc);
		sendKey(driver, searchLoc, s);
		click(driver, searchButtonLoc);
		explicitWaitVisible(driver, resultSearch, TIMEOUT_10000);
	}

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", "C://Users//LQA//Downloads//setting//chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://automationpractice.com/index.php");

		initField();
	}

	private void initField() {
		searchLoc = By.xpath("//*[@id=\"search_query_top\"]");
		hintResultSearch = By.xpath("//*[@class=\"ac_results\"]");
		searchButtonLoc = By.xpath("//*[@class=\"btn btn-default button-search\"]");
		resultSearch = By.xpath("//*[@class=\"product_list grid row\"]");
		counterProduct = By.xpath("//*[@class=\"heading-counter\"]");
		noResults = By.xpath("//*[@class=\"alert alert-warning\"]");
		price = By.xpath("//div[@class=\"content_price\"]/span[@itemprop=\"price\"]");
		// TODO Auto-generated method stub

	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}
