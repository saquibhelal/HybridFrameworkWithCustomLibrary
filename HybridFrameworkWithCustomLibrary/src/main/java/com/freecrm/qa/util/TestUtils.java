package com.freecrm.qa.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import com.freecrm.qa.base.TestBase;

public class TestUtils extends TestBase  {

	public static String TestDataExcelSheet="E:\\Icam_Automation_Test_QA\\HybridFrameworkWithCustomLibrary\\src\\main\\java"
			+ "\\com\\freecrm\\qa\\testdata\\FreeCrmExcelData.xlsx";
	
	
	public static Workbook book;
	public static Sheet sheet;
	
	
	public static Object[][] getTestData(String sheetName){
		
		FileInputStream file=null;
		try{
			file= new FileInputStream(TestDataExcelSheet);
			
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		try{
			book=WorkbookFactory.create(file);
			
		} catch (InvalidFormatException e) {

		   e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		sheet=book.getSheet(sheetName);
		Object[][] data=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
		for(int i =0; i< sheet.getLastRowNum();i++){
			for(int k=0;k<sheet.getRow(0).getLastCellNum();k++){
				data[i][k]= sheet.getRow(i+1).getCell(k).toString();
				//System.out.println(data[i][k]);
				
			}
		}
		
		
		return data;
		
	}
	
}
