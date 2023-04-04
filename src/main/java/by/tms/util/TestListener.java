package by.tms.util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        takeScreenShot(result);
    }

    private void takeScreenShot(ITestResult iTestResult) {
        ITestContext context = iTestResult.getTestContext();
        try {
            WebDriver webDriver = (WebDriver) context.getAttribute("driver");
            if (webDriver != null) {
                File srcFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
                FileUtils.moveFile(srcFile, new File(System.getProperty("user.dir") + "/src/main/resources/screenshots"
                                + iTestResult.getMethod().getMethodName() + ".png"));
            }
        } catch (NoSuchSessionException | IllegalStateException | IOException ex) {
            System.out.println("Screenshot was not created because of error: \n" + ex.getLocalizedMessage());
        }
    }
}
