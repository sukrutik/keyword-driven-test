package com.operations;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.util.Properties;
//import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import com.google.common.io.Files;

public class UIOperation {

	WebDriver driver;
	public UIOperation(WebDriver driver){
		this.driver = driver;
	}
	public void perform(Properties p,String operation,String objectName,String objectType,String value) throws Exception{
		System.out.println("");
		switch (operation.toUpperCase()) {
		case "CLICK":
			//Perform click
			driver.findElement(this.getObject(p,objectName,objectType)).click();
			break;
		case "SETTEXT":
			//Set text on control
			driver.findElement(this.getObject(p,objectName,objectType)).sendKeys(value);
			break;			
		case "GOTOURL":
			//Get url of application
			driver.get(p.getProperty(value));
			break;
		case "GETTEXT":
			//Get text of an element
			driver.findElement(this.getObject(p,objectName,objectType)).getText();
			break;
		case "WAITFORJSLOAD":
			Thread.sleep(3000);
			break;
		case "ClOSEAPP" :
			driver.close();
			break;
		case "TITLE" :
			assertTrue(driver.getTitle().contains(p.getProperty(value)));
              break;
		case "DROPDOWN" :
			
           Select drpwn= new Select(driver.findElement(this.getObject(p, objectName, objectType))) ;
              drpwn.selectByVisibleText(value);
              break;
		case "WINDOMAX"	:
			driver.manage().window().maximize();
			break;
		case "CAPTUREDSCREENSHOT" :
		
			File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			//for giving random names to screenshot
			String ext = "png";
			String name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);
			//Files.copy(screenshotFile, new File ("C:\\Users\\User\\eclipse-workspace\\kdfproject\\screenshots\\screenshot.png"));
			Files.copy(screenshotFile, new File ("C:\\Users\\User\\eclipse-workspace\\kdfproject\\screenshots\\"+name));
			
			
		break;
		
		default:
			break;
		}
	}
	
	/**
	 * Find element BY using object type and value
	 * @param p
	 * @param objectName
	 * @param objectType
	 * @return
	 * @throws Exception
	 */
	private By getObject(Properties p,String objectName,String objectType) throws Exception{
		//Find by xpath
		if(objectType.equalsIgnoreCase("XPATH")){
			
			return By.xpath(p.getProperty(objectName));
		}
		//find by class
		else if(objectType.equalsIgnoreCase("CLASSNAME")){
			
			return By.className(p.getProperty(objectName));
			
		}
		//find by name
		else if(objectType.equalsIgnoreCase("NAME")){
			
			return By.name(p.getProperty(objectName));
			
		}
		//Find by css
		else if(objectType.equalsIgnoreCase("CSS")){
			
			return By.cssSelector(p.getProperty(objectName));
			
		}
		//find by link
		else if(objectType.equalsIgnoreCase("LINK")){
			
			return By.linkText(p.getProperty(objectName));
			
		}
		//find by partial link
		else if(objectType.equalsIgnoreCase("PARTIALLINK")){
			
			return By.partialLinkText(p.getProperty(objectName));
			
		}
		//find by id
				else if(objectType.equalsIgnoreCase("ID")){
					
					return By.id(p.getProperty(objectName));
				}
		else
		{
			throw new Exception("Wrong object type");
		}
	}
}
