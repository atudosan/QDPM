package pageObjects;

import java.util.HashMap;

import org.openqa.selenium.By;

import testBase.DriverFactory;
import testBase.TestBase;

public class TasksPageObjects extends TestBase {

	By btn_addTask = By.xpath("//button[text()='Add Task']");
	By field_search = By.id("search_menu");
	By txt_search = By.xpath("//*[@id='search_menu']//input[@name='search[keywords]']");
	By btn_search = By.xpath("//*[@id='search_menu']//input[@type='submit']");
	By dd_selectProjectForNewTaskCreation = By.id("form_projects_id");
	By dd_taskType = By.id("tasks_tasks_type_id");
	By txt_taskName = By.id("tasks_name");
	By dd_taskStatus = By.id("tasks_tasks_status_id");
	By dd_taskPriority = By.id("tasks_tasks_priority_id");
	By dd_taskLabel = By.id("tasks_tasks_label_id");
	By dd_taskCreatedBy = By.id("tasks_created_by");
	By btn_save = By.xpath("//button[@type='submit' and text()='Save']");

	public void createTask() throws Throwable {

		selectDropDownByVisibleText(DriverFactory.getInstance().getDriver().findElement(dd_selectProjectForNewTaskCreation),
				"NewTaskProjectDropDown", "Project A");
		selectDropDownByVisibleText(DriverFactory.getInstance().getDriver().findElement(dd_taskType),
				"NewTaskTypeDropDown", "Changes (Hourly rate $15.00)");
		sendText(DriverFactory.getInstance().getDriver().findElement(txt_taskName), "NewTaskNameTextField",
				"NewDemoTask1");
		selectDropDownByVisibleText(DriverFactory.getInstance().getDriver().findElement(dd_taskStatus),
				"NewTaskStatusDropDown", "Open");
		selectDropDownByVisibleText(DriverFactory.getInstance().getDriver().findElement(dd_taskPriority),
				"NewTaskPriorityDropDown", "High");
		selectDropDownByVisibleText(DriverFactory.getInstance().getDriver().findElement(dd_taskLabel),
				"NewTaskLabelDropDown", "Task");
		click(DriverFactory.getInstance().getDriver().findElement(btn_save), "SaveButton");

	}

	public void verifyTaskCreationOnUI() throws Throwable {
		moveCursorToWebElement(DriverFactory.getInstance().getDriver().findElement(field_search), "SearchTaskOption");
		sendText(DriverFactory.getInstance().getDriver().findElement(txt_search), "SerachTaskTextBox", "NewDemoTask1");
		click(DriverFactory.getInstance().getDriver().findElement(btn_search), "SearchButton");

		// table verification
		assertEqualsString("NewDemoTask1", getTaskTableCellValueByColumnName("Name"), "TaskNameFromTable_UI");
	}

	private String getTaskTableCellValueByColumnName(String columnName) {

		String valueXpath = "//table[starts-with(@id, 'itmes_listing')]/tbody/tr/td[count(//table[starts-with(@id, 'itmes_listing')]/thead/tr/th/div[text()='"
				+ columnName + "']/parent::th/preceding-sibling::th)+1]";
		String value = DriverFactory.getInstance().getDriver().findElement(By.xpath(valueXpath)).getText();
		return value;
	}
	
	public void verifyTaskCreationInDB() throws Throwable {
		HashMap<String, String> dbData = extractDataFromDB("tasks", "NewDemoTask1");
		String actualEntryName = dbData.get("name");
		assertEqualsString("NewDemoTask1", actualEntryName, "'Actual Entry Name From DB'");
	}

}
