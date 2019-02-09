package com.freecrm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.freecrm.qa.base.TestBase;

//@Author Rehan
public class CreateNewContact extends TestBase{


	@FindBy(xpath="//a[contains(text(),'New Contact')]")
	WebElement newContact;
	
	@FindBy(xpath="//*[@id='first_name']")
	WebElement firstName;
	
	@FindBy(xpath="//*[@id='surname']")
	WebElement lastName;
	
	@FindBy(xpath="//input[@value='Save']")
	WebElement save;
	
	public CreateNewContact(){
		PageFactory.initElements(driver, this);
	}
	
	public String validateContactPage(){
		String title="Contact Information";
		return title;
	}
	
	public void createNewContact(){
		driver.switchTo().frame("mainpanel");
		Actions action=new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//a[contains(text(),'Contacts')]"))).build().perform();
		newContact.click();
		firstName.sendKeys("Selenium");
		lastName.sendKeys("Jenkins");
		save.click();
		
	}

}
