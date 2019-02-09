package com.freecrm.qa.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import com.freecrm.qa.base.TestBase;
import com.freecrm.qa.pages.LoginPage;

public class Helper extends TestBase{

	//@Author Rehan
	// To capture screenshots
			public static void takeScreenshotAtEndOfTest() throws IOException {
				File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				String currentDir = System.getProperty("user.dir");
				
				FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
				
				}
			
			// To Highlight the element
			public static void highLightElement(WebDriver driver,WebElement element){
				JavascriptExecutor js=(JavascriptExecutor)driver;
				js.executeScript("arguments[0].setAttribute('style','background: palegreen; border: 8px solid red:')", element);
				try{
					Thread.sleep(500);
				}
				catch (InterruptedException e) {
					System.out.println(e.getMessage());
				}
				js.executeScript("arguments[0].setAttribute('style','border: solid 2px white')", element);
			}
	
			// To Handle frame
			public void switchToFrame(int frame) {
				try {
					driver.switchTo().frame(frame);
					System.out.println("Navigated to frame with id " + frame);
				} catch (NoSuchFrameException e) {
					System.out.println("Unable to locate frame with id " + frame
							+ e.getStackTrace());
				} catch (Exception e) {
					System.out.println("Unable to navigate to frame with id " + frame
							+ e.getStackTrace());
				}
			}
			
			
			// Sending Email 
			// You can refer the user guide https://commons.apache.org/proper/commons-email/userguide.html
			public static void sendEmail() throws EmailException{
				Email email = new SimpleEmail();
				email.setHostName("smtp.gmail.com");
				email.setSmtpPort(465);
				email.setAuthenticator(new DefaultAuthenticator("saquibhelal78@gmail.com", "****"));
				email.setSSLOnConnect(true);
				email.setFrom("rehan@gmail.com");
				email.setSubject("Automation Test Report");
				email.setMsg("This is a test mail from Selenium :-)");
				email.addTo("rehanabrahim@gmail.com");
				email.send();
				System.out.println("Email Sent Successfully======>");
			}
			
			// To scroll to particular element 
			public static void scrollSpecificElement(WebDriver driver,WebElement element){
				((JavascriptExecutor) driver).executeScript(
		                "arguments[0].scrollIntoView();", element);
			}
			
			// Handle Alert in web base pop-up
			public static void handleWebBaseAlert(){
			    String alertMsg=driver.switchTo().alert().getText();// to capture alert text
			    System.out.println("Alert msg is:"+alertMsg);
				Alert alrt=driver.switchTo().alert();// In direct approach to handle alert
				alrt.accept();
				
				driver.switchTo().alert().accept();
				
				Assert.assertEquals(alertMsg, "Field cannot be empty");//verify alert msg
				
			}
			
			// Element display or not
			public static void displayElement(){
				boolean elementDisplayed=driver.findElement(By.xpath("")).isDisplayed();
				if(elementDisplayed){
					System.out.println("Element is displayed");
				}
				else
				{
					System.out.println("Element is not displayed");
				}
			}
			
			
			//Elemenent is enable or not
			public static void enableElement(){
				boolean enable=driver.findElement(By.xpath("")).isEnabled();
				if(enable){
					System.out.println("Element is enabled in page");
				}
				else
				{
					System.out.println("Element is not enabled in page");

				}
			}
			
			
			
			
			
			
}
