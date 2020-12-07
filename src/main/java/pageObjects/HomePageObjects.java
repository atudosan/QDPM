package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import testBase.DriverFactory;
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
	
	By sidebarMenu_Users_By = By.xpath("//ul[@class='page-sidebar-menu']//i/following-sibling::span[text()='Users']");
	
	By subMenu_Users_AddUser_By = By.xpath("//ul[@class='page-sidebar-menu']//i/following-sibling::span[text()='Users']/ancestor"
			+ "::a/following-sibling::ul//li/a[@class='cursor-pointer']/span");
	
	By subMenu_Users_ViewAll_By = By.xpath("//ul[@class='page-sidebar-menu']//li/a/i/following-sibling::span[text()='Users']/"
			+ "ancestor::a/following-sibling::ul//li/a/span[text()='View All']");
	
	
	
	
	public void clickOnMenu(String menu) {
		String menuXpath_str = "//ul[@class='page-sidebar-menu']//i/following-sibling::span[text()='"+menu+"']";
		WebElement menuXpath = DriverFactory.getInstance().getDriver().findElement(By.xpath(menuXpath_str));
		click(menuXpath, "Menu");
	}
	
	public void clickOnSubMenu(String menu, String subMenu) {
		String menuXpath_str = "//ul[@class='page-sidebar-menu']//i/following-sibling::span[text()='"+menu+"']";
		WebElement menuXpath = DriverFactory.getInstance().getDriver().findElement(By.xpath(menuXpath_str));
		click(menuXpath, "Menu");
		
		String subMenuXpath_str = "//ul[@class='page-sidebar-menu']//li/a/i/following-sibling::span[text()='"+menu+"']/"
				+ "ancestor::a/following-sibling::ul//li/a/span[text()='"+subMenu+"']";
		WebElement subMenuXpath = DriverFactory.getInstance().getDriver().findElement(By.xpath(subMenuXpath_str));
		click(subMenuXpath, "SubMenu");
	}
	
	public void checkIfHomePageisOpened(String expectedFullname) throws Throwable {
		WebElement userFullName = DriverFactory.getInstance().getDriver().findElement(userFullName_By);
		String actualUserFullName = getTextFromWebElement(userFullName, "User Full Name");
		assertEqualsString(actualUserFullName, expectedFullname, "Full Name Account");
		//assertIfActualStringContainsExpectedString(actualUserFullName, expectedFullname, "Full Name Account");		
	}
	

}
