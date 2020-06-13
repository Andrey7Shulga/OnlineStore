package sel.automation.practice.pages.idea;

import org.apache.commons.math3.util.Precision;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertEquals;

public class CartPage {

    public  static final ArrayList<String> orderList = new ArrayList<String>();
    public  static final ArrayList<String> orderTotalCost = new ArrayList<String>();

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
        int size = $$("#cart_summary > tbody > tr").size();
        return size;
    }


    public CartPage clickToProceedToCheckOutButton() {

        $(".standard-checkout").click();
        return page(CartPage.class);
    }

    public String checkDeliveryAddressName() {

        String deliveryName = $("#address_delivery li:nth-child(2)").getText();
        return deliveryName;
    }

    public String checkBillingAddressName() {

        String billingName = $("#address_invoice li:nth-child(2)").getText();
        return billingName;
    }

    public CartPage clickToProcessAddressButton() {

        $("#center_column button[name='processAddress']").click();
        return page(CartPage.class);
    }

    public String shippingOptionIsChecked() {

        String deliveryActivated = $("input.delivery_option_radio").getAttribute("checked");
        return deliveryActivated;
    }


    public CartPage completeShippingOptions() {

        String shipCost = $("div.delivery_option_price").getText();

        //collect an order's total cost information
        orderTotalCost.add(shipCost);

        //click On Terms And Servise Checkbox
        $("#uniform-cgv").click();

        return page(CartPage.class);
    }


    public CartPage clickToProcessCarrierButton() {

        $("#center_column button[name='processCarrier']").click();
        return page(CartPage.class);
    }

    public int totalNumberOfOrders() {

        int total = $$("#cart_summary tbody > tr").size();
        return total;
    }

    public CartPage ordersInfo() {

        String firstOrderTitle = $("#cart_summary tr:nth-child(1) p.product-name").getText();
        String firstOrderPrice = $("#cart_summary tr:nth-child(1) td:nth-child(4) .price").getText();
        String secondOrderTitle = $("#cart_summary tr:nth-child(2) p.product-name").getText();
        String secondOrderPrice = $(".special-price").getText();
        String thirdOrderTitle = $("#cart_summary tr:nth-child(3) p.product-name").getText();
        String thirdOrderPrice = $("#cart_summary tr:nth-child(3) td:nth-child(4) .price").getText();
        String totOrderCost = $("#total_price").getText();

        //collect an order (three items' names and prices) information
        orderList.add(firstOrderTitle);
        orderList.add(firstOrderPrice);
        orderList.add(secondOrderTitle);
        orderList.add(secondOrderPrice);
        orderList.add(thirdOrderTitle);
        orderList.add(thirdOrderPrice);

        //collect the order's total cost information
        orderTotalCost.add(totOrderCost);

        return page(CartPage.class);
    }

    public float totalUserCost() {

        String abc = String.valueOf(orderTotalCost.get(1)).replace("$", "");
        float totalUserCost = Float.parseFloat(abc);

        return totalUserCost;
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

        String shippingCost = orderTotalCost.get(0).replace("$", "");
        String priceItemOne = orderList.get(1).replace("$", "");
        String priceItemTwo = orderList.get(3).replace("$", "");
        String priceItemThree = orderList.get(5).replace("$", "");

        float shippingCost_f = Float.parseFloat(shippingCost);
        float priceItemOne_f = Float.parseFloat(priceItemOne);
        float priceItemTwo_f = Float.parseFloat(priceItemTwo);
        float priceItemThree_f = Float.parseFloat(priceItemThree);

        float abc = shippingCost_f + priceItemOne_f + priceItemTwo_f + priceItemThree_f;
        float totalSumCounting = Precision.round(abc, 2);

        return totalSumCounting;

    }


    public CartPage payByBankWireClick() {

        $(".bankwire").click();
        return page(CartPage.class);
    }


    public String orderSummaryText() {

        String cde = $("#center_column h3").getText();
        return cde;
    }


    public String bankWirePaymentConfirmation() {

        String amount_conf = $("#amount").getText();
        return amount_conf;
    }

    public CartPage confirmMyOrder() {

        //press 'I confirm my order' button
        $("#cart_navigation > button").click();
        return page(CartPage.class);
    }

    public String orderConfirmationSubPage() {

        String endless = $("#center_column > h1").getText();
        return endless;
    }

    public String finalAmountConfirmation() {

        //confirmation the final amount
        String finalAmount = $("#center_column > div > span > strong").getText();
        return finalAmount;
    }


}
