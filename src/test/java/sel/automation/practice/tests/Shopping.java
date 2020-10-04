package sel.automation.practice.tests;

import org.testng.annotations.Test;
import sel.automation.practice.pages.CartPage;
import sel.automation.practice.pages.MainPage;

import static org.testng.AssertJUnit.assertEquals;
import static sel.automation.practice.pages.CartPage.enterToCartPage;
import static sel.automation.practice.pages.CartPage.orderTotalCost;
import static sel.automation.practice.pages.MainPage.enterToMainPage;

public class Shopping extends BaseTest{

    MainPage mainPage = new MainPage();
    CartPage cartPage = new CartPage();

    private String url = "http://automationpractice.com/index.php";
    private String myLogIn = "ray_sun@mail.ru";
    private String myPassword = "12345";
    private String userName = "And And";
    private String shippingOptIsCheckedValue = "true";
    private String confirmationPageTextOne = "BANK-WIRE PAYMENT.";
    private String confirmationPageTextTwo = "ORDER CONFIRMATION";
    private int itemNumbers = 3;


    @Test
    public void chain() {

        driver.get(url);

        enterToMainPage()
                .logIn(myLogIn, myPassword)
                .isLoginCorrect(userName)
                .firstItemToChoose()
                .secondItemToChoose()
                .thirdItemToChoose();
                assertEquals(mainPage.checkCartSize(), itemNumbers);
        enterToMainPage()
                .checkCartList()
                .checkItemAndCartLists()
                .orderProcessing();

        enterToCartPage()
                .shoppingCartIsVisible();
                assertEquals(cartPage.getShoppingCartNumberOfOrders(), itemNumbers);
        enterToCartPage()
                .clickToProceedToCheckOutButton();
                assertEquals(cartPage.checkDeliveryAddressName(), userName);
                assertEquals(cartPage.checkBillingAddressName(), userName);
        enterToCartPage()
                .clickToProcessAddressButton();
                assertEquals(cartPage.shippingOptionIsChecked(), shippingOptIsCheckedValue);
        enterToCartPage()
                .completeShippingOptions()
                .clickToProcessCarrierButton();
                assertEquals(cartPage.totalNumberOfOrders(), itemNumbers);
        enterToCartPage()
                .checkCartAndOrderLists()
                .payByBankWireClick();
                assertEquals(cartPage.totalSumCounting(), cartPage.totalUserCost());
                assertEquals(cartPage.orderSummaryText(), confirmationPageTextOne);
                assertEquals(cartPage.bankWirePaymentConfirmation(), orderTotalCost.get(2));

        enterToCartPage()
                .confirmMyOrder();
                assertEquals(cartPage.orderConfirmationSubPage(), confirmationPageTextTwo);
                assertEquals(cartPage.finalAmountConfirmation(), orderTotalCost.get(2));

        enterToMainPage()
                .logOut()
                .isLogOutCorrect();

    }

}
