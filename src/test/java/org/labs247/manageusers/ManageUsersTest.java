package org.labs247.manageusers;

import org.labs247.base.CustomSoftAssert;
import org.labs247.base.LoggedInUserBaseTestSetup;
import org.labs247.pages.LeftSideMenu;
import org.labs247.pages.manageusers.ManageUsersPage;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class ManageUsersTest extends LoggedInUserBaseTestSetup {

    private CustomSoftAssert softAssert;

    @Test(description = "Verify the admin user can create and edit user")
    public void verifyCreateAndEditUser() {

        softAssert = new CustomSoftAssert();
        Map<String,String> userData = getUserTestData();

        LeftSideMenu leftSideMenu = new LeftSideMenu(getDriver());

        leftSideMenu.clickOnHamburgerButton();
        leftSideMenu.selectModuleFromLeftSideMenu("Manage Users");

        ManageUsersPage manageUsersPage = new ManageUsersPage(getDriver());

        manageUsersPage.clickOnNewUsersButton();
        manageUsersPage.enterFirstName(userData.get("firstName"));
        manageUsersPage.enterLastName(userData.get("lastName"));
        manageUsersPage.enterEmail(userData.get("email"));
        manageUsersPage.clickOnUserType();
        manageUsersPage.selectUserType(userData.get("userType"));
        manageUsersPage.clickOutSideTag();
        manageUsersPage.clickOnCompanies();

        manageUsersPage.selectCompany(userData.get("company"));
        manageUsersPage.clickOnSaveButton();

        manageUsersPage.pauseForMillsSeconds(1000);

        manageUsersPage.enterSearchKeyword(userData.get("firstName")+" "+ userData.get("lastName"));

        manageUsersPage.pauseForMillsSeconds(1000);

        // Validate the data is displayed correctly in the table
        softAssert.assertEquals(manageUsersPage.getTableData().get(0),userData.get("firstName")+" "+ userData.get("lastName"),"The user name is wrong!");
        softAssert.assertEquals(manageUsersPage.getTableData().get(1),userData.get("email"),"The email is wrong!");
        softAssert.assertEquals(manageUsersPage.getTableData().get(2),userData.get("company"),"The company is wrong!");
        softAssert.assertEquals(manageUsersPage.getTableData().get(3),userData.get("userType"),"The user type is wrong!");

        //

        manageUsersPage.clickOnActionButton();
        manageUsersPage.clickOnEditUserButton();

        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyHHms");
        String editedFirstName = "Edited_FN"+formatter.format(new Date());
        System.out.println(editedFirstName);
        manageUsersPage.pauseForMillsSeconds(3000);
        manageUsersPage.enterFirstName(editedFirstName);
        manageUsersPage.pauseForMillsSeconds(3000);
        manageUsersPage.clickOnSaveButton();

        manageUsersPage.pauseForMillsSeconds(3000);

        softAssert.assertEquals(manageUsersPage.getTableData().get(0),editedFirstName+" "+ userData.get("lastName"),"The user name is not updated");


        softAssert.assertAll();
    }

    private Map<String,String> getUserTestData() {
        Map<String,String> dataMap = new LinkedHashMap<>();
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyHHms");
        String firstName = "Auto_FN"+formatter.format(new Date());
        String lastName = "Auto_LN"+formatter.format(new Date());
        String email = "Auto_"+formatter.format(new Date())+"@test.com";

        dataMap.put("firstName",firstName);
        dataMap.put("lastName",lastName);
        dataMap.put("email",email);
        dataMap.put("userType","Super Admin");
        dataMap.put("company","Guard.me (UK)");

        return dataMap;
    }

}
