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

	public void createTask(HashMap<String, String> testData) throws Throwable {

		selectDropDownByVisibleText(
				DriverFactory.getInstance().getDriver().findElement(dd_selectProjectForNewTaskCreation),
				"NewTaskProjectDropDown", testData.get("ProjectToCreateTaskUnder"));
		selectDropDownByVisibleText(DriverFactory.getInstance().getDriver().findElement(dd_taskType),
				"NewTaskTypeDropDown", testData.get("TaskType"));
		sendText(DriverFactory.getInstance().getDriver().findElement(txt_taskName), "NewTaskNameTextField",
				testData.get("TaskName"));
		selectDropDownByVisibleText(DriverFactory.getInstance().getDriver().findElement(dd_taskStatus),
				"NewTaskStatusDropDown", testData.get("TaskStatus"));
		selectDropDownByVisibleText(DriverFactory.getInstance().getDriver().findElement(dd_taskPriority),
				"NewTaskPriorityDropDown", testData.get("TaskPriority"));
		selectDropDownByVisibleText(DriverFactory.getInstance().getDriver().findElement(dd_taskLabel),
				"NewTaskLabelDropDown", testData.get("Label"));
		click(DriverFactory.getInstance().getDriver().findElement(btn_save), "SaveButton");

	}

	public void verifyTaskCreationOnUI(HashMap<String, String> testData) throws Throwable {
		moveCursorToWebElement(DriverFactory.getInstance().getDriver().findElement(field_search), "SearchTaskOption");
		sendText(DriverFactory.getInstance().getDriver().findElement(txt_search), "SerachTaskTextBox",
				testData.get("TaskName"));
		click(DriverFactory.getInstance().getDriver().findElement(btn_search), "SearchButton");

		// table verification
		assertEqualsString(testData.get("TaskName"), getTaskTableCellValueByColumnName("Name"), "TaskNameFromTable_UI");
	}

	private String getTaskTableCellValueByColumnName(String columnName) {
		// this xpath will point to the specific record from by passing the name of the
		// respective column
		String valueXpath = "//table[starts-with(@id, 'itmes_listing')]/tbody/tr/td[count(//table[starts-with(@id, 'itmes_listing')]/thead/tr/th/div[text()='"
				+ columnName + "']/parent::th/preceding-sibling::th)+1]";
		String value = DriverFactory.getInstance().getDriver().findElement(By.xpath(valueXpath)).getText();
		return value;
	}

	// verify if task which was created in DB has the same records as we passed from
	// Excel
	public void verifyTaskCreationInDB(HashMap<String, String> testData) throws Throwable {
		// verifing if name of task is the same
		HashMap<String, String> dbData = extractDataFromDB("tasks", "name", testData.get("TaskName"));
		String actualEntryName = dbData.get("name");
		assertEqualsString(testData.get("TaskName"), actualEntryName, "'Actual Entry Name From DB'");
		// verifing if status of task is the same
		String dbTaskStatus = getOtherDetailsFromDB("tasks", "name", testData.get("TaskName"), "tasks_status_id", 
				"tasks_status", "id", "name" );
		assertEqualsString(testData.get("TaskStatus"), dbTaskStatus, "'Actual Task Status From DB'");
		// verifing if status of task is the same
		String dbTaskPriority = getOtherDetailsFromDB("tasks", "name", testData.get("TaskName"), "tasks_priority_id", 
				"tasks_priority", "id", "name" );
		assertEqualsString(testData.get("TaskPriority"), dbTaskPriority, "'Actual Task Priority From DB'");
	}

}