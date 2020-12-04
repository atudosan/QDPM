package tests;

import org.testng.annotations.Test;

import pageObjects.LoginPageObjects;
import testBase.TestBase;

public class TestCase extends TestBase {
	LoginPageObjects login = new LoginPageObjects();
	
	@Test
	public void login1() {
		login.loginIntoAccount("michael@jackson.com", "Michael");
	}
	
	@Test
	public void login2() {
		login.loginIntoAccount("michael@jackson.com", "Michael");
	}

}
