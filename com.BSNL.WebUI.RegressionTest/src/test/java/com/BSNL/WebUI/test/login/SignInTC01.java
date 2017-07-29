package com.BSNL.WebUI.test.login;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.BSNL.WebUI.Utility.Methods.GlobalVariable;
import com.BSNL.WebUI.Utility.Methods.UtilityMethods;
import com.BSNL.WebUI.page.login.LoginPage;
import com.relevantcodes.extentreports.ExtentReports;


public class SignInTC01 {
	
	private String url,loginExcelPath,browserName,expectedTitle,expectedTitleLogin;
	private String configPath="..\\com.BSNL.WebUI.RegressionTest\\src\\test\\resources\\AutomationConfig.properties";
	UtilityMethods util = new UtilityMethods();
	
	public SignInTC01() throws Exception
	{
		Properties prop = util.LoadProperty(configPath);
		url=prop.getProperty("url");
		loginExcelPath=prop.getProperty("loginExcelPath");
		browserName=util.SearchExcel(loginExcelPath, "Sign In_TC01", "Browser Name");
		expectedTitle=util.SearchExcel(loginExcelPath, "Sign In_TC01", "Field 1");
		expectedTitleLogin=util.SearchExcel(loginExcelPath, "Sign In_TC01", "Field 2");
	}
	
	/* ----------------------Folder Creation Logic Has Changed ----------------------*/
	/*public void folderCreate()
	{
		try{	
		//Get system time in MMddyyyy_HHMM format
		Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
		//Date timeStamp = new Date();
		SimpleDateFormat smplDtFrmt = new SimpleDateFormat("yyyy-MM-dd_HHmm");
		GlobalVariable.strDirectoy =smplDtFrmt.format(timeStamp);
		String path = "..\\com.BSNL.WebUI.RegressionTest\\TestResult\\Login_"+GlobalVariable.strDirectoy;
		//Create Folder for result file storage
		GlobalVariable.dir=new File(path);
		boolean successful1 = GlobalVariable.dir.mkdir();
		GlobalVariable.dir2=new File(path+File.separator+"Screenshot");
		//Create Folder for result file storage
		boolean successful2 = GlobalVariable.dir2.mkdir();
	    if (successful1&successful2)
	    {
	      // creating the directory succeeded
	      System.out.println("Directory was created successfully");
	    }
	    else
	    {
	      // creating the directory failed
	      System.out.println("Failed -- trying to create the directory");
	    }
	    //Set the full filename for extent reports 
	    String filename = GlobalVariable.dir+"\\Login_Result.html";
	    GlobalVariable.strDirectoy="Login_"+GlobalVariable.strDirectoy;
	    GlobalVariable.extent = new ExtentReports(filename);
		}catch(Exception e){
			e.printStackTrace();
		}
	}*/
	
	@BeforeTest
	public void folderCreate()
	{
		boolean mkSuccessful = false,deleteSuccessful,srnshtSuccessful;
		try{	
		GlobalVariable.strDirectoy ="Login";
		String path = "..\\com.BSNL.WebUI.RegressionTest\\TestResult\\Test Result_"+GlobalVariable.strDirectoy;
		//Create Folder for result file storage
		GlobalVariable.dir=new File(path);
		if(GlobalVariable.dir.exists())
		{
			deleteSuccessful=util.removeDirectory(GlobalVariable.dir);
			if(deleteSuccessful){
			System.out.println("Existing Directory Deleted");
			mkSuccessful = GlobalVariable.dir.mkdir();
			}
			else
				System.out.println("Cannot Delete Existing Directory");	
		}
		else
		{
			mkSuccessful = GlobalVariable.dir.mkdir();
		}
		GlobalVariable.dir2=new File(path+File.separator+"Screenshot");
		//Create Folder for result file storage
		srnshtSuccessful = GlobalVariable.dir2.mkdir();
	    if (mkSuccessful&srnshtSuccessful)
	    {
	      // creating the directory succeeded
	      System.out.println("Directory was created successfully");
	    }
	    else
	    {
	      // creating the directory failed
	      System.out.println("Failed -- trying to create the directory");
	    }
	    //Set the full filename for extent reports 
	    String filename = GlobalVariable.dir+"\\Login_Result.html";
	    GlobalVariable.strDirectoy="Test Result_"+GlobalVariable.strDirectoy;
	    GlobalVariable.extent = new ExtentReports(filename);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@BeforeMethod
	public void Setup()
	{
		GlobalVariable.driver = util.openURL(browserName,url);
	}
	
	@Test
	public void SignIn_TC01() throws Exception
	{
		GlobalVariable.test=GlobalVariable.extent.startTest("Test Case Id : SignIn TC01", "Description: To Validate that Login Page will Open in Chrome");
		String actualTitle = GlobalVariable.driver.getTitle();
		util.waitInSecond(3);
		util.extentValidation("Navigate to https://portal2.bsnl.in/myportal/", expectedTitle, actualTitle, true, false, false);
		LoginPage login =new LoginPage();
		String status=login.signIn();
		util.extentValidation("Sign in option is present - Check", "Y", status, true, false, false);
		actualTitle = GlobalVariable.driver.getTitle();
		util.extentValidation("Sign in Page should appear - Check", expectedTitleLogin, actualTitle, true, false, false);
		status=login.LoginElementCheck();
		util.extentValidation("Username field should be present - Check", "Y", status, true, false, false);
		util.extentValidation("Password field should be present - Check", "Y", status, true, false, false);
		
	}
	
	@AfterMethod
	public void TearDown() throws IOException
	{
		GlobalVariable.wb.close();
		GlobalVariable.driver.close();
		GlobalVariable.extent.endTest(GlobalVariable.test);
	}
	
	@AfterTest
	public void closeExtentReport() throws IOException
	{
		GlobalVariable.extent.flush();	
	}	
}
