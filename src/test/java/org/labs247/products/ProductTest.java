package org.labs247.products;

import org.labs247.base.LoggedInUserBaseTestSetup;
import org.labs247.pages.LeftSideMenu;
import org.testng.annotations.Test;

public class ProductTest extends LoggedInUserBaseTestSetup {

    @Test(description = "Verify the admin user can create product")
    public void verifyCreateProduct() {

        LeftSideMenu leftSideMenu = new LeftSideMenu(getDriver());

        leftSideMenu.clickOnHamburgerButton();
        leftSideMenu.selectModuleFromLeftSideMenu("Products");
        leftSideMenu.pauseForMillsSeconds(5000);
    }
}
