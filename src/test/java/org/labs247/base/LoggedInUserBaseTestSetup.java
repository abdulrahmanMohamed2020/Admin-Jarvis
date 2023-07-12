package org.labs247.base;

import org.labs247.constants.Constants;
import org.labs247.uitils.LoginHelper;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;

@Listeners({TestListenerSetup.class})
public class LoggedInUserBaseTestSetup extends BaseTestSetup {

    @Parameters(value={"browser","userType"})
    @BeforeMethod(description = "initialize the web drive", alwaysRun = true)
    @Override
    public void setUp(@Optional("firefox") String browser,@Optional("payLater") String userType) throws MalformedURLException {

        LoginHelper.loginToApplicationApi();
        driverThreadLocal.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilityFactory.getCapabilities(browser)));

        String[] tokenSplitter = LoginHelper.getToken().split("\\|");

        Cookie cookie = new Cookie("auth._token.company","Bearer%20"+tokenSplitter[0]+"%7C"+tokenSplitter[1]);

        driverThreadLocal.get().get(BASE_URL);
        driverThreadLocal.get().manage().addCookie(cookie);
        driverThreadLocal.get().get(BASE_URL);
        driverThreadLocal.get().manage().window().maximize();
    }

    @Override
    public WebDriver getDriver(){
        return driverThreadLocal.get();
    }

    @AfterMethod(description = "close the web drive", alwaysRun = true)
    public void tearDown() {
        WebDriver driver = getDriver();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
    }
}