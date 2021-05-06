package base;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {

	public static final long TIMEOUT_10000 = 10000;

	public boolean isElemenViseble(WebDriver driver, By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isElemenViseble(WebDriver driver, By by, long timeOut, String errorMessage) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(timeOut));
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			driver.findElement(by);
			return true;
		} catch (Exception e) {
			System.out.println(errorMessage);
			return false;
		}
	}

	public boolean isAllElemenViseble(WebDriver driver,List<WebElement> list, long timeOut, String errorMessage) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(timeOut));
			wait.until(ExpectedConditions.visibilityOfAllElements(list));
			return true;
		} catch (Exception e) {
			System.out.println(errorMessage);
			return false;
		}
	}
	
	public boolean isElemenInviseble(WebDriver driver, By by, long timeOut, String errorMessage) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(timeOut));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
			return true;
		} catch (Exception e) {
			System.out.println(errorMessage);
			return false;
		}
	}
	

	public WebElement wait(WebDriver driver, By by, long timeout) {
		for (int i = 0; i < timeout / 500; i++) {
			if (isElemenViseble(driver, by)) {
				return driver.findElement(by);
			} else {
				implicitWait(driver, 500); // cho 0.5s check 1 lan.
			}
		}
		return null;
	}
	public void pause(long time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public WebElement waitLoadElement(WebDriver driver, By by, long timeout) { // millis
		for (int i = 0; i < timeout / 500; i++) {
			if (isElemenViseble(driver, by)) {
				return driver.findElement(by);
			} else {
				driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500)); // wait 0.5s
			}
		}
		return null;
	}

	public void explicitWaitVisible(WebDriver driver, By by, long timeout) { // millis
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(timeout));
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public void explicitWaitInvisible(WebDriver driver, By by, long timeout) { // millis
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(timeout));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
	}

	public void implicitWait(WebDriver driver, long timeout) { // millis
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(timeout));
	}

	public void click(WebDriver driver, By by) {
		driver.findElement(by).click();
	}

	public void sendKey(WebDriver driver, By by, String text) {
		driver.findElement(by).sendKeys(text);
	}

	public void selectByIndex(WebDriver driver, By by, int index) {
		new Select(driver.findElement(by)).selectByIndex(index);
	}

	public void selectByTextValue(WebDriver driver, By by, String text) {
		new Select(driver.findElement(by)).selectByValue(text);
	}

	public void selectByVisibleText(WebDriver driver, By by, String text) {
		new Select(driver.findElement(by)).selectByVisibleText(text);
	}

	public String getText(WebDriver driver, By by) {
		return driver.findElement(by).getText();
	}

	public String getTextByAttribute(WebDriver driver, By by, String att) {
		return driver.findElement(by).getAttribute(att);
	}

	public void clearText(WebDriver driver, By by) {
		driver.findElement(by).clear();
	}
	
	public void clearTextAndAddNew(WebDriver driver, By by, String newstring) {
		WebElement element = driver.findElement(by);
		element.clear();
		element.sendKeys(newstring);
	}

	public List<WebElement> getListElement(WebDriver driver, String ulxpath, String tagName) {// get ra 1 list element tu 1 element khac
		WebElement industries = driver.findElement(By.xpath(ulxpath));
		List<WebElement> links = industries.findElements(By.tagName(tagName));
		return links;
	}

	public List<WebElement> getListElement(WebDriver driver, By a) { // get ra 1 list element tu 1 by
		List<WebElement> elts = driver.findElements(a);
		return elts;
	}
}
