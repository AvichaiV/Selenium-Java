package Resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class base {

	public WebDriver driver;
	public Properties prop;

	public WebDriver initializeDriver() throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/Resources/data.properties");
		String stBase = System.getProperty("user.dir");
		prop.load(fis);
		String propfrommven = prop.getProperty("getpropfrommven");
		String browserName;
		if (propfrommven.equals("1")) {
			browserName = System.getProperty("browser");			
		}
		else {
			browserName = prop.getProperty("browser");
		}
		
		
		String useSeleniumGrid = prop.getProperty("usegrid");
		
		// Chrome
		if (browserName.equalsIgnoreCase("Chrome")) {
			if (useSeleniumGrid.contentEquals("1")) {
				DesiredCapabilities dc = new DesiredCapabilities();
				final ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--headless");
				chromeOptions.addArguments("--start-maximized");
				dc.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
				dc.setBrowserName("chrome");
				dc.setPlatform(Platform.LINUX);
				driver = new RemoteWebDriver(new URL("http://172.17.0.2:4444/wd/hub"),dc);
			}
			
			else {
				String driverPath = stBase +"/drivers/chromedriver";
				final ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--start-maximized");
				System.setProperty("webdriver.chrome.driver", driverPath);
				driver = new ChromeDriver(chromeOptions);
			}
			
			
		}
		// FireFox
		else if (browserName.equalsIgnoreCase("FireFox")) {
			if (useSeleniumGrid.contentEquals("1")) {
				DesiredCapabilities dc = new DesiredCapabilities();
				dc.setBrowserName("firefox");
				dc.setPlatform(Platform.LINUX);
				driver = new RemoteWebDriver(new URL("http://172.17.0.2:4444/wd/hub"),dc);
			}
			else {
				String driverPath = stBase + "/drivers/geckodriver";
				System.setProperty("webdriver.gecko.driver", driverPath);
				driver = new FirefoxDriver();
			}
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	public String getScreenshotPath(String testCaseName,WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs((OutputType.FILE));
		String destenetionFile = System.getProperty("user.dir") + "/reports/" + testCaseName + ".png";
		FileUtils.copyFile(source,new File(destenetionFile));
		return destenetionFile;
		
		
		
	
	}

}
