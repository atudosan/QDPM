package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TasksPageObjects {
	
	@FindBy(xpath="//button[text()='Add Task']")
	WebElement btn_addTask;
	
	@FindBy(id="search_menu")
	WebElement field_search;
	
	@FindBy(xpath="//*[@id='search_menu']//input[@name='search[keywords]']")
	WebElement txt_search;
	
	@FindBy(xpath="//*[@id='search_menu']//input[@type='submit']")
	WebElement btn_search;
	
	@FindBy(id="form_projects_id")
	WebElement dd_selectProjectForNewTaskCreation;
	
	@FindBy(id="tasks_tasks_type_id")
	WebElement dd_taskType;
	
	@FindBy(id="tasks_name")
	WebElement dd_taskName;
	
	@FindBy(id="tasks_tasks_status_id")
	WebElement dd_taskStatus;
	
	@FindBy(id="tasks_tasks_priority_id")
	WebElement dd_taskPriority;
	
	@FindBy(id="tasks_tasks_label_id")
	WebElement dd_taskLabel;
	
	@FindBy(id="tasks_created_by")
	WebElement dd_taskCreatedBy;
	
	@FindBy(xpath="//button[@type='submit' and text()='Save']")
	WebElement btn_save;
	
	
	public TasksPageObjects(WebDriver driver) {
		PageFactory.initElements(driver, this);		
	}
	

}
