package com.freecrm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.freecrm.qa.pages.LoginPage;
import com.freecrm.qa.util.WebEventListner;

import io.github.bonigarcia.wdm.WebDriverManager;

//@Author Rehan Abrahim
public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver firingDriver;
	public static WebEventListner eventListner;
	LoginPage loginPage;
	
	
	public TestBase() {
		
		prop=new Properties();
		FileInputStream fis;
		try {
			fis = new FileInputStream("E:\\Icam_Automation_Test_QA\\HybridFrameworkWithCustomLibrary\\src\\main\\java\\"
					+ "com\\freecrm\\qa\\config\\config.properties");
			
				prop.load(fis);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		catch(IOException e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}
	

	// Calling different browser according to the requirement
	public static void initializeBrowser(){
		String browserName=prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("Chrome")){
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("FF")){
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("IE")){
			WebDriverManager.iedriver().setup();
			driver=new InternetExplorerDriver();
		}
		else if(browserName.equalsIgnoreCase("Opera")){
			WebDriverManager.operadriver().setup();
			driver=new OperaDriver();
		}
		
		firingDriver= new EventFiringWebDriver(driver);
		eventListner= new WebEventListner();
		
		firingDriver.register(eventListner);
		driver=firingDriver;
		
		driver.manage().window().maximize();
	  //driver.manage().window().setPosition(new Point(0, -1000));
		driver.manage().deleteAllCookies();
		//driver.manage().timeouts().pageLoadTimeout();
		//driver.manage().timeouts().implicitlyWait();
		driver.get(prop.getProperty("url"));
	  //driver.manage().window().maximize();	
	}
	
	// Call ur browser and do the operation once done quit ur browser
	@BeforeClass
	public void setUp(){
		initializeBrowser();
		loginPage=new LoginPage();
	}
	
	@AfterClass
	public void tearDown(){
		System.out.println("======Browser is shutting down=====\n");
		driver.quit();
	}
	
	
}
