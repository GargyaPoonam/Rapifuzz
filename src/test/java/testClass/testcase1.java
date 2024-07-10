package testClass;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import PageClass.HomePage;
import browserUtility.BaseClass;
import browserUtility.ConfiDataProvider;
import browserUtility.ExcelDataDriven;


@Listeners(browserUtility.Listner.class)
public class testcase1 {
	
	WebDriver driver;
	ConfiDataProvider config;
	ExcelDataDriven excel;
	
@BeforeSuite()
public void setupsuite() throws IOException {
	config = new ConfiDataProvider();
	excel = new ExcelDataDriven();
	
}
@BeforeClass
public void f() {
	driver =BaseClass.getBrowser(driver, config.getBrowser(), config.getUrl());
	
}
@Test()
public void Test1() throws InterruptedException, IOException {
	
	HomePage H1 = new HomePage(driver);
	
	//H1.inputStation_from("Del");
	Thread.sleep(2000);
	//H1.inputDate();
	Thread.sleep(2000);
	H1.stataionverify();
	
	
}

@AfterClass
public void closebrowser() {
	BaseClass.teardown(driver);
}

}
