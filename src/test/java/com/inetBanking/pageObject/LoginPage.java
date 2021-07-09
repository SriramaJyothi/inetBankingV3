package com.inetBanking.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
		WebDriver ldriver; //Driver object
		public LoginPage(WebDriver rdriver) {//constructor
			
			ldriver=rdriver;
			PageFactory.initElements(rdriver,this);
		}
		@FindBy(name="uid")
		@CacheLookup
		WebElement txtUserName;
		
		@FindBy(name="password")
		@CacheLookup
		WebElement txtPassword;
		
		@FindBy(name="btnLogin")
		@CacheLookup
		WebElement btnLogin;
		@FindBy(xpath="//body[1]/div[3]/div[1]/ul[1]/li[15]/a[1]")
		@CacheLookup
		WebElement lnkLogout;
		
		
		//Action methods
		public void setUserName(String uname) {
			txtUserName.sendKeys(uname);
		}
		public void setPassword(String pwd) {
			txtPassword.sendKeys(pwd);
		}
		public void clickSubmit() {
			btnLogin.click();
		}
		public void clickLogout() {
			
			lnkLogout.click();
			
		}
		
	}

