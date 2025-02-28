package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class globalDriver {
	protected static WebDriver driver;
	protected static WebDriverWait wait;
	protected WebDriver getDriver(){	

		String os = System.getProperty("os.name").toLowerCase();
    	String driverPath = "src/test/resources/drivers/";
    	
    	ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless");
        options.addArguments("--enable-logging");
    	
		if (os.contains("win")) {
			// Windows driver
				if (driver == null) {
					System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
					System.out.println("Windows");
					driver = new ChromeDriver(options);
					wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			}
        
		} else if (os.contains("nix") || os.contains("nux") || os.contains("aix") || os.contains("mac")) {
			// Unix, Linux, or Mac driver
				if (driver == null) {
					System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver");
					System.out.println("Linux");
					driver = new ChromeDriver(options);
					wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			}
		} else {
				throw new IllegalStateException("Unsupported operating system for webdriver setup");
		}
		return driver;
	}
	protected WebDriverWait getWait() {
		if(wait == null) {
			wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		}
		return wait;
	}

}
