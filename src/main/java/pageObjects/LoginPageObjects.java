package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageObjects {
	
	@FindBy(name="login[email]")
	WebElement txt_email;
	
	@FindBy(name="login[password]")
	WebElement txt_password;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement btn_login;
	
	

}
