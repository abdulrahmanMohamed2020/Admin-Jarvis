package org.labs247.core;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.*;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BasePage {

    protected WebDriver driver;
    private WebDriverWait driverWait;
    private static final Duration TIMEOUT = Duration.ofSeconds(5);
    private static final Duration POLLING_TIMEOUT = Duration.ofMillis(200);

    public BasePage(WebDriver driver) {
        this.driver = driver;
        driverWait = new WebDriverWait(driver, TIMEOUT);
    }

    public BasePage(WebDriver driver, Duration timeout) {
        this.driver = driver;
        this.driverWait = new WebDriverWait(driver, timeout);
    }

    public WebElement findElement(By locator) {
        Wait<WebDriver> wait = getFluentWait();
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public List<WebElement> findElements(By locator) {
        Wait<WebDriver> wait = getFluentWait();
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public boolean elementVisible(By locator) {
        scrollToElement(findElement(locator));
        boolean flag;
        try {
            WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            flag = true;
        } catch (Exception ex) {
            flag = false;
        }
        return flag;
    }

    public boolean elementNotVisible(By locator) {
        scrollToElement(findElement(locator));
        boolean flag;
        try {
            WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
            wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOfElementLocated(locator)));
            flag = true;
        } catch (Exception ex) {
            flag = false;
        }
        return flag;
    }

    public boolean elementClickable(By locator) {
        scrollToElement(findElement(locator));
        boolean flag;
        try {
            WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            flag = true;
        } catch (Exception ex) {
            flag = false;
        }
        return flag;
    }

    public boolean elementNotClickable(By locator) {
        scrollToElement(findElement(locator));
        boolean flag;
        try {
            WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
            wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(locator)));
            flag = true;
        } catch (Exception ex) {
            flag = false;
        }
        return flag;
    }

    public void actionClick(By locator) {
        WebElement element = findElement(locator);
        scrollToElement(element);

        driverWait.until(ExpectedConditions.visibilityOf(element));
        driverWait.until(ExpectedConditions.elementToBeClickable(element));
        try {
            element.click();
        } catch (StaleElementReferenceException ex) {
            findElement(locator).click();
        }
    }

    public void actionClick(WebElement element) {
        scrollToElement(element);

        driverWait.until(ExpectedConditions.visibilityOf(element));
        driverWait.until(ExpectedConditions.elementToBeClickable(element));
        try {
            element.click();
        } catch (StaleElementReferenceException ex) {
            element.click();
        }
    }

    public void typeText(By locator, String text) {
        WebElement element = findElement(locator);
        scrollToElement(element);

        driverWait.until(ExpectedConditions.visibilityOf(element));
        try {
            element.clear();
            element.sendKeys(text);
        } catch (StaleElementReferenceException ex) {
            findElement(locator).clear();
            findElement(locator).sendKeys(text);
        }
    }

    public void selectItem(String item, By dropDown) {

        // Create a Select object with the dropdown element
        Select select = new Select(driver.findElement(dropDown));
        // Select an option by value
        select.selectByValue(item);
    }

    protected void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }

    protected void scrollToElement(By locator) {
        WebElement element = findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }

    private Wait<WebDriver> getFluentWait() {
        return new FluentWait<>(driver)
                .withTimeout(TIMEOUT)
                .pollingEvery(POLLING_TIMEOUT)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
    }

    protected List<String> getTable() {
        List<WebElement> rows = findElements(By.tagName("tr"));
        ArrayList<String> cellValues = new ArrayList<>();


        rows.forEach(row ->{
            List<WebElement> cells = row.findElements(By.tagName("td"));
            cells.forEach(cell -> {
                String cellText = cell.getText();
                if (!cellText.isEmpty()) {
                    cellValues.add(cellText);
                }
            });
        });

        //cellValues.forEach(System.out::println);
        return cellValues ;
    }

    protected List<WebElement> getTableElementsBulkImport(By tableLocator) {
        return findElement(tableLocator).findElements(By.tagName("tr"));
    }

    protected void clickOutSide() {
        // Create an instance of the Actions class
        Actions actions = new Actions(driver);

        // Perform a click outside the <aside> element
        actions.moveToElement(driver.findElement(By.tagName("body"))).click().perform();
    }

    public void navigateToPage(String pageUrl) {
        driver.navigate().to(pageUrl);
    }

    public void pauseForMillsSeconds(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearField(By locator) {
        clearFieldUsingJavaScript(findElement(locator));
    }
    private void clearFieldUsingJavaScript(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", element);
    }

    public void actionUsingJavaScript(By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", findElement(locator));
    }

    public void writeTextUsingJavaScript(By locator,String text) {
        WebElement inputField = findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1];",inputField , text);
        ((JavascriptExecutor) driver).executeScript("var event = new Event('input'); arguments[0].dispatchEvent(event);", inputField);
    }

    public void uploadFileToRemoteDriver(String filePath) {
        // Find the file drop area element
        WebElement fileDropArea = findElement(By.xpath("//input[@type='file']"));

        ((RemoteWebElement)fileDropArea).setFileDetector(new LocalFileDetector());
        fileDropArea.sendKeys(filePath);
    }
}
