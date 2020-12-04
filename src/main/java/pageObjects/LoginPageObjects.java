package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import testBase.DriverFactory;
import testBase.TestBase;

public class LoginPageObjects extends TestBase {
	
	By EMAIL = By.name("login[email]");
	By PASSWORD = By.name("login[password]");
	By LOGIN_BTN = By.xpath("//button[@type='submit' and text()='Login ']");


	
	//log to account
	public void loginIntoAccount(String email, String password) {
		WebElement emailTextField = DriverFactory.getInstance().getDriver().findElement(EMAIL);
		sendText(emailTextField, "Email Login Field", email);
		WebElement passwordTextField = DriverFactory.getInstance().getDriver().findElement(PASSWORD);
		sendText(passwordTextField, "Password Login Field", password);
		WebElement loginButton = DriverFactory.getInstance().getDriver().findElement(PASSWORD);
		click(loginButton, "Login Button");
		
	}
	
	
	

}
