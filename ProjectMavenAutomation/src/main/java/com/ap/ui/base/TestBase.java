package com.ap.ui.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.ap.ui.util.TestUtil;
import com.ap.ui.util.WebEventListener;

public class TestBase { //setting up the property values for the class im going to work with

	public static WebDriver driver;
	public static Properties propt;
	public static EventFiringWebDriver en_driver;
	public static WebEventListener eventListener; //sends events that takes place
	
	public TestBase() {    //this is a constructor because there is no method to be carried out. 
		try {  //tryCatch is capturing any error that might occur + avoid errors using IO exception 
			propt = new Properties();
			FileInputStream ipa = new FileInputStream(System.getProperty("user.dir")+ "/ProjectMavenAutomation/src/main/java/com/ap/ui/config/config.properties"); //able to read the file. getting info from that fill into our script
			propt.load(ipa);  
			}catch (FileNotFoundException e) {  //tryCatch is capturing any error that might occur and avoid any error from any interaction of files
				e.printStackTrace(); 
			}catch (IOException e) {
				e.printStackTrace();
			}		
		}

	public static void initialization() { //created a method to initialize browser
		String browserName = propt.getProperty("browser"); 
		
		if(browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ "/src/main/resources/drivers/chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("FF")) {
			System.setProperty("webdriver.gecko.driver", "Provide file path for the driver");
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.ie.driver", "");
			driver = new InternetExplorerDriver();
		}

		//creating object for action that's occurring and sharing with driver
		en_driver = new EventFiringWebDriver(driver); //creating an object "Listener" to call the object multiple times. our run time is quicker
		//create object of WebEventListener to register it with eventFiringWebDriver
		eventListener = new WebEventListener();
		//event can be captured based on the method we create WebEventListener class
		en_driver.register(eventListener); //registers all the event that is happening , PASS / FAIL 
		//since we know driver object is for browser and en_driver is for event that's taking place.
		//we declaring with equal to each when they are exchanging the info
		
		driver = en_driver; 
		
		driver.manage().window().maximize(); //maximizing the window screen
		driver.manage().deleteAllCookies(); //deleting all cookies captured 
		driver.manage().timeouts().implicitlyWait(TestUtil.Implicit_Wait, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.Page_load, TimeUnit.SECONDS);
		
		driver.get(propt.getProperty("url"));
		
		
		
		
	}
}

