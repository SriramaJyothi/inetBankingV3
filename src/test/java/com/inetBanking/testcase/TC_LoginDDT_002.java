package com.inetBanking.testcase;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetBanking.pageObject.LoginPage;
import com.inetBanking.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass {
	// test method
	// data provider method

	@Test(dataProvider="LoginData")
	public void loginDDT(String user,String pwd) {
		
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(user);
		logger.info("user name provided");
		lp.setPassword(pwd);
		logger.info("password provided");
		lp.clickSubmit();
		
		if(isAlertPresent()==true)
		{
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			Assert.assertTrue(true);
			logger.warn("log in fail");
		}
		else
		{
			Assert.assertTrue(false);
			logger.info("login pass");
			lp.clickLogout();
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
		}
	}
		//user define method to check alerts are present or not
	
	public boolean isAlertPresent()
	{
		try {
			driver.switchTo().alert();
			return true;
		}
		catch(NoAlertPresentException e) {
			return false;
		}

	}
	

	@DataProvider(name="LoginData")
	
	String [][] getData() throws IOException
	{
		String path=System.getProperty("user.dir")+"/src/test/java/com/inetBanking/testdata/LoginData.xlsx";
		int rownum=XLUtils.getRowCount(path,"Sheet1");
		int colcount=XLUtils.getCellCount(path,"Sheet1",1);
		
		String logindata[][]=new String[rownum][colcount];
		
		for(int i=1;i<rownum;i++) {
			
			for(int j=0;j<colcount;j++) {
				
				logindata[i][j]=XLUtils.getCellData(path,"Sheet1",j, i);//1 0
			}
		}
		
		return logindata;
	}
	}


