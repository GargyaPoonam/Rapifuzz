package browserUtility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	
	
	public static WebDriver getBrowser(WebDriver driver, String browser,  String Url) {
		
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("Safari")) {
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();
		}
		else if(browser.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else
			System.out.println("browser is not supported");
		
		
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

            driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.MINUTES);
            driver.get(Url);
            return driver;
        }
	
	
	public static void teardown(WebDriver driver) {
		driver.quit();
	}
		
	}


