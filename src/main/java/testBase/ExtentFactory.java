package testBase;

import com.aventstack.extentreports.ExtentTest;


public class ExtentFactory {
	
	// creating private instance
		private static ExtentFactory instance = new ExtentFactory();

		// private constructor
		private ExtentFactory() {
		}

		// creating method which allow us to have global access to that instance
		public static ExtentFactory getInstance() {
			return instance;
		}

		ThreadLocal<ExtentTest> extent = new ThreadLocal<ExtentTest>();
		
		//set current thread copy of this particular ThreadLocal variable with specific value(webdriver reference).
		// driverInstance will be taken using createBrowserInstance() from BrowsrFactory class and it will be passed to the // TreadLocal driver variable
		public void setExtent(ExtentTest extentInstance) {
			extent.set(extentInstance); // this method will set the value for ThreadLocal driver instance
		}

		// this method will get the value for our driver from ThreadLocal driver instance
		public ExtentTest getExtent() {
			return extent.get();
		}
		
		public void removeExtentObject() {
			extent.remove();
		}

		
}
