package testBase;

import pageObjects.AddUserPageObjects;
import pageObjects.HomePageObjects;
import pageObjects.LoginPageObjects;
import pageObjects.TasksPageObjects;
import reusableComponents.DB_Operations;

public class ObjectsRepository {
	
	public static LoginPageObjects loginPage = new LoginPageObjects();
	public static HomePageObjects homePage = new HomePageObjects();
	public static TasksPageObjects taskPage = new TasksPageObjects();
	public static DB_Operations dbOps = new DB_Operations();
	public static AddUserPageObjects addUser = new AddUserPageObjects();

}
