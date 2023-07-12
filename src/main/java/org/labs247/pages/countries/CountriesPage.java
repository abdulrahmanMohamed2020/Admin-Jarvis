package org.labs247.pages.countries;

import io.qameta.allure.Step;
import org.labs247.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class CountriesPage extends BasePage {

    private final By search = By.id("search");
    private final By actionButton = By.xpath("//td[@class='text-start']//button");
    private final By editCountryButton = By.xpath("//div[text()='Edit Country']");
    private final By taxRateField = By.name("tax-rate-0");
    private final By confirmButton = By.id("submit-edit");

    public CountriesPage(WebDriver driver) {
        super(driver);
    }

    @Step("Enter the search keyword: {0}")
    public void enterSearchKeyword(String keyword) {
        typeText(search,keyword);
    }

    @Step("Click on the actions button")
    public void clickOnActionButton() {
        actionClick(actionButton);
    }

    @Step("Click on the edit country button")
    public void clickOnEditCountryButton() {
        actionClick(editCountryButton);
    }

    @Step("Enter the tax rate: {0}")
    public void enterTaxRate(String taxRate) {
        writeTextUsingJavaScript(taxRateField,taxRate);
    }

    @Step("Click on the confirm button")
    public void clickOnConfirmButton() {
        actionClick(confirmButton);
    }

    public List<String> getTableData() {
        return getTable();
    }
}
