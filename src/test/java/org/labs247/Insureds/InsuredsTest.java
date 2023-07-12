package org.labs247.Insureds;

import org.labs247.base.LoggedInUserBaseTestSetup;
import org.labs247.pages.Insureds.InsuredsPage;
import org.testng.annotations.Test;

public class InsuredsTest extends LoggedInUserBaseTestSetup {
    private InsuredsPage insuredsPage;

    @Test(description = "Verify the admin user can create insured")
    public void verifyCreateInsured() {

        insuredsPage = new InsuredsPage(getDriver());

        insuredsPage.clickOnNewInsuredButton();
        insuredsPage.clickOnContactSource();
        insuredsPage.selectContactSource("Message Center");
        insuredsPage.pauseForMillsSeconds(3000);
    }
}
