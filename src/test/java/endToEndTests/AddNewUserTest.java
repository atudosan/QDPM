package endToEndTests;

import java.util.HashMap;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import reusableComponents.ExcelOperation;
import testBase.TestBase;

public class AddNewUserTest extends TestBase {
	
	ExcelOperation excel2 = new ExcelOperation("AddUserData");
	
		
	@Test(dataProvider = "addUserData")
	public void addNewUserTest(Object obj) throws Throwable {

		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj;
		loginPage.loginIntoAccount(testData.get("LoginEmail"),
				testData.get("LoginPassword"));
		homePage.checkIfHomePageIsOpened(testData.get("AccountUserName"));
		homePage.clickOnAddUsersMenu();
		addUser.addNewUser(testData);
	}

	@DataProvider(name = "addUserData")
	public Object[][] testDataSupplier1() throws Exception {
		Object[][] obj = new Object[excel2.rowCount()][1];
		for (int i = 1; i <= excel2.rowCount(); i++) {
			HashMap<String, String> testData = excel2.getTestDataIntoMap(i);
			obj[i - 1][0] = testData;
		}
		return obj;
	}

}
