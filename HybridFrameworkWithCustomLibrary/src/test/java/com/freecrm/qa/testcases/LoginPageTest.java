package com.freecrm.qa.testcases;

import org.apache.commons.mail.EmailException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.freecrm.qa.base.TestBase;
import com.freecrm.qa.pages.LoginPage;
import com.freecrm.qa.util.Helper;

public class LoginPageTest extends TestBase {
	
	//@Author Rehan
	LoginPage loginPage;
	
	public LoginPageTest(){
		super();
	}
	
	/*@BeforeClass    /// Check TestBase class
	public void setUp(){
		initializeBrowser();
		loginPage=new LoginPage();
	}*/
	
	@Test(priority=1,description="Please check TesBase class for browser calling and quit operation")
	public void loginPageTitleTest() throws EmailException{
		String title=loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "Free Crm Application ");
		Helper.sendEmail();// Implementing Helper class 
	}
 
	@Test(priority=2)
	public void LogoTest(){
		boolean flag=loginPage.validateCRMImage();
		Assert.assertTrue(flag);
	}
	
	
	
	
	/*@AfterClass /////Check Test Base class
	public void tearDown(){
		System.out.println("======Browser is shutting down=====\n");
		driver.quit();
	}*/
	

}
