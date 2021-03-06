package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import testBase.DriverFactory;
import testBase.ExtentFactory;
import testBase.TestBase;

public class LoginPageObjects extends TestBase {

	By EMAIL = By.name("login[email]");
	By PASSWORD = By.name("login[password]");
	By LOGIN_BTN = By.xpath("//button[@type='submit']");
	By FIELD_REQUIRED_MSG = By.xpath("//label[@class='error']");
	By NO_MATCH_CREDENTIALS_MSG = By.id("userAlertContainer");

	// log to account
	public HomePageObjects loginIntoAccount(String email, String password) {
		sendText(EMAIL, "Email Login", email);
		sendText(PASSWORD, "Password Login", password);
		clickOn(LOGIN_BTN, "Login");
		return new HomePageObjects();
	}


	public void validateErrorMsg(String expectedMsg, String errorMsgType, String testInfo ) {
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Starting Negative Login Test using "+testInfo);
		if(errorMsgType.contains("credentials")) {
		WebElement noCredetialsMatchMsg = DriverFactory.getInstance().getDriver().findElement(NO_MATCH_CREDENTIALS_MSG);
		String actualNoCredetialsMatchMsg = noCredetialsMatchMsg.getText();
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Comparing ["+expectedMsg+"] as a expected "
				+ "error message and ["+actualNoCredetialsMatchMsg+"] as an actual error message");
		Assert.assertEquals(actualNoCredetialsMatchMsg, expectedMsg);	
		} else if (errorMsgType.contains("fieldRequired")){
			WebElement fieldRequiredMsg = DriverFactory.getInstance().getDriver().findElement(FIELD_REQUIRED_MSG);
			String actualFieldRequiredMsg = fieldRequiredMsg.getText();
			ExtentFactory.getInstance().getExtent().log(Status.INFO, "Comparing ["+expectedMsg+"] as a expected "
					+ "error message and ["+actualFieldRequiredMsg+"] as an actual error message");
			Assert.assertEquals(actualFieldRequiredMsg, expectedMsg);			
		}
	}

}