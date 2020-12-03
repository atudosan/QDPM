package testBase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import reusableComponents.ConfigPropExtractData;

public class TestBase {

	// create an instance of BrowserFactoryClass in order to pass it to the
	// setDriver() method from DriveFactoryClass.
	BrowserFactory factory = new BrowserFactory();

	@BeforeMethod
	public void LaunchApplication() throws Exception {
		
		String browser = ConfigPropExtractData.getPropValueByKey("browser");
		String url = ConfigPropExtractData.getPropValueByKey("url");
		
		// create driverInstance to pass it to setDriver() method
		WebDriver driverInstance = factory.createBrowserInstance(browser);
		// here we set the driver value into the ThreadLocal driver instance
		DriverFactory.getInstance().setDriver(driverInstance);
		// this method will get the value driver from ThreadLocal driver instance and
		// set it for our driver instance
		WebDriver driver = DriverFactory.getInstance().getDriver();

		driver.manage().window().maximize();
		;
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(url);

	}

	@AfterMethod
	public void tearDown() {
		DriverFactory.getInstance().closeBrowser();
	}

}
