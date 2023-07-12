package org.labs247.pages;

import io.qameta.allure.Step;
import org.labs247.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LeftSideMenu extends BasePage {

    private final By hamburgerButton = By.xpath("//parent::button[not(contains(@role,'button'))]//parent::span//i");
    private final String moduleStr = "//div[contains(text(),'@val')]";

    public LeftSideMenu(WebDriver driver) {
        super(driver);
    }

    @Step("Click on hamburger button")
    public void clickOnHamburgerButton() {
        actionClick(hamburgerButton);
    }

    @Step("Select a/an {0} module")
    public void selectModuleFromLeftSideMenu(String moduleName) {
        actionClick(By.xpath(moduleStr.replace("@val",moduleName)));
    }
}
