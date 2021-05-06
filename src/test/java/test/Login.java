package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login {
	
	private WebDriver driver;

	public Login(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	public void login() {
		driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("haphuc.8410@gmail.com");
		driver.findElement(By.xpath("//*[@id=\"passwd\"]")).sendKeys("12345678");
		driver.findElement(By.xpath("//*[@id=\"SubmitLogin\"]")).click();
	}

}
