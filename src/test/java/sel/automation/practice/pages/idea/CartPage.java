package sel.automation.practice.pages.idea;

import org.apache.commons.math3.util.Precision;
import sel.automation.practice.pages.components.PageHelper;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertEquals;

public class CartPage {

    public  static final ArrayList<String> orderList = new ArrayList<>();
    public  static final ArrayList<String> orderTotalCost = new ArrayList<>();

    private PageHelper ph = new PageHelper();
    MainPage mp = new MainPage();


    public static CartPage enterToCartPage() {

        return page(CartPage.class);
    }

    public CartPage shoppingCartIsVisible() {

        //the cart's list should be visible
        $("#cart_summary tbody").isDisplayed();
        return page(CartPage.class);
    }


    public int getShoppingCartNumberOfOrders() {

        //the cart list's number must be equal 3
        return ph.getSizeFromCss("#cart_summary > tbody > tr");
    }


    public CartPage clickToProceedToCheckOutButton() {

        ph.clickOnCssElement(".standard-checkout");
        return page(CartPage.class);
    }

    public String checkDeliveryAddressName() {

        return ph.getTextFromCssElement("#address_delivery li:nth-child(2)");
    }

    public String checkBillingAddressName() {

        return ph.getTextFromCssElement("#address_invoice li:nth-child(2)");
    }

    public CartPage clickToProcessAddressButton() {

        ph.clickOnCssElement("#center_column button[name='processAddress']");
        return page(CartPage.class);
    }

    public String shippingOptionIsChecked() {

        return ph.getAttributeFromCssElement("input.delivery_option_radio", "checked");
    }


    public CartPage completeShippingOptions() {

        String shipCost = ph.getTextFromCssElement("div.delivery_option_price");

        //collect an order's total cost information
        orderTotalCost.add(shipCost);

        //click On Terms And Servise Checkbox
        ph.clickOnCssElement("#uniform-cgv");

        return page(CartPage.class);
    }


    public CartPage clickToProcessCarrierButton() {

        ph.clickOnCssElement("#center_column button[name='processCarrier']");
        return page(CartPage.class);
    }

    public int totalNumberOfOrders() {

        return ph.getSizeFromCss("#cart_summary tbody > tr");

    }

    public CartPage ordersInfo() {

        String totOrderCost = ph.getTextFromCssElement("#total_price");

        //xpath for order's titles and prices
        String xpathEndpoint = "//*[@id='cart_summary']/tbody/tr";
        String titleXpath = "/td[2]/p/a";
        String priceXpath = "/td[6]/span";

        ph.arrayCollecting(orderList, xpathEndpoint, titleXpath, priceXpath);

        //collect the order's total cost information
        orderTotalCost.add(totOrderCost);

        return page(CartPage.class);
    }

    public float totalUserCost() {

        String abc = String.valueOf(orderTotalCost.get(1)).replace("$", "");
        return Float.parseFloat(abc);

    }


    public ArrayList<String> getOrderList() {

        ordersInfo();
        return orderList;
    }

    public ArrayList<String> getOrdertotalCost() {

        return orderTotalCost;
    }

    public CartPage checkCartAndOrderLists() {

        assertEquals(mp.getCartList(), getOrderList());
        return page(CartPage.class);
    }

    public CartPage checkTotalLists() {

        assertEquals(mp.getCartListTotal(), getOrdertotalCost());
        return page(CartPage.class);
    }


    public float totalSumCounting() {

        float itemPrices = 0;

        for(int i=1; i<6; i=i+2) {

            itemPrices += Float.parseFloat(orderList.get(i).replace("$", ""));

        }

        String shippingCost = orderTotalCost.get(0).replace("$", "");
        float shippingCost_f = Float.parseFloat(shippingCost);

        float totalCost = itemPrices + shippingCost_f;

        return Precision.round(totalCost, 2);


    }


    public CartPage payByBankWireClick() {

        ph.clickOnCssElement(".bankwire");

        return page(CartPage.class);
    }


    public String orderSummaryText() {

        return ph.getTextFromCssElement("#center_column h3");
    }


    public String bankWirePaymentConfirmation() {

        return ph.getTextFromCssElement("#amount");
    }

    public CartPage confirmMyOrder() {

        ph.clickOnCssElement("#cart_navigation > button");
        return page(CartPage.class);
    }

    public String orderConfirmationSubPage() {

        return ph.getTextFromCssElement("#center_column > h1");
    }

    public String finalAmountConfirmation() {

        //confirmation the final amount
        return ph.getTextFromCssElement("#center_column > div > span > strong");
    }


}
