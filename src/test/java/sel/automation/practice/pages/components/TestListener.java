package sel.automation.practice.pages.components;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    Logger logger = Logger.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result) {

        logger.info("Test started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        logger.info("Test completed successfully");

    }

    @Override
    public void onTestFailure(ITestResult result) {

        logger.info("Test failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        logger.info("Test skipped");


    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }
}
