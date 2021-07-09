package com.inetBanking.testcase;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetBanking.pageObject.LoginPage;

public class TC_LoginTest_001 extends BaseClass
{ 

//test case contain only one test method
	@Test
public void loginTest() throws IOException  {
	
		driver.get(baseURL);
		logger.info("URL login");
		LoginPage lp=new LoginPage(driver);
		
		lp.setUserName(username);
		logger.info("Username enter");
		lp.setPassword(password);
		logger.info("password enter");
		lp.clickSubmit();
		
		System.out.println(driver.getTitle());
		
		if(driver.getTitle().equals("Guru99 Bank Manager HomePage")) {
			Assert.assertTrue(true);
			logger.info("test pass");
		}
		else {
			captureScreenshot(driver,"loginTest");
				Assert.assertTrue(false);
				logger.info("test fail");
			}
		}
	
}

	
	
	
	
	
	



