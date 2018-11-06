package com.uoa.qa.winc.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;

import com.uoa.qa.winc.utilities.TestUtil;
import com.uoa.qa.winc.utilities.WebEventListener;



public class TestBase {
	
	public static Logger log = LogManager.getLogger(TestBase.class.getName());
	
	public static WebDriver driver=null;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebDriverEventListener e_listener;
	

	
public TestBase() throws IOException {
		
		String path=System.getProperty("user.dir"); 
		FileInputStream fis= new FileInputStream(path+"\\src\\main\\java\\com\\uoa\\qa\\winc\\properties\\data.properties");
		prop=new Properties();
		prop.load(fis);
		
	}
	
	
	public static void intialize() throws IOException {
		
		//String browser=prop.getProperty("browser");
		
		if(driver==null) {
			
			
				
				if (prop.getProperty("browser").trim().equalsIgnoreCase("chrome")) {

					//System.setProperty("webdriver.chrome.driver",
							//"C:\\Users\\kbas663\\Downloads\\eclips_selenium\\driver\\chromedriver.exe");
					
							String path=System.getProperty("user.dir"); 
							
							System.setProperty("webdriver.chrome.driver",
									path+"\\src\\main\\java\\com\\uoa\\qa\\winc\\resources\\chromedriver.exe");
							
					driver = new ChromeDriver();
					

				}

				else if (prop.getProperty("browser").equalsIgnoreCase("FF")) {

					//System.setProperty("webdriver.gecko.driver",
							//"C:\\Users\\kbas663\\Downloads\\eclips_selenium\\driver\\driver\\geckodriver.exe");
					
					String path=System.getProperty("user.dir"); 
					
					System.setProperty("webdriver.gecko.driver",
							path+"\\src\\main\\java\\com\\uoa\\qa\\winc\\resources\\geckodriver.exe");
					
					driver = new FirefoxDriver();

				}
				
				else if (prop.getProperty("browser").trim().equalsIgnoreCase("IE")){
					
					
					
					log.error("********IE not supported*********");
					
					
//					System.setProperty("webdriver.ie.driver",
//							"C:\\Users\\kbas663\\Downloads\\eclips_selenium\\driver\\IEDriverServer.exe");
//					
//					InternetExplorerOptions ieOptions = new InternetExplorerOptions();
//					ieOptions.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
//					
//					driver = new InternetExplorerDriver(ieOptions);
//			
					
					
				}
				
			}
			
		
		
		
		e_driver= new EventFiringWebDriver(driver);
		e_listener= new WebEventListener();
		e_driver.register(e_listener);
		
		driver=e_driver;
	
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
	driver.manage().timeouts().implicitlyWait(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
	
	driver.get(prop.getProperty("url").trim());
	
	
	
	
	
	}
		
		
	

}
