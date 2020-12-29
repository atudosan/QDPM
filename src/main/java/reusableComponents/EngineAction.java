package reusableComponents;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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

	protected WebElement find(By locator) {
		WebElement element = DriverFactory.getInstance().getDriver().findElement(locator);
		return element;
	}

	protected List<WebElement> findAll(By locator) {
		return DriverFactory.getInstance().getDriver().findElements(locator);
	}

	protected void sendText(By locator, String elementLog, String text) {
		try {
			waitForElementToBeVisibile(locator, 10);
			find(locator).sendKeys(text);
			ExtentFactory.getInstance().getExtent().log(Status.INFO,
					"The text '" + text + "' was sent " + "to the [" + elementLog + "] text field");
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "The text '" + text + "' was not "
					+ "sent to the [" + elementLog + "] text field, due to Exception: " + e);
		}
	}

	protected void selectCheckbox(By locator, String elementLog) {
		if (!find(locator).isSelected()) {
			find(locator).click();
			ExtentFactory.getInstance().getExtent().log(Status.INFO, "select [" + elementLog + "] checkbox");
		} else {
			ExtentFactory.getInstance().getExtent().log(Status.INFO,
					"[" + elementLog + "] checkbox was already selected");
		}
	}

	protected void selectCheckboxByProvidedBooleanValue(By locator, String booleanValue, String elementLog) {
		waitForElementToBeVisibile(locator, 10);
		if (booleanValue.contains("true")) {
			if (find(locator).isSelected()) {
				ExtentFactory.getInstance().getExtent().log(Status.INFO,
						"[" + elementLog + "] " + "checkbox was selected because the passed value was 'true'");
			} else {
				find(locator).click();
				ExtentFactory.getInstance().getExtent().log(Status.INFO,
						"[" + elementLog + "] " + "checkbox was selected because the passed value was 'true'");
			}
		} else if (booleanValue.contains("false")) {
			if (find(locator).isSelected()) {
				find(locator).click();
				ExtentFactory.getInstance().getExtent().log(Status.INFO,
						"[" + elementLog + "] " + "checkbox was unselected because the passed value was 'false'");
			} else {

				ExtentFactory.getInstance().getExtent().log(Status.INFO,
						"[" + elementLog + "] " + "checkbox was unselected because the passed value was 'false'");
			}
		}
	}

	private void pressKey(By locator, Keys key, String elementLog) {
		find(locator).sendKeys(key);
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Pressed on " + elementLog + " key");

	}

	protected void uploadFile(By locator, String fileName) {
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Start Uploading file");
		clickOn(locator, "Choose File Button");
		String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\" + fileName;
		sendText(locator, "File Path", filePath);
		pressKey(locator, Keys.ENTER, "ENTER");
	}

	protected void clickOn(By locator, String elementLog) {
		try {
			waitForElementToBeVisibile(locator, 10);
			find(locator).submit();
			ExtentFactory.getInstance().getExtent().log(Status.INFO,
					"[" + elementLog + "] button was clicked successfully");
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					"[" + elementLog + "] button was not clicked, due to Exception: " + e);
		}
	}

	protected void click(By locator) {
		find(locator).click();
	}

	protected void moveCursorToWebElement(By locator, String elementLog) {
		try {
			JavascriptExecutor executor = (JavascriptExecutor) DriverFactory.getInstance().getDriver();
			executor.executeScript("arguments[0].scrollIntoView(true);", find(locator));
			Actions actions = new Actions(DriverFactory.getInstance().getDriver());
			actions.moveToElement(find(locator)).build().perform();
			ExtentFactory.getInstance().getExtent().log(Status.INFO,
					"Mouse is hover over [" + elementLog + "] successfully");
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					"Mouse is not hover over [" + elementLog + "], due to Exception: " + e);
		}
	}

	protected void clearText(By locator, String elementLog) {
		try {
			find(locator).clear();
			Thread.sleep(250);
			ExtentFactory.getInstance().getExtent().log(Status.INFO,
					"Data from [" + elementLog + "] was cleared successfully");
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					"Data from [" + elementLog + "] was not cleared, due to Exception: " + e);
		}
	}

	protected boolean isElementPresent(By locator, String elementLog) {
		boolean presence = false;
		try {
			find(locator).isDisplayed();
			ExtentFactory.getInstance().getExtent().log(Status.INFO, "The [" + elementLog + "] is present");
			return presence;
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					"The [" + elementLog + "] is not present, due to Exception: " + e);
			return presence;
		}
	}

	// Select dropdown value value by visibleText
	protected void selectDropDownByVisibleText(By locator, String elementLog, String ddVisibleText) throws Throwable {
		try {
			waitForElementToBeVisibile(locator, 3);
			WebElement elementLog_web = find(locator);
			Select s = new Select(elementLog_web);
			s.selectByVisibleText(ddVisibleText);
			ExtentFactory.getInstance().getExtent().log(Status.INFO,
					"[" + elementLog + "] dropdown was Selected using visible text: " + ddVisibleText);
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Dropdown [" + elementLog
					+ "] was not selected using visible text: " + ddVisibleText + ", due to exception: " + e);
		}
	}

	// Select dropdown value value by value
	protected void selectDropDownByValue(By locator, String elementLog, String ddValue) throws Throwable {
		try {
			Select s = new Select(find(locator));
			s.selectByValue(ddValue);
			ExtentFactory.getInstance().getExtent().log(Status.INFO,
					"Dropdown [" + elementLog + "] was Selected by its value: " + ddValue);
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Dropdown [" + elementLog
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

	protected void assertIfActualStringContainsExpectedString(String expectedValue, String actualValue,
			String locatorName) throws Throwable {
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
	protected String getTextFromWebElement(By locator, String elementLog) {
		String text = "";
		try {
			text = find(locator).getText().strip();
			ExtentFactory.getInstance().getExtent().log(Status.INFO,
					"Retrived Text for " + elementLog + " is [" + text + "]");
			return text;
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					elementLog + "==> Text not retrived due to exception: " + e);

		}
		return text;
	}

	// this method will extract data from DB by passing name of the table and
	// expected TaskName
	// will return a hashMAp object, key will point to the name of the column and
	// value will store
	// the actual record
	protected HashMap<String, String> extractDataFromDB(String nameOfTable, String nameOfColumn, String recordByColumn)
			throws Throwable {
		HashMap<String, String> dbData = null;
		try {
			// build sql query by passing nameOfTable,
			String sqlQuery = "SELECT * FROM `" + nameOfTable + "` WHERE " + nameOfColumn + " = '" + recordByColumn
					+ "'";
			dbData = dbOps.getSqlResultInMap(sqlQuery);
			ExtentFactory.getInstance().getExtent().log(Status.INFO, "The data from DB was stored in HashMap");
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					"The data from DB was not stored in HashMap, due to" + e);
		}
		return dbData;
	}

	protected String getOtherDetailsFromDB(String nameOfMainTable, String nameOfColumn, String recordNameByColumn,
			String SearchingColumnNameInMainTable, String nameOfSearchingTable, String PassingColumnName,
			String SearchingColumnName) throws Throwable {

		ExtentFactory.getInstance().getExtent().log(Status.INFO,
				"Starting Validatation Test --> " + "[" + nameOfSearchingTable + "] value from DB");

		HashMap<String, String> dbData = null;
		String sqlFirstQuery = "SELECT * FROM `" + nameOfMainTable + "` WHERE " + nameOfColumn + " =" + " '"
				+ recordNameByColumn + "'";
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "1st SQL Query [" + sqlFirstQuery + "]");

		try {
			dbData = dbOps.getSqlResultInMap(sqlFirstQuery);
			ExtentFactory.getInstance().getExtent().log(Status.INFO,
					"Send main Query and stored recieved data " + "into a HashMAp");
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					"The data from DB was not stored in HashMap, due to: " + e);
		}

		String valueOfSearchingColumnFromMainTable = dbData.get(SearchingColumnNameInMainTable);
		ExtentFactory.getInstance().getExtent().log(Status.INFO,
				"Stored the value " + "[" + valueOfSearchingColumnFromMainTable + "] into a String for next SQL Query");

		HashMap<String, String> dbSubData = null;
		String sqlSecondQuery = "SELECT * FROM `" + nameOfSearchingTable + "` WHERE " + PassingColumnName + " = '"
				+ valueOfSearchingColumnFromMainTable + "'";
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "2nd SQL Query [" + sqlSecondQuery + "]");

		try {
			dbSubData = dbOps.getSqlResultInMap(sqlSecondQuery);
			ExtentFactory.getInstance().getExtent().log(Status.INFO,
					"Send second Query and stored the data in HashMAp");

		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					"The data from DB was not stored in HashMap, due to" + e);
		}

		String actualValueOfSearchingColumn = dbSubData.get(SearchingColumnName);
		ExtentFactory.getInstance().getExtent().log(Status.INFO,
				"Stored the value [" + actualValueOfSearchingColumn + "] for DB Validation");

		return actualValueOfSearchingColumn;

	}

	private void waitForElementToBeVisibile(By locator, Integer timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(DriverFactory.getInstance().getDriver(),
				Duration.ofSeconds(timeOutInSeconds));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	private void waitForElementToBeClicable(By locator, Integer timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(DriverFactory.getInstance().getDriver(),
				Duration.ofSeconds(timeOutInSeconds));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	

}