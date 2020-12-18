package tests;

import java.util.HashMap;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import reusableComponents.ExcelOperation;
import testBase.TestBase;

public class NegativeLoginTest extends TestBase {

	ExcelOperation excel1 = new ExcelOperation("NegativeLoginTest");

	@Test(dataProvider = "NegativeLoginTestData")
	public void negativeLoginTest(Object obj1) {

		// we convert our array Object into a HashMap Object
		
		HashMap<String, String> loginTestData = (HashMap<String, String>) obj1;

		loginPage.loginIntoAccount(loginTestData.get("Username"), loginTestData.get("Password"));

	}

	@DataProvider(name = "NegativeLoginTestData")
	private Object[][] testDataSupplier() throws Exception {
		Object[][] obj = new Object[excel1.rowCount()][1];
		for (int i = 1; i <= excel1.rowCount(); i++) {
			HashMap<String, String> loginTestData = excel1.getTestDataIntoMap(i);
			obj[i - 1][0] = loginTestData;
		}
		return obj; 	
	}

}
