package reusableComponents;

import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import testBase.DriverFactory;
import testBase.ExtentFactory;
import testBase.ObjectsRepository;

public class EngineAction extends ObjectsRepository {

	protected void sendText(WebElement webElement, String element, String text) {
		try {
			webElement.sendKeys(text);
			ExtentFactory.getInstance().getExtent().log(Status.INFO,
					"The text '" + text + "' was sent " + "to the [" + element + "]");
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					"The text '" + text + "' was not " + "sent to the [" + element + "], due to Exception: " + e);

		}
	}

	protected void click(WebElement webElement, String element) {
		try {
			webElement.click();
			ExtentFactory.getInstance().getExtent().log(Status.INFO, "[" + element + "] was clicked successfully");
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					"[" + element + "] was not clicked, due to Exception: " + e);
		}
	}

	protected void moveCursorToWebElement(WebElement webElement, String element) {
		try {
			JavascriptExecutor executor = (JavascriptExecutor) DriverFactory.getInstance().getDriver();
			executor.executeScript("arguments[0].scrollIntoView(true);", webElement);
			Actions actions = new Actions(DriverFactory.getInstance().getDriver());
			actions.moveToElement(webElement).build().perform();
			ExtentFactory.getInstance().getExtent().log(Status.INFO,
					"Mouse is hover over [" + element + "] successfully");
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					"Mouse is not hover over [" + element + "], due to Exception: " + e);
		}
	}

	protected void clearText(WebElement webElement, String element) {
		try {
			webElement.clear();
			Thread.sleep(250);
			ExtentFactory.getInstance().getExtent().log(Status.INFO,
					"Data from [" + element + "] was cleared successfully");
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					"Data from [" + element + "] was not cleared, due to Exception: " + e);
		}
	}

	protected boolean isElementPresent(WebElement webElement, String element) {
		boolean presence = false;
		try {
			webElement.isDisplayed();
			ExtentFactory.getInstance().getExtent().log(Status.INFO, "The [" + element + "] is present");
			return presence;
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					"The [" + element + "] is not present, due to Exception: " + e);
			return presence;
		}
	}

	// Select dropdown value value by visibleText
	protected void selectDropDownByVisibleText(WebElement webElement, String element, String ddVisibleText)
			throws Throwable {
		try {
			Select s = new Select(webElement);
			waitForVisibility(webElement, 3);
			s.selectByVisibleText(ddVisibleText);
			ExtentFactory.getInstance().getExtent().log(Status.INFO,
					"Dropdown [" + element + "] was Selected by visible text: " + ddVisibleText);
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Dropdown [" + element
					+ "] was not selected by visible text: " + ddVisibleText + ", due to exception: " + e);
		}
	}

	// Select dropdown value value by value
	protected void selectDropDownByValue_custom(WebElement webElement, String element, String ddValue) throws Throwable {
		try {
			Select s = new Select(webElement);
			s.selectByValue(ddValue);
			ExtentFactory.getInstance().getExtent().log(Status.INFO,
					"Dropdown [" + element + "] was Selected by its value: " + ddValue);
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Dropdown [" + element
					+ "] was not selected by its value: " + ddValue + ", due to exception: " + e);
		}
	}

	// String Asserts
	protected static void assertEqualsString(String expectedValue, String actualValue, String locatorName)
			throws Throwable {
		try {
			if (actualValue.equals(expectedValue)) {
				ExtentFactory.getInstance().getExtent().log(Status.INFO,
						"Assertion 'StringsAreEqual' is successful regarding the field " + locatorName
								+ " Expected value was: " + expectedValue + " actual value is: " + actualValue);
			} else {
				ExtentFactory.getInstance().getExtent().log(Status.FAIL, "String Assertion FAILED on field "
						+ locatorName + " Expected value was: " + expectedValue + " actual value is: " + actualValue);
				Assert.assertTrue(false);
			}
		} catch (Exception e) {
			Assert.assertTrue(false, e.toString());
		}
	}

	protected void assertIfActualStringContainsExpectedString(String expectedValue, String actualValue, String locatorName)
			throws Throwable {
		try {
			if (actualValue.contains(expectedValue)) {
				ExtentFactory.getInstance().getExtent().log(Status.INFO, "String Assertion is successful on field "
						+ locatorName + " Expected value was: " + expectedValue + " actual value is: " + actualValue);
			} else {
				ExtentFactory.getInstance().getExtent().log(Status.FAIL, "String Assertion FAILED on field "
						+ locatorName + " Expected value was: " + expectedValue + " actual value is: " + actualValue);
				Assert.assertTrue(false);
			}
		} catch (Exception e) {
			Assert.assertTrue(false, e.toString());
		}
	}

	// Get text from webelement
	protected String getTextFromWebElement(WebElement webElement, String element) {
		String text = "";
		try {
			text = webElement.getText().strip();
			ExtentFactory.getInstance().getExtent().log(Status.INFO, element + "==> Text retried is: " + text);
			return text;
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					element + "==> Text not retried due to exception: " + e);

		}
		return text;
	}
	
	//this method will extract data from DB by passing name of the table and expected TaskName 
	// will return a hashMAp object, key will point to the name of the column and value will store
	// the actual record 
	protected HashMap<String, String> extractDataFromDB(String nameOfTable, String nameOfColumn, String recordByColumn
			) throws Throwable {
		HashMap<String, String> dbData = null;
		try {
			//build sql query by passing nameOfTable,  
			String sqlQuery = "SELECT * FROM `" + nameOfTable + "` WHERE "+nameOfColumn+" = '" + recordByColumn + "'";
			dbData = dbOps.getSqlResultInMap(sqlQuery);
			ExtentFactory.getInstance().getExtent().log(Status.INFO, "The data from DB was stored in HashMap");
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					"The data from DB was not stored in HashMap, due to" + e);
		}
		return dbData;
	}
	
	protected String getOtherDetailsFromDB(String nameOfMainTable, String 
			nameOfColumn, String recordNameByColumn, String SearchingColumnNameInMainTable,
			String nameOfSearchingTable, String PassingColumnName, String SearchingColumnName) throws Throwable {
		
		ExtentFactory.getInstance().getExtent().log(Status.INFO,  "Starting Validatation Test --> "
				+ "["+nameOfSearchingTable+"] value from DB") ;

		HashMap<String, String> dbData = null;
		String sqlFirstQuery = "SELECT * FROM `" + nameOfMainTable + "` WHERE "+nameOfColumn+" ="
				+ " '" + recordNameByColumn + "'";
		ExtentFactory.getInstance().getExtent().log(Status.INFO,  "1st SQL Query ["+sqlFirstQuery+"]");
		
		try {
			dbData = dbOps.getSqlResultInMap(sqlFirstQuery);
			ExtentFactory.getInstance().getExtent().log(Status.INFO, "Send main Query and stored recieved data "
					+ "into a HashMAp");
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					"The data from DB was not stored in HashMap, due to: " + e);
		}
		
		String valueOfSearchingColumnFromMainTable = dbData.get(SearchingColumnNameInMainTable);
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Stored the value "
				+ "["+valueOfSearchingColumnFromMainTable+"] into a String for next SQL Query");

		HashMap<String, String> dbSubData = null;
		String sqlSecondQuery = "SELECT * FROM `" + nameOfSearchingTable + "` WHERE "+
				PassingColumnName+" = '" + valueOfSearchingColumnFromMainTable + "'";
		ExtentFactory.getInstance().getExtent().log(Status.INFO,  "2nd SQL Query ["+sqlSecondQuery+"]");

		
		try {
			dbSubData = dbOps.getSqlResultInMap(sqlSecondQuery);
			ExtentFactory.getInstance().getExtent().log(Status.INFO, "Send second Query and stored the data in HashMAp");

		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					"The data from DB was not stored in HashMap, due to" + e);
		}
		
		String actualValueOfSearchingColumn = dbSubData.get(SearchingColumnName);
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Stored the value ["+actualValueOfSearchingColumn+"] for DB Validation");

		return actualValueOfSearchingColumn;
		
	}

	private static void waitForVisibility(WebElement element, Integer timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(DriverFactory.getInstance().getDriver(),
				Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

}
