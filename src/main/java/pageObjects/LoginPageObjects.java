package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObjects {
	
	@FindBy(name="login[email]")
	WebElement txt_email;
	
	@FindBy(name="login[password]")
	WebElement txt_password;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement btn_login;

	//initiate all the page objects for passed driver instance
	public LoginPageObjects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//log to account
	public void login(String email, String password) {
		txt_email.sendKeys(email);
		txt_password.sendKeys(password);
		btn_login.click();
		
	}
	
	
	

}
