package pageObjects;

import org.openqa.selenium.By;

import testBase.TestBase;

public class HomePageObjects extends TestBase {
	
	//WebDriver driver = null;
	By userFullName_By = By.xpath("//span[@class='username']");
	
	By sidebarMenu_Dashboard_By = By.xpath("//ul[@class='page-sidebar-menu']//i/following-sibling::span[text()='Dashboard']");
	
	By sidebarMenu_Projects_By = By.xpath("//ul[@class='page-sidebar-menu']//i/following-sibling::span[text()='Projects']");
	
	By subMenu_Projects_AddProject_By = By.xpath("//ul[@class='page-sidebar-menu']//li/a/i/following-sibling::span[text()='Projects']"
			+ "/ancestor::a/following-sibling::ul//li/a[@class='cursor-pointer']/span");

	By subMenu_Projects_ViewAll_By = By.xpath("//ul[@class='page-sidebar-menu']//li/a/i/following-sibling::span[text()='Projects']/"
			+ "ancestor::a/following-sibling::ul//li/a/span[text()='View All']");
	
	By sidebarMenu_Tasks_By = By.xpath("//ul[@class='page-sidebar-menu']//i/following-sibling::span[text()='Tasks']");
	
	By subMenu_Tasks_AddTask_By = By.xpath("//ul[@class='page-sidebar-menu']//i/following-sibling::span[text()='Tasks']/ancestor"
			+ "::a/following-sibling::ul//li/a[@class='cursor-pointer']/span");
	
	By subMenu_Tasks_ViewAll_By = By.xpath("//ul[@class='page-sidebar-menu']//li/a/i/following-sibling::span[text()='Tasks']/"
			+ "ancestor::a/following-sibling::ul//li/a/span[text()='View All']");
	
	By sidebarMenu_Users_By = By.xpath("//span[text()='Users']");
	
	By subMenu_Users_AddUser_By = By.xpath("//span[text()='Add User']");
			
	
	By subMenu_Users_ViewAll_By = By.xpath("//ul[@class='page-sidebar-menu']//li/a/i/following-sibling::span[text()='Users']/"
			+ "ancestor::a/following-sibling::ul//li/a/span[text()='View All']");
	
	public void clickOnAddUsersMenu() {
		click(sidebarMenu_Users_By);
		click(subMenu_Users_AddUser_By);
	}
	
	public void clickOnAddTaskMenu() {
		click(sidebarMenu_Tasks_By);
		click(subMenu_Tasks_AddTask_By);
	}
	
	
	public void clickOnMenu(String menu) {
		String menuXpath_str = "//ul[@class='page-sidebar-menu']//i/following-sibling::span[text()='"+menu+"']";
		By menuXpath_loc = By.xpath(menuXpath_str);
		clickOn(menuXpath_loc, "Menu");
	}  
	
	public void clickOnSubMenu(String menu, String subMenu) {
		String menuXpath_str = "//ul[@class='page-sidebar-menu']//i/following-sibling::span[text()='"+menu+"']";
		By menuXpath_loc = By.xpath(menuXpath_str);
		clickOn(menuXpath_loc, menu);
		
		String subMenuXpath_str = "//ul[@class='page-sidebar-menu']//li/a/i/following-sibling::span[text()='"+menu+"']/"
				+ "ancestor::a/following-sibling::ul//li/a/span[text()='"+subMenu+"']";
		By subMenuXpath_loc = By.xpath(subMenuXpath_str);
		clickOn(subMenuXpath_loc, subMenu);
	}
	
	public void checkIfHomePageIsOpened(String expectedFullname) throws Throwable {
		String actualUserFullName = getTextFromWebElement(userFullName_By, "Account Name");
		assertEqualsString(actualUserFullName, expectedFullname, "Account Name");
	}
	

}