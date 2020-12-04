package reusableComponents;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import testBase.DriverFactory;
import testBase.ExtentFactory;

public class EngineAction {

	public void sendText(WebElement webElement, String element, String text) {
		try {
			webElement.sendKeys(text);
			ExtentFactory.getInstance().getExtent().log(Status.PASS,
					"The text '" + text + "' was sent " + "to the [" + element + "]");
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					"The text '" + text + "' was not " + "sent to the [" + element + "], due to Exception: " + e);

		}
	}

	public void click(WebElement webElement, String element) {
		try {
			webElement.click();
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "[" + element + "] was clicked successfully");
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					"[" + element + "] was not clicked, due to Exception: " + e);
		}
	}

	public void moveCursorTowebElement(WebElement webElement, String element) {
		try {
			JavascriptExecutor executor = (JavascriptExecutor) DriverFactory.getInstance().getDriver();
			executor.executeScript("arguments[0].scrollIntoView(true);", webElement);
			Actions actions = new Actions(DriverFactory.getInstance().getDriver());
			actions.moveToElement(webElement).build().perform();
			ExtentFactory.getInstance().getExtent().log(Status.PASS,
					"Mouse is hover over [" + element + "] successfully");
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					"Mouse is not hover over [" + element + "], due to Exception: " + e);
		}
	}

	public void clearText(WebElement webElement, String element) {
		try {
			webElement.clear();
			Thread.sleep(250);
			ExtentFactory.getInstance().getExtent().log(Status.PASS,
					"Data from [" + element + "] was cleared successfully");
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					"Data from [" + element + "] was not cleared, due to Exception: " + e);
		}
	}

	public boolean isElementPresent(WebElement webElement, String element) {
		boolean presence = false;
		try {
			webElement.isDisplayed();
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "The [" + element + "] is present");
			return presence;
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					"The [" + element + "] is not present, due to Exception: " + e);
			return presence;
		}
	}

	// Select dropdown value value by visibleText
	public void selectDropDownByVisibleText_custom(WebElement webElement, String element, String ddVisibleText)
			throws Throwable {
		try {
			Select s = new Select(webElement);
			s.selectByVisibleText(ddVisibleText);
			ExtentFactory.getInstance().getExtent().log(Status.PASS,
					"Dropdown [" + element + "] was Selected by visible text: " + ddVisibleText);
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Dropdown [" + element
					+ "] was not selected by visible text: " + ddVisibleText + ", due to exception: " + e);
		}
	}

	// Select dropdown value value by value
	public void selectDropDownByValue_custom(WebElement webElement, String element, String ddValue) throws Throwable {
		try {
			Select s = new Select(webElement);
			s.selectByValue(ddValue);
			ExtentFactory.getInstance().getExtent().log(Status.PASS,
					"Dropdown [" + element + "] was Selected by its value: " + ddValue);
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Dropdown [" + element
					+ "] was not selected by its value: " + ddValue + ", due to exception: " + e);
		}
	}

	// String Asserts
	public void assertEqualsString(String expectedValue, String actualValue, String locatorName)
			throws Throwable {
		try {
			if (actualValue.equals(expectedValue)) {
				ExtentFactory.getInstance().getExtent().log(Status.PASS, "String Assertion is successful on field "
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
	public String getText(WebElement webElement, String element) {
		String text = "";
		try {
			text = webElement.getText();
			ExtentFactory.getInstance().getExtent().log(Status.PASS, element + "==> Text retried is: " + text);
			return text;
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					element + "==> Text not retried due to exception: " + e);

		}
		return text;
	}

}
