package com.ap.ui.ExtentReport.Listener;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReporterN implements IReporter{
	private ExtentReports extent;
	
	
public void generateReport(List<XmlSuite> xmlSuites, List<ISuite>suites, //method created for Array list, Xmlsuites holds all the information(classes or pages with method and test cases), so when it pass,fail,or skip it will save it in that output path directory
		String outputDirectory){
	
	extent = new ExtentReports(outputDirectory + File.separator //we created the object "extent" and invoking it now to store results in this object
			+ "Extent.html", true); //we are saying to save the file as Extent.html. Boolean disregard empty extent reports. 
	//two additional new line of code that I forgot Earlier
	for(ISuite suite : suites){ // ":" is a conditional operator. it will run 1 or more suites if applicable
		Map<String, ISuiteResult>result = suite.getResults(); //Map obtains a key value, unique value that cannot be duplicated
		//and then it will be Mapped to one location. Map is a interface in Java which allows u to create a map between value you get from results
		//and one unique value (class fail, skip) sharing it with extend report. 
	
	for(ISuiteResult r : result.values()){ //takes the results 
		ITestContext context =r.getTestContext();
		
		buildTestNo(context.getPassedTests(), LogStatus.PASS);
		buildTestNo(context.getFailedTests(), LogStatus.FAIL);
		buildTestNo(context.getSkippedTests(), LogStatus.SKIP);
			
	}
}
	
extent.flush(); //adding the result you have and attaching it to the html file that we have 
extent.close();

}

private void buildTestNo(IResultMap tests, LogStatus status){ //creating a private constructor and created a object called test then we are putting our extentTest in test object
	ExtentTest test;
	
	if(tests.size()>0){
		for (ITestResult result : tests.getAllResults()){
			test = extent.startTest(result.getMethod().getMethodName());
			
			test.setStartedTime(getTime(result.getStartMillis())); //tells us how fast or the time /duration it took us to run the test
			test.setEndedTime(getTime(result.getEndMillis()));
			
			for(String group : result.getMethod().getGroups()) //for loop to go thru all of our results. combines all the fails,skips together in groups
				test.assignCategory(group);
			
			if(result.getThrowable() !=null){ //if theres a error we log it, if not we take the status and we add prefix "ed" "passed"
				test.log(status, result.getThrowable());
			}else{
				test.log(status, "Test" + status.toString().toLowerCase() + "ed");
				
		}
			extent.endTest(test);
			
	}
}
}
private Date getTime(long millis){ //stamps the time and date when the report was run on local machine
	Calendar calender = Calendar.getInstance();
	calender.setTimeInMillis(millis);
	return calender.getTime();
	
	
}
}