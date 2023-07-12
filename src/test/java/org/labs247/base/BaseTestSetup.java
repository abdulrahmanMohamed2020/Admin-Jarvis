package org.labs247.base;

import org.labs247.core.CapabilityFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;

@Listeners({TestListenerSetup.class})
public class BaseTestSetup {

    protected static ThreadLocal<RemoteWebDriver> driverThreadLocal = new ThreadLocal<>();
    protected final String BASE_URL = "https://admin.testing-internal.guardme-jarvis.dev/";

    public CapabilityFactory capabilityFactory = new CapabilityFactory();

    @Parameters(value={"browser","userType"})
    @BeforeMethod(description = "initialize the web drive", alwaysRun = true)
    public void setUp(@Optional("firefox") String browser,@Optional("payLater") String userType) throws MalformedURLException {
        driverThreadLocal.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilityFactory.getCapabilities(browser)));
        driverThreadLocal.get().get(BASE_URL);
    }

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
