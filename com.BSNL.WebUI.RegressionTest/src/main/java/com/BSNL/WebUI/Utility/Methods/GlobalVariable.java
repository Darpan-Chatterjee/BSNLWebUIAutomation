package com.BSNL.WebUI.Utility.Methods;

import java.io.File;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;


public class GlobalVariable {
	
	public static WebDriver driver;
	public static XSSFWorkbook wb;
	public static String strDirectoy;
	public static File dir,dir2;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static int screenshotNum =0; 

}
