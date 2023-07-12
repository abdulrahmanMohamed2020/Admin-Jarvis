package org.labs247.uitils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.labs247.constants.Constants;

import java.util.HashMap;
import java.util.Map;

public class LoginHelper {

    private static String token;

//    public static void loginWithEmail(WebDriver driver) {
//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.enterEmail(Constants.VALID_EMAIL_PAY_LATER_USER);
//        loginPage.clickOnContinue();
//    }
//
//    public static void enterThePinCode(WebDriver driver) {
//        VerifyPinPage verifyPinPage = new VerifyPinPage(driver);
//        verifyPinPage.enterSixPinCode(Constants.MASTER_PIN_CODE);
//        verifyPinPage.clickOnVerifyPinButton();
//    }

    public static void loginToApplicationApi() {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(getUserDataParams())
                .post("https://api-admin-testing-internal.guardme-jarvis.dev/api/company/auth/sign-in");

        token = response.body().jsonPath().getString("data.token");
    }
    private static Map<String,String> getUserDataParams() {

        Map<String,String> userDataMap = new HashMap<>();
        userDataMap.put("email", Constants.VALID_EMAIL_USER);
        userDataMap.put("pin", "000000");
        userDataMap.put("password", "000000");
        userDataMap.put("type", "email");

        return userDataMap;
    }

    public static String getToken() {
        return token;
    }

}
