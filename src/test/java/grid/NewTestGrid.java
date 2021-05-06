package grid;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class NewTestGrid {
	WebDriver driver;

	@Test
	public void f() {
//		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//		capabilities.setBrowserName("chrome");
//		capabilities.setPlatform(Platform.WINDOWS);
//		try {
//			driver = new RemoteWebDriver(new URL("http://10.10.31.109:4444/wd/hub"), capabilities);
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		driver.get("http://kenh14.vn");
//		System.out.println(driver.getTitle());
	}

	

//	@Test
//	public void f() {
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("disable-infobars");
//		options.setAcceptInsecureCerts(true);
//		options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
//		options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
//		Proxy proxy = new Proxy();
//		proxy.setAutodetect(false);
//		proxy.setHttpProxy("http://10.10.31.109:4444/wd/hub"); 
//		options.setCapability("proxy", proxy); 
//		driver = new ChromeDriver(options);
//		driver.get("http://kenh14.vn");
//	}
}
