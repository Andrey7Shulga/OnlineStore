package sel.automation.practice.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import sel.automation.practice.listeners.TestListener;
import sel.automation.practice.listeners.WebDriverListener;
import sel.automation.practice.pages.idea.CartPage;
import sel.automation.practice.pages.idea.MainPage;

import static com.codeborne.selenide.Selenide.*;
import static org.testng.AssertJUnit.assertEquals;
import static sel.automation.practice.pages.idea.CartPage.enterToCartPage;
import static sel.automation.practice.pages.idea.CartPage.orderTotalCost;
import static sel.automation.practice.pages.idea.MainPage.enterToMainPage;

@Listeners(TestListener.class)

public class Shopping {

    private WebDriver driver;

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



    @BeforeClass
    public void setup() {
        //Log4J configuration
        BasicConfigurator.configure();

        Configuration.timeout = 10000;
        Configuration.collectionsTimeout = 10000;

        //WebDriver setup
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        WebDriverRunner.setWebDriver(driver);
        driver.manage().window().maximize();

        //WebListener setup
        EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);
        eventFiringWebDriver.register(new WebDriverListener());
        driver = eventFiringWebDriver;

        driver.get(url);

    }


    @Test
    public void chain() {
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
                .checkTotalLists()
                .payByBankWireClick();
                assertEquals(cartPage.totalSumCounting(), cartPage.totalUserCost());
                assertEquals(cartPage.orderSummaryText(), confirmationPageTextOne);
                assertEquals(cartPage.bankWirePaymentConfirmation(), orderTotalCost.get(1));

        enterToCartPage()
                .confirmMyOrder();
                assertEquals(cartPage.orderConfirmationSubPage(), confirmationPageTextTwo);
                assertEquals(cartPage.finalAmountConfirmation(), orderTotalCost.get(1));

        enterToMainPage()
                .logOut()
                .isLogOutCorrect();

    }


    @AfterClass
    public void quitForever() {

        if (driver != null) {
            clearBrowserCookies();
            driver.quit();
        }

    }

}
