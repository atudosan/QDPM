package tests;

import java.util.HashMap;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import reusableComponents.ExcelOperation;
import testBase.TestBase;

public class NegativeLoginTests extends TestBase {

	ExcelOperation excel1 = new ExcelOperation("NegativeLoginTest");

	
	@Test(dataProvider = "negativeLoginTestData")
	public void negativeLoginTests(Object obj2) throws Throwable {

		// we convert our array Object into a HashMap Object
		
		@SuppressWarnings("unchecked")
		//HashMap<String, String> testData = (HashMap<String, String>) obj1;
		HashMap<String, String> loginTestData = (HashMap<String, String>) obj2;

		loginPage.loginIntoAccount(loginTestData.get("Username"), loginTestData.get("Password"));
		loginPage.validateErrorMsg(loginTestData.get("Expected message"), loginTestData.get("Type"), 
				loginTestData.get("info"));

	}

	@DataProvider(name = "negativeLoginTestData")
	private Object[][] testDataSupplier() throws Exception {
		Object[][] obj = new Object[excel1.rowCount()][1];
		for (int i = 1; i <= excel1.rowCount(); i++) {
			HashMap<String, String> loginTestData = excel1.getTestDataIntoMap(i);
			obj[i - 1][0] = loginTestData;
		}
		return obj; 	
	}

}
