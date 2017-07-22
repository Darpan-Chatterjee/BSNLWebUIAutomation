package com.BSNL.WebUI.page.login;

import java.util.Properties;

import com.BSNL.WebUI.Utility.Methods.UtilityMethods;

public class LoginPage {
	
	private String signIn,userName,password,signInButton;
	private String error1,error2,error3,error4;
	private String dashBoardHDR;
	private String configPath="..\\com.BSNL.WebUI.RegressionTest\\src\\main\\resources\\PageMap\\login\\loginConfig.properties";
	UtilityMethods util = new UtilityMethods();
	
	public LoginPage() throws Exception
	{
		Properties prop = util.LoadProperty(configPath);
		signIn=prop.getProperty("signIn");	
		userName=prop.getProperty("userName");
		password=prop.getProperty("password");
		signInButton=prop.getProperty("signInButton");
		error1=prop.getProperty("error1");	
		error2=prop.getProperty("error2");
		error3=prop.getProperty("error3");
		error4=prop.getProperty("error4");
		dashBoardHDR=prop.getProperty("dashBoardHDR");
	}	
	
	public String signIn()
	{
		String status="N";
		try
		{
			if(util.getLocator(signIn).isDisplayed())
			{
				util.getLocator(signIn).click();
				status="Y";
			}
		}
		catch(Exception e)
		{
			return status;
		}
		return status;
	}
	
	public String LoginElementCheck()
	{
		String status="N";
		try
		{
			if(util.getLocator(userName).isDisplayed()&& util.getLocator(password).isDisplayed()&& util.getLocator(signInButton).isDisplayed())
			{
				util.getLocator(signInButton).click();
				status="Y";
			}
		}
		catch(Exception e)
		{
			return status;
		}
		return status;
	}
	
	public String[] Login(String UserName,String Password)
	{
		String error[]= new String[4];
		try
		{
			if(UserName.equals("")&&Password.equals(""))
			{
				util.getLocator(signInButton).click();
				util.waitInSecond(2);
				error[0]=util.getLocator(error1).getText();
				error[1]=util.getLocator(error2).getText();
				error[2]=util.getLocator(error3).getText();
				error[3]=util.getLocator(error4).getText();	
			}
			else if((!UserName.equals(""))&&Password.equals(""))
			{
				util.getLocator(userName).sendKeys(UserName);
				util.waitInSecond(1);
				util.getLocator(password).sendKeys(Password);
				util.waitInSecond(1);
				util.getLocator(signInButton).click();
				util.waitInSecond(2);
				error[0]=util.getLocator(error1).getText();
				error[3]=util.getLocator(error4).getText();	
			}
			else if(UserName.equals("")&&(!Password.equals("")))
			{
				util.getLocator(userName).sendKeys(UserName);
				util.waitInSecond(1);
				util.getLocator(password).sendKeys(Password);
				util.waitInSecond(1);
				util.getLocator(signInButton).click();
				util.waitInSecond(2);
				error[0]=util.getLocator(error1).getText();
				error[1]=util.getLocator(error2).getText();
				error[2]=util.getLocator(error3).getText();
			}
			
			else if(!(UserName.equals(""))&&(!Password.equals("")))
			{
				util.getLocator(userName).sendKeys(UserName);
				util.waitInSecond(1);
				util.getLocator(password).sendKeys(Password);
				util.waitInSecond(1);
				util.getLocator(signInButton).click();
				util.waitInSecond(2);
				try
				{
					error[0]=util.getLocator(error1).getText();
				}
				catch(Exception ex)
				{
					error[0]=util.getLocator(dashBoardHDR).getText();
				}	
			}
		}
		catch(Exception e)
		{
			return error;
		}
		return error;
	}
	
	
}
