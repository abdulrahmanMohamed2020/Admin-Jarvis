package org.labs247.pages.Insureds;

import io.qameta.allure.Step;
import org.labs247.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InsuredsPage extends BasePage {

    private final By addNewInsured = By.xpath("//span[text()='+ New Insured']//parent::button");
    private final By contactSource = By.id("source");
    private final String sourceValueStr = "//div[text()='@val']";

    public InsuredsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Click on (+ New Insured) button")
    public void clickOnNewInsuredButton() {
        findElement(addNewInsured).click();
    }

    @Step("Click on Contact Source")
    public void clickOnContactSource() {
        actionClick(contactSource);
    }

    @Step("Select {0} from contact source")
    public void selectContactSource(String source) {
        actionClick(By.xpath(sourceValueStr.replace("@val",source)));
    }
}
