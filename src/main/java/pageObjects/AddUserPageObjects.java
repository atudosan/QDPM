package pageObjects;

import org.openqa.selenium.By;

import testBase.TestBase;

public class AddUserPageObjects extends TestBase {
	
	By ckb_activeCheckbox = By.id("uniform-users_active");
	By dd_group = By.id("users_users_group_id");
	By txt_userName = By.id("users_name");
	By txt_usersEmail = By.id("users_email");
	By txt_phone = By.id("extra_fields_9");
	By btn_choosePhoto = By.id("users_photo");
	By dd_language = By.id("users_culture");
	By ckb_userNotify = By.id("users_notify");
	By btn_save = By.id("submit_button");
	
	public void addNewUser() {
		
	}
	
	
	

}
