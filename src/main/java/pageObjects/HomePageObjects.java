package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageObjects {
	
	WebDriver driver = null;
	
	@FindBy(xpath="//ul[@class='page-sidebar-menu']//i/following-sibling::span[text()='Dashboard']")
	WebElement sidebarMenu_Dashboard;
	
	@FindBy(xpath="//ul[@class='page-sidebar-menu']//i/following-sibling::span[text()='Projects']")
	WebElement sidebarMenu_Projects;
	
	@FindBy(xpath="//ul[@class='page-sidebar-menu']//li/a/i/following-sibling::span[text()='Projects']"
			+ "/ancestor::a/following-sibling::ul//li/a[@class='cursor-pointer']/span")
	WebElement subMenu_Projects_AddProject;
	
	@FindBy(xpath="//ul[@class='page-sidebar-menu']//li/a/i/following-sibling::span[text()='Projects']/"
			+ "ancestor::a/following-sibling::ul//li/a/span[text()='View All']")
	WebElement subMenu_Projects_ViewAll;
	
	@FindBy(xpath="//ul[@class='page-sidebar-menu']//i/following-sibling::span[text()='Tasks']")
	WebElement sidebarMenu_Tasks;
	
	@FindBy(xpath="//ul[@class='page-sidebar-menu']//i/following-sibling::span[text()='Tasks']/ancestor"
			+ "::a/following-sibling::ul//li/a[@class='cursor-pointer']/span")
	WebElement subMenu_Tasks_AddTask;
	
	@FindBy(xpath="//ul[@class='page-sidebar-menu']//li/a/i/following-sibling::span[text()='Tasks']/"
			+ "ancestor::a/following-sibling::ul//li/a/span[text()='View All']")
	WebElement subMenu_Tasks_ViewAll;
	
	@FindBy(xpath="//ul[@class='page-sidebar-menu']//i/following-sibling::span[text()='Users']")
	WebElement sidebarMenu_Users;
	
	@FindBy(xpath="//ul[@class='page-sidebar-menu']//i/following-sibling::span[text()='Users']/ancestor"
			+ "::a/following-sibling::ul//li/a[@class='cursor-pointer']/span")
	WebElement subMenu_Users_AddUser;
	
	@FindBy(xpath="//ul[@class='page-sidebar-menu']//li/a/i/following-sibling::span[text()='Users']/"
			+ "ancestor::a/following-sibling::ul//li/a/span[text()='View All']")
	WebElement subMenu_Users_ViewAll;
	
	public HomePageObjects(WebDriver driver) {
		PageFactory.initElements(driver, this);
		//whever we will create an object of this class it will pass the driver value to the class driver variable  
		this.driver = driver;
	}
	
	public void clickOnMenu(String menu) {
		String menuXpath = "//ul[@class='page-sidebar-menu']//i/following-sibling::span[text()='"+menu+"']";
		driver.findElement(By.xpath(menuXpath)).click();
	}
	
	public void clickOnSubMenu(String menu, String subMenu) {
		String menuXpath = "//ul[@class='page-sidebar-menu']//i/following-sibling::span[text()='"+menu+"']";
		driver.findElement(By.xpath(menuXpath)).click();
		String subMenuXpath = "//ul[@class='page-sidebar-menu']//li/a/i/following-sibling::span[text()='"+menu+"']/"
				+ "ancestor::a/following-sibling::ul//li/a/span[text()='"+subMenu+"']";
		driver.findElement(By.xpath(subMenuXpath)).click();
	}
	
	
	

}
