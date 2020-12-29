package pageObjects;

import java.lang.module.FindException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;

import testBase.DriverFactory;
import testBase.ExtentFactory;
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
	By lbs_allAssignments = By.xpath("//label[contains(@for,'assigned_to_')]");
	By ckb_assignAdmin = By.id("tasks_assigned_to_1");
	By ckb_assignClient = By.id("tasks_assigned_to_3");
	By ckb_assignDeveloper = By.id("tasks_assigned_to_2");
	By ckb_assignManager = By.id("tasks_assigned_to_4");
	By link_time = By.xpath("//a[text()='Time']");
	By txt_estimatedTime = By.id("tasks_estimated_time");
	By txt_startDate = By.id("tasks_start_date");
	By txt_dueDate = By.id("tasks_due_date");
	By dd_taskProgress = By.id("tasks_progress");
	
	
	

	public void createTask(HashMap<String, String> testData) throws Throwable {

		selectDropDownByVisibleText(dd_selectProjectForNewTaskCreation,	"New Task Project", 
				testData.get("ProjectToCreateTaskUnder"));
		selectDropDownByVisibleText(dd_taskType,  "New Task Type", testData.get("TaskType"));
		sendText(txt_taskName, "New Task Name", testData.get("TaskName"));
		selectDropDownByVisibleText(dd_taskStatus, "New Task Status", 
				testData.get("TaskStatus"));
		selectDropDownByVisibleText(dd_taskPriority, "New Task Priority", 
				testData.get("TaskPriority"));
		selectDropDownByVisibleText(dd_taskLabel, "New Task Label", testData.get("Label"));
		selectOneCheckboxForAssignment(testData.get("AssignTo"));
		clickOn(btn_save, "Save");

	}

	public void verifyTaskCreationOnUI(HashMap<String, String> testData) throws Throwable {
		moveCursorToWebElement(field_search, "Search Task Option");
		sendText(txt_search, "Search Task", testData.get("TaskName"));
		clickOn(btn_search, "Search Button");

		// table verification
		assertEqualsString(testData.get("TaskName"), getTaskTableCellValueByColumnName("Name"),
				"TaskNameFromTable_UI");
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
	
	public void selectOneCheckboxForAssignment(String value){
		List<WebElement> allAssignmentsLabels = findAll(lbs_allAssignments);
		for (WebElement lbsAssignment : allAssignmentsLabels) {
			if(lbsAssignment.getText().contains(value)) {
				lbsAssignment.click();
				ExtentFactory.getInstance().getExtent().log(Status.INFO, "Selected 'assign to ["+
				value+"]' checkbox");
			}
			else {
				ExtentFactory.getInstance().getExtent().log(Status.WARNING, "No such Value in "
						+ "checkbox List");
			}
		}
	}
		
}