package sel.automation.practice.listeners;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    Logger logger = Logger.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult iTestResult) {
        logger.info("Test started");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        logger.info("Test is successful");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        logger.info("Test failed");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        logger.info("Test skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}
