package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UsersPageObjects {
	
	@FindBy(xpath="//button[text()='Add User']")
	WebElement btn_addUser;
	
	@FindBy(id="search_menu")
	WebElement field_search;
	
	@FindBy(xpath="//*[@id='search_menu']//input[@name='search[keywords]']")
	WebElement txt_search;
	
	@FindBy(xpath="//*[@id='search_menu']//input[@type='submit']")
	WebElement btn_search;
	
	@FindBy(id="users_users_group_id")
	WebElement dd_group;
	
	@FindBy(name="users[name]")
	WebElement txt_fullName;
	
	@FindBy(name="users[password]")
	WebElement txt_password;
	
	@FindBy(name="users[email]")
	WebElement txt_email;
	
	@FindBy(name="extra_fields[9]")
	WebElement txt_phone;
	
	@FindBy(id="users_photo")
	WebElement btn_userPhoto;
	
	@FindBy(id="submit_button")
	WebElement btn_save;
	
	@FindBy(id="users_notify")
	WebElement chk_notifyUser;
	
	public UsersPageObjects(WebDriver driver) {
		PageFactory.initElements(driver, this);		
	}
	
		
}
