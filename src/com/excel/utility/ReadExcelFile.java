package com.excel.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ReadExcelFile {

		public Sheet readExcel(String filePath,String fileName, String sheetName) throws IOException{
			//create a object of file class to open xlsx file
			File file = new File(filePath+"\\"+fileName);
			
			//create an object of file input stream class to read excel file
			FileInputStream  inputStream = new FileInputStream(file);
			Workbook Testscriptbook = null;
			
			//find the file extension by spliting file name in substring and getting only extension name
			String fileExtensionName = fileName.substring(fileName.indexOf("."));
			
			//check cond in if the file is xlsx file
			if(fileExtensionName.equals(".xlsx")) {
				//if it is xlsx file then create a new object of xssf workbook class
				Testscriptbook = new XSSFWorkbook(inputStream);
			}
			
			//check cond in if the file is xls file
			else if(fileExtensionName.equals(".xls")) {
				//if it is xls file then create a new object of xssf workbook class
				Testscriptbook = new HSSFWorkbook(inputStream);
			}
			
			//read sheet inside the workbook by its name
			Sheet Testsheet = Testscriptbook.getSheet(sheetName)
;			return Testsheet;
}
}
