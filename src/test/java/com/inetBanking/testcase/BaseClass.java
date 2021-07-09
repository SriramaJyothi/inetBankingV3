package com.inetBanking.testcase;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetBanking.utilities.ReadConfig;


public class BaseClass { //it is common for all test cases
	  
	ReadConfig readconfig=new ReadConfig();
	
	public String baseURL=readconfig.getApplicationURL();                                     //"http://demo.guru99.com/V4/";
	public String username=readconfig.getUsername();                                                                   //"mngr335933";
	public String password=readconfig.getPassword();                                                                    //"qUhapEg";
	public static WebDriver driver;
	public static Logger logger;

	@Parameters("browser") 
	@BeforeClass
	public void setup(String br) {
		
		logger = Logger.getLogger("ebanking");
		PropertyConfigurator.configure("log4j.properties");
	
		
		if(br.equals("chrome"))
		{
		System.setProperty("webdriver.chrome.driver",readconfig.getChromepath());
		driver=new ChromeDriver();
	}else if(br.equals("firefox")) {
		System.setProperty("webdriver.gecko.driver",readconfig.getFirefoxpath());
		driver=new FirefoxDriver();
	}
	else if(br.equals("ie")){
		System.setProperty("webdriver.driver",readconfig.getIEpath());
		driver=new FirefoxDriver();
	}
		
	
	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	driver.get(baseURL);
	}
		
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	public void captureScreenshot(WebDriver driver,String tname) throws IOException {
		TakesScreenshot ts =(TakesScreenshot) driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File target=new File(System.getProperty("user.dir")+"/Screenshots/"+tname+".png");
		FileUtils.copyFile(source,target);
		System.out.println("Screenshot taken Successfully");
	}

	public String randomestring()
	{
		String genaratedstring1=RandomStringUtils.randomAlphabetic(8);
		return(genaratedstring1);
	}
	
	public String randomeNum()
	{
		String genaratedstring2=RandomStringUtils.randomNumeric(8);
		return(genaratedstring2);
	}
}
