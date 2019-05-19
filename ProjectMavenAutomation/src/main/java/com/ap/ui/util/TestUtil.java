package com.ap.ui.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.ap.ui.base.TestBase;

public class TestUtil extends TestBase {

	public static long Page_load = 10 ; 
	public static long Implicit_Wait = 10 ; 
	

	public static String XL_SHEET_PATH ="path of xl sheet";
	
	static Workbook book; 
	static Sheet sheet; 
	static JavascriptExecutor js;
	
	public static Object[][] getTestData(String sheetName) { //two arrays for user name and password
		FileInputStream file = null; //if the cell is empty no values or data, don't perform the action
		try { //using try catch to handle and error handling this way instead of IOException(another way) 
			file = new FileInputStream(XL_SHEET_PATH );
		
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		}catch (InvalidFormatException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName); //method to go thru the information i'm going to have in my excel sheet(user-name and pass)
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()]; //create an array to hold info there
		
		for (int i = 0; i< sheet.getLastRowNum(); i++) { //pull info from all the rows and columns, won't run if nothing there(null)
			for (int j = 0; j <sheet.getRow(0).getLastCellNum(); j++) {
				data[i][j]=sheet.getRow(i+1).getCell(j).toString(); 
			}
		}
				return data; 
	}
	public static void takeScreenshotAt() throws IOException {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);//method to take a screenshot
		String currentDirect = System.getProperty("user.dir"); //getting the properties from directory, and creating a folder called screen shot
		FileUtils.copyFile(srcFile, new File(currentDirect + "/screenshot/" + //pasting the file after taking screenshot in this folder
				System.currentTimeMillis() + ".png")); //will save the screenshot by time-stamp and save it as a .png file format
	}
	
	//these codes are to capture any activity(console log thats happening) when running our script. 
	public static void runTimeInfo(String messageType, String message) throws InterruptedException {
		js = (JavascriptExecutor) driver;
		
		//"can you go and find the Jquery on the website so i can translate it to whatever i'm getting from console log"
		//its going to the ajax website to get the dependencies it needs to translate 
		js.executeScript("if (!window.JQuery){"
			+ "var jquery = document.createElement('scrupt); jquery.type = 'text/javascript';"
			+ "jquery.src = 'https://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js';"
			+ "documents.getElementsByTagName('head')[0].appendChild(jquery);" + "}");
		Thread.sleep(6000);
		
		//we are telling it to go retrieve what we're getting in that payload. we retrieve the header , go back to the link. that link will translate what the header means
		js.executeScript("$.getScript('https://the-internet.herokuapp.com/js/vendor/jquery.growl.js')");
		
				js.executeScript("$('head').append('<link rel=\"stylesheet\" "
						+ "href=\"https://the-internet.herokuapp.com/css/jquery.growl.css\" " + "type=\"text/css\" />');");
				Thread.sleep(6000);
		
				//creating logic to separate out the different types of errors/warnings 
	
			js.executeScript("$ growl({ title: 'GET' , message: '/'});");
		
		if(messageType.equals("error")) {
			js.executeScript("$growl.error({ title: 'ERROR' message: '"+message+"'});");
		}else if(messageType.equals("info")){
				js.executeScript("$growl.error({ title: 'Notice', message: 'notice message will appear here' });");
		}else if(messageType.equals("warning")){
			js.executeScript("$growl.warning({ title: 'Warning!!', message: 'warning message will appear here' });");
		}else 
			System.out.println("Show NO Error Message");
		Thread.sleep(6000);
	}
}



















