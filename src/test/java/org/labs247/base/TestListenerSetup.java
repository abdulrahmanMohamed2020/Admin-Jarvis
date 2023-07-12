package org.labs247.base;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TestListenerSetup extends BaseTestSetup implements ITestListener {

    @Attachment(value = "Screenshot", type = "image/png")
    private static byte[] takeScreenShotForAllure(WebDriver webDriver) {
        return ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "{0}", type = "text/plain")
    private static String saveTextLog(String message) {
        return message;
    }

    private static void saveScreenShot(WebDriver webDriver, ITestResult result){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        String methodName = result.getMethod().getMethodName();
        File scrFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(("Screenshots/"+methodName+"_"+formater.format(calendar.getTime())+".png")));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = getDriver();
        if (driver != null) {
            takeScreenShotForAllure(driver);
        }
        saveTextLog(result.getMethod().getMethodName() + " failed and screenshot taken.");
        //TestListenerSetup.saveScreenShot(getDriver(),result);
    }
}
