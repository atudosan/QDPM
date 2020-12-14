package tests;

import java.util.HashMap;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.HomePageObjects;
import pageObjects.LoginPageObjects;
import pageObjects.TasksPageObjects;
import reusableComponents.ExcelOperation;
import testBase.TestBase;

public class TestCase extends TestBase {
	

	
	ExcelOperation excel = new ExcelOperation("TaskCreationData");
	
	@Test(dataProvider = "taskCreationData")
	// we should pass object to our method because the data provider is returning us an object array
	public void TaskCreationTest(Object obj1) throws Throwable {	
		//we convert our Object array into a HashMap Object
		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		loginPage.loginIntoAccount(testData.get("UserName"), testData.get("Password"));
		homePage.checkIfHomePageIsOpened(testData.get("FullName"));
		homePage.clickOnSubMenu("Tasks", "Add Task");
		//we pass our hashmap object as a parameter to that object and we have parametrize method itself 
		taskPage.createTask(testData);
		taskPage.verifyTaskCreationOnUI(testData);
		taskPage.verifyTaskCreationInDB(testData);
		
		
		
	}
	
	@DataProvider (name="taskCreationData")
	public Object[][] testDataSupplier() throws Exception {
		Object[][] obj = new Object[excel.rowCount()][1];
		for (int i = 1; i <= excel.rowCount(); i++) {
			HashMap<String, String> testData = excel.getTestDataIntoMap(i);
			obj[i - 1][0] = testData;
		}
		return obj;
	}
	
	

}
