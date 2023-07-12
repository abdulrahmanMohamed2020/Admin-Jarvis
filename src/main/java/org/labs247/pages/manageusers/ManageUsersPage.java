package org.labs247.pages.manageusers;

import io.qameta.allure.Step;
import org.labs247.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class ManageUsersPage extends BasePage {

    private final By search = By.id("search");
    private final By newUsersButton = By.xpath("//span[contains(text(),'New Users')]");
    private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    private final By email = By.id("user-email");
    private final By userTypeDropDown = By.id("type_name");
    private final By companyDropDown = By.id("id");
    private final By saveButton = By.xpath("//span[text()[normalize-space()='Save']]");
    private final By actionButton = By.xpath("//td[@class='text-start']//button");
    private final By editUserButton = By.xpath("//div[contains(text(),'Edit User')]");

    private final String userTypeValueStr = "//div[text()=' @val']";
    private final String companyValueStr = "//div[@class='v-list-item__content']//child::div[text()='@val']";

    public ManageUsersPage(WebDriver driver) {
        super(driver);
    }

    @Step("Enter the search keyword: {0}")
    public void enterSearchKeyword(String keyword) {
        typeText(search,keyword);
    }

    @Step("Click on the (+ New Users) button")
    public void clickOnNewUsersButton() {
        actionClick(newUsersButton);
    }

    @Step("Enter the first name: {0}")
    public void enterFirstName(String firstNameValue) {
        clearField(firstName);
        writeTextUsingJavaScript(firstName,firstNameValue);
    }

    @Step("Enter the last name: {0}")
    public void enterLastName(String lastNameValue) {
        typeText(lastName,lastNameValue);
    }

    @Step("Enter the email: {0}")
    public void enterEmail(String emailValue) {
        typeText(email,emailValue);
    }

    @Step("Click on the user type dropdown")
    public void clickOnUserType() {
        actionClick(userTypeDropDown);
    }

    @Step("Select {0} from user types")
    public void selectUserType(String userType) {
        actionClick(By.xpath(userTypeValueStr.replace("@val",userType)));
    }

    @Step("Click on the companies dropdown")
    public void clickOnCompanies() {
        actionClick(companyDropDown);
    }

    @Step("Select {0} from companies")
    public void selectCompany(String company) {
        actionClick(By.xpath(companyValueStr.replace("@val",company)));
    }

    @Step("Click on the Save button")
    public void clickOnSaveButton() {
        actionClick(saveButton);
    }

    @Step("Click on the actions button")
    public void clickOnActionButton() {
        actionClick(actionButton);
    }

    @Step("Click on the edit user button")
    public void clickOnEditUserButton() {
        actionClick(editUserButton);
    }

    public void clickOutSideTag() {
        clickOutSide();
    }

    public List<String> getTableData() {
        return getTable();
    }

}
