package tests;

import org.testng.annotations.Test;

import pageObjects.HomePageObjects;
import pageObjects.LoginPageObjects;
import pageObjects.TasksPageObjects;
import testBase.TestBase;

public class TestCase extends TestBase {
	
	
	
	@Test
	public void login1() throws Throwable {
		loginPage.loginIntoAccount("michael@jackson.com", "Michael");
		homePage.checkIfHomePageisOpened("Michael Jackson");
		homePage.clickOnSubMenu("Tasks", "Add Task");
		taskPage.createTask();
		taskPage.verifyTaskCreationOnUI();
		taskPage.verifyTaskCreationInDB();
		
		
	}
	
	

}
