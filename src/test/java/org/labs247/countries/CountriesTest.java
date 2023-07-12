package org.labs247.countries;

import org.labs247.base.CustomSoftAssert;
import org.labs247.base.LoggedInUserBaseTestSetup;
import org.labs247.pages.LeftSideMenu;
import org.labs247.pages.countries.CountriesPage;
import org.testng.annotations.Test;

public class CountriesTest extends LoggedInUserBaseTestSetup {

    private static final String COUNTRY_NAME = "Egypt";
    private static final String TAX_RATE = "20";
    private CustomSoftAssert softAssert;

    @Test(description = "Verify the admin user can update the tax rate of specific country")
    public void verifyUpdateTaxRate() {
        softAssert = new CustomSoftAssert();
        LeftSideMenu leftSideMenu = new LeftSideMenu(getDriver());

        leftSideMenu.clickOnHamburgerButton();
        leftSideMenu.selectModuleFromLeftSideMenu("Countries");

        CountriesPage countriesPage = new CountriesPage(getDriver());

        countriesPage.pauseForMillsSeconds(1000);

        countriesPage.enterSearchKeyword(COUNTRY_NAME);

        countriesPage.pauseForMillsSeconds(1000);
        softAssert.assertEquals(countriesPage.getTableData().get(0),COUNTRY_NAME,"The country name is wrong!");

        countriesPage.clickOnActionButton();
        countriesPage.clickOnEditCountryButton();

        countriesPage.enterTaxRate(TAX_RATE);
        countriesPage.clickOnConfirmButton();

        countriesPage.pauseForMillsSeconds(1000);

        softAssert.assertEquals(countriesPage.getTableData().get(2),TAX_RATE+"%","The tax rate is not updated!");

        softAssert.assertAll();
    }
}
