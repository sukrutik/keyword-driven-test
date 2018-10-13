package com.testcases;

import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.excel.utility.ReadExcelFile;
import com.operations.ReadObject;
import com.operations.UIOperation;
 

public class ExecuteTest {

	@Test
	
	public void testLogin() throws Exception{
		System.setProperty("webdriver.chrome.driver","E:\\class data\\softwaress\\drivers\\chromedriver.exe" );
		WebDriver driver =new ChromeDriver();

		
		ReadExcelFile file = new ReadExcelFile();
		ReadObject object = new ReadObject();
		
		Properties allObjects = object.getObjectRepositoriy();
		UIOperation operation = new UIOperation(driver);
		
		//read keyword sheet
		Sheet test = file.readExcel(System.getProperty("user.dir")+"\\", "test1.xlsx", "Sheet1");
		
		//find no of rows in excel file
		int rowCount = test.getLastRowNum()-test.getFirstRowNum();
		
		//create a loop over all the rows of excel file to read it
		for(int i=1;i<rowCount+1;i++)
		{
			//loop over all the rows
			Row row = test.getRow(i);
			//check if the first cell contain a value, if yes, that means it is the new test case name
			if(row.getCell(0).toString().length()==0) {
				//print testcase detail on console
				System.out.println(row.getCell(1).toString()+"---"+row.getCell(2).toString()+"---"+row.getCell(3).toString()+"---"+row.getCell(4).toString());
				//call perform funtion to perform operation on UI
				operation.perform(allObjects,row.getCell(1).toString(),row.getCell(2).toString(),row.getCell(3).toString(),row.getCell(4).toString());
				
			}
			else {
				//print the new tc name when it started
				
				System.out.println("New Testcase->"+row.getCell(0).toString()+"started");
			}
		}
		
	}
	
	
	
	
	
	
	
}
