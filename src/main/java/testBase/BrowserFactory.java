package testBase;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {

	// create webdriver object for given browser
	public WebDriver createBrowserInstance(String browser) throws MalformedURLException {

		WebDriver driver = null;

		if (browser.equalsIgnoreCase("chrome")) {
			
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");// to avoid cookies and cashes issues
			driver = new ChromeDriver(options);
			
		} else if (browser.equalsIgnoreCase("firefox")) {
			
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions foptions = new FirefoxOptions();
			foptions.addArguments("-private");
			driver = new FirefoxDriver(foptions);
			
			
		} else if (browser.equalsIgnoreCase("edge")) {
			
			WebDriverManager.edgedriver().setup();
			EdgeOptions eoptions = new EdgeOptions();
			eoptions.addArguments("-private");
			driver = new EdgeDriver(eoptions);
			
		} else if (browser.equalsIgnoreCase("docker")) {
						
			  DesiredCapabilities dr = new DesiredCapabilities();
			  WebDriverManager.chromedriver().setup();
			   dr.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
			  dr.setAcceptInsecureCerts(true); 
			  dr.setBrowserName("chrome");
			  dr.setPlatform(Platform.LINUX);
			 
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), dr);
		
			
		}

		return driver;
	}

}
