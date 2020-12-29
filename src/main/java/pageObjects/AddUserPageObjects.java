package pageObjects;

import java.util.HashMap;

import org.openqa.selenium.By;

import testBase.TestBase;

public class AddUserPageObjects extends TestBase {

	By ckb_activeCheckbox = By.id("uniform-users_active");
	By dd_group = By.id("users_users_group_id");
	By txt_userName = By.id("users_name");
	By txt_userPassword = By.id("users_password");
	By txt_usersEmail = By.id("users_email");
	By txt_phone = By.id("extra_fields_9");
	By btn_choosePhoto = By.xpath("//input[@id='users_photo']");
	By dd_language = By.id("users_culture");
	By ckb_userNotify = By.xpath("//label[@for='users_notify']");
	By btn_save = By.id("submit_button");

	public void addNewUser(HashMap<String, String> testData) throws Throwable {
		selectCheckboxByProvidedBooleanValue(ckb_activeCheckbox, testData.get("ActiveUser"), "Active User?");
		selectDropDownByVisibleText(dd_group, "Which User Group", testData.get("UserGroup"));
		sendText(txt_userName, "Account Name", testData.get("AcountName"));
		sendText(txt_userPassword, "Password", testData.get("UserGroup"));
		sendText(txt_usersEmail, "Email", testData.get("Email"));
		//sendText(txt_phone, "Phone", testDataForAddUser.get("Phone"));
		selectDropDownByVisibleText(dd_language, "Language", testData.get("Language"));
		selectCheckboxByProvidedBooleanValue(ckb_userNotify, testData.get("UserNotify"), "Notify User?");
		clickOn(btn_save, "Submit");		 
	}

}
