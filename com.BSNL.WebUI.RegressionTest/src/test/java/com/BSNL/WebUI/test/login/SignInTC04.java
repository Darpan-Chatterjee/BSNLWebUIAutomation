package com.BSNL.WebUI.test.login;

import java.io.IOException;
import java.util.Properties;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.BSNL.WebUI.Utility.Methods.GlobalVariable;
import com.BSNL.WebUI.Utility.Methods.UtilityMethods;
import com.BSNL.WebUI.page.login.LoginPage;


public class SignInTC04 {
	
	private String url,loginExcelPath,browserName,expectedTitle,expectedTitleLogin,userName,password;
	private String error1,error2,error3;
	private String configPath="..\\com.BSNL.WebUI.RegressionTest\\src\\test\\resources\\AutomationConfig.properties";
	UtilityMethods util = new UtilityMethods();
	
	public SignInTC04() throws Exception
	{
		Properties prop = util.LoadProperty(configPath);
		url=prop.getProperty("url");
		loginExcelPath=prop.getProperty("loginExcelPath");
		browserName=util.SearchExcel(loginExcelPath, "Sign In_TC04", "Browser Name");
		userName=util.SearchExcel(loginExcelPath, "Sign In_TC04", "User Name");
		password=util.SearchExcel(loginExcelPath, "Sign In_TC04", "Password");
		expectedTitle=util.SearchExcel(loginExcelPath, "Sign In_TC04", "Field 1");
		expectedTitleLogin=util.SearchExcel(loginExcelPath, "Sign In_TC04", "Field 2");
		error1=util.SearchExcel(loginExcelPath, "Sign In_TC04", "Field 3");
		error2=util.SearchExcel(loginExcelPath, "Sign In_TC04", "Field 4");
		error3=util.SearchExcel(loginExcelPath, "Sign In_TC04", "Field 5");
	}
	
	@BeforeMethod
	public void Setup()
	{
		GlobalVariable.driver = util.openURL(browserName,url);
	}
	
	@Test
	public void SignIn_TC04() throws Exception
	{
		GlobalVariable.test=GlobalVariable.extent.startTest("Test Case Id : SignIn TC04", "Description: To Validate error msgs if sign in is clicked without providing only username.");
		String actualTitle = GlobalVariable.driver.getTitle();
		util.waitInSecond(3);
		util.extentValidation("Navigate to https://portal2.bsnl.in/myportal/", expectedTitle, actualTitle, true, false, false);
		LoginPage login =new LoginPage();
		String status=login.signIn();
		util.extentValidation("Sign in option is present - Check", "Y", status, true, false, false);
		actualTitle = GlobalVariable.driver.getTitle();
		util.extentValidation("Sign in Page should appear - Check", expectedTitleLogin, actualTitle, true, false, false);
		String error[]=login.Login(userName,password);
		util.extentValidation("Error msg : You have some form errors. Please check below. - Check", error1, error[0], true, false, false);
		util.extentValidation("Error msg : Username is required. - Check", error2, error[1], true, false, false);
		util.extentValidation("Error msg : Enter Email id/ Mobileno/ Old Username - Check", error3, error[2], true, false, false);
	}
	
	@AfterMethod
	public void TearDown() throws IOException
	{
		GlobalVariable.wb.close();
		GlobalVariable.driver.close();
		GlobalVariable.extent.endTest(GlobalVariable.test);
	}	
}
