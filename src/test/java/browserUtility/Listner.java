package browserUtility;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class Listner implements ITestListener {

    ExtentReports extent;
    ExtentTest test;

    @Override
    public void onStart(ITestContext context) {
        // Initialize ExtentReports instance
        File path = new File("/Users/poonamsharma/eclipse-workspace1/Erail.in/reports/index.html");
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("WebAutomationName");
        reporter.config().setDocumentTitle("TestResults");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Poonam Sharma");
        System.out.println("ExtentReports initialized.");

        System.out.println("Test Suite started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
        System.out.println("ExtentReports flushed.");
        System.out.println("Test Suite finished: " + context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        System.out.println("Test started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        if (test == null) {
            test = extent.createTest(result.getMethod().getMethodName());
        }
        test.log(Status.PASS, "Test passed: " + result.getMethod().getMethodName());
        System.out.println("Test passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        if (test == null) {
            test = extent.createTest(result.getMethod().getMethodName());
        }
        test.fail(result.getThrowable());
        test.log(Status.FAIL, "Test failed: " + result.getMethod().getMethodName());
        test.log(Status.FAIL, "Error Message: " + result.getThrowable().getMessage());
        test.log(Status.FAIL, "Stack Trace: " + result.getThrowable().toString());
        System.out.println("Test failed: " + result.getMethod().getMethodName());
        result.getThrowable().printStackTrace();
    }


    @Override
    public void onTestSkipped(ITestResult result) {
        if (test == null) {
            test = extent.createTest(result.getMethod().getMethodName());
        }
        test.log(Status.SKIP, "Test skipped: " + result.getMethod().getMethodName());
        System.out.println("Test skipped: " + result.getMethod().getMethodName());
    }
}
