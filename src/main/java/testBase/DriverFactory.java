package testBase;

import org.openqa.selenium.WebDriver;

public class DriverFactory {

	// creating private instance
	private static DriverFactory instance = new DriverFactory();

	// private constructor
	private DriverFactory() {
	}

	// creating method which allow us to have global access to that instance
	public static DriverFactory getInstance() {
		return instance;
	}

	ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	
	//set current thread copy of this particular ThreadLocal variable with specific value(webdriver reference).
	// driverInstance will be taken using createBrowserInstance() from BrowsrFactory class and it will be passed to the // TreadLocal driver variable
	public void setDriver(WebDriver driverInstance) {
		driver.set(driverInstance); // this method will set the value for ThreadLocal driver instance
	}

	// this method will get the value for our driver from ThreadLocal driver instance
	public WebDriver getDriver() {
		return driver.get();
	}

	public void closeBrowser() {
		driver.get().close();
		driver.remove();
	}

}
