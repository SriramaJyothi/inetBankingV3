package com.inetBanking.testcase;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetBanking.pageObject.AddNewCustomer;
import com.inetBanking.pageObject.LoginPage;

public class TC_AddCustomerTest_003 extends BaseClass{
	
	@Test
	public void addNewCustomer() throws InterruptedException, IOException {
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(username);
		logger.info("username provided");
		lp.setPassword(password);
		logger.info("password provided");
		lp.clickSubmit();
		Thread.sleep(3000);
		
		AddNewCustomer addcust=new AddNewCustomer(driver);
		addcust.clickAddNewCustomer();
		logger.info("providing customer details....");
		
		addcust.custName("jyothi");
		addcust.custgender("female");
		addcust.custdob("16","06","1993");
		Thread.sleep(3000);
		addcust.custaddress("indianjj");
		addcust.custcity("hyd");
		addcust.custstate("ts");
		addcust.custpinno("500090");
		addcust.custtelephoneno("854265225");
		
		String email=randomestring()+"@gmail.com";
		addcust.custemailid(email);
		
		addcust.custpassword("tshbjj");
		addcust.custsubmit();
		
		Thread.sleep(3000);
		
		boolean res=driver.getPageSource().contains("Customer Registered Successfully!!!");
		if(res==true) {
			Assert.assertTrue(true);
		}
		else {
			captureScreenshot(driver,"addNewCustomer");
			Assert.assertTrue(false);
		}
	}
	
}
