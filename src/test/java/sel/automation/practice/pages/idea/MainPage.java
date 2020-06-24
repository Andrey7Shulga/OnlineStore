package sel.automation.practice.pages.idea;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import sel.automation.practice.pages.components.MenuBlock;
import sel.automation.practice.pages.components.PageHelper;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertEquals;

public class MainPage {

    private MenuBlock menublock;
    PageHelper ph = new PageHelper();

    public  static final ArrayList<String> itemList = new ArrayList<String>();
    public  static final ArrayList<String> cartList = new ArrayList<String>();
    public  static final ArrayList<String> cartListTotal = new ArrayList<String>();



    public static MainPage enterToMainPage() {
        return page(MainPage.class);

    }

    public MainPage logIn (String login, String password) {

        menublock.blockToLogin();

        //Enter a registered e-mail address as a login
//        $("#email").clear();
//        $("#email").setValue(login);
        ph.setValueToCssElement("#email", login);


        //enter a registered password as a password
//        $("#passwd").clear();
//        $("#passwd").setValue(password);
        ph.setValueToCssElement("#passwd", password);

        //submit the login form
//        $("#SubmitLogin").click();
        ph.clickOnCssElement("#SubmitLogin");

        return page(MainPage.class);

    }

    public MainPage isLoginCorrect(String expectedLogin) {
        //get the logged user's name
//        String currentLogin = $(".account > span:nth-child(1)").getText();
        String currentLogin = ph.getTextFromCssElement(".account > span:nth-child(1)");

        assertEquals(expectedLogin, currentLogin);

        return page(MainPage.class);
    }


    public MainPage firstItemToChoose() {

        menublock.womenMenuBlock();

        //select 'TOP' categories
//        $("#subcategories a[title='Tops']").click();
        ph.clickOnCssElement("#subcategories a[title='Tops']");

        //select 'BLOUSES' subcategories
//        $("#subcategories a[title='Blouses']").click();
        ph.clickOnCssElement("#subcategories a[title='Blouses']");

        //hover an element to get some attributes
//        $(".product_img_link").hover();
        ph.hoverOnCssElement(".product_img_link");

//        String firstItemName = $(".right-block .product-name").getText();
//        String firstItemPrice = $("div.content_price span.price").getText();
        String firstItemName = ph.getTextFromCssElement(".right-block .product-name");
        String firstItemPrice = ph.getTextFromCssElement("div.content_price span.price");

        //collect the first item information from the list
        itemList.add(firstItemName);
        itemList.add(firstItemPrice);

        //add an item to a cart and close the cart window
//        $(".ajax_add_to_cart_button").click();
//        $(".cross").click();
        ph.clickOnCssElement(".ajax_add_to_cart_button");
        ph.clickOnCssElement(".cross");

        return page(MainPage.class);

    }

    public MainPage secondItemToChoose() {

        menublock.dressesMenuBlock();

        //select 'Summer Dresses' category
//        $("#subcategories a[title='Summer Dresses']").click();
        ph.clickOnCssElement("#subcategories a[title='Summer Dresses']");

        //hover an element to get some attributes
//        $("li.ajax_block_product:nth-child(3) .product_img_link").hover();
        ph.hoverOnCssElement("li.ajax_block_product:nth-child(3) .product_img_link");

//        String secondItemName = $("#center_column li:nth-child(3) a.product-name").getText();
        String secondItemName = ph.getTextFromCssElement("#center_column li:nth-child(3) a.product-name");

//        String secondItemPrice =
//                $x("/html/body/div/div[2]/div/div[3]/div[2]/ul/li[3]/div/div[1]/div/div[2]/span[1]").getText();
        String secondItemPrice =
                ph.getTextFromXpathElement("/html/body/div/div[2]/div/div[3]/div[2]/ul/li[3]/div/div[1]/div/div[2]/span[1]");

        //collect the second item information from the list
        itemList.add(secondItemName);
        itemList.add(secondItemPrice);

        //add an item to cart and close a cart window
//        $("li.ajax_block_product:nth-child(3) a[title='Add to cart']").click();
//        $(".cross").click();
        ph.clickOnCssElement("li.ajax_block_product:nth-child(3) a[title='Add to cart']");
        ph.clickOnCssElement(".cross");

        return page(MainPage.class);

    }

    public MainPage thirdItemToChoose() {

        menublock.tshirtsMenuBlock();

        //hover an element to get some attributes
//        $(".product_img_link").hover();
        ph.hoverOnCssElement(".product_img_link");

//        String thirdItemName = $(".right-block .product-name").getText();
//        String thirdItemPrice = $("div.content_price:nth-child(4) > span:nth-child(1)").getText();
        String thirdItemName = ph.getTextFromCssElement(".right-block .product-name");
        String thirdItemPrice = ph.getTextFromCssElement("div.content_price:nth-child(4) > span:nth-child(1)");

        //Collect the third item information from the list
        itemList.add(thirdItemName);
        itemList.add(thirdItemPrice);

        //add an item to cart and close a cart window
//        $(".ajax_add_to_cart_button").click();
//        $(".cross").click();
        ph.clickOnCssElement(".ajax_add_to_cart_button");
        ph.clickOnCssElement(".cross");

        return page(MainPage.class);

    }


    public ArrayList<String> getItemList () {

        return itemList;
    }


    public int checkCartSize() {

//        $(".shopping_cart > a:nth-child(1)").hover();
        ph.hoverOnCssElement(".shopping_cart > a:nth-child(1)");

//        int mainCartSize =  $$(".products dt").size();
        int mainCartSize =  ph.getSizeFromCss(".products dt");

        return mainCartSize;

    }

    public MainPage checkCartList() {

//        String cartItemOneName = $("dt.first_item a.cart_block_product_name").getAttribute("title");
//        String cartItemOnePrice = $("dt.first_item span.price").getText();
//        String cartItemTwoName = $("dt.item a.cart_block_product_name").getAttribute("title");
//        String cartItemTwoPrice = $("dt.item span.price").getText();
//        String cartItemThreeName = $("dt.last_item a.cart_block_product_name").getAttribute("title");
//        String cartItemThreePrice = $("dt.last_item span.price").getText();

        // collecting Name and Price information from the cart's items
        String cartItemOneName = ph.getAttributeFromCssElement("dt.first_item a.cart_block_product_name", "title");
        String cartItemOnePrice = ph.getTextFromCssElement("dt.first_item span.price");
        String cartItemTwoName = ph.getAttributeFromCssElement("dt.item a.cart_block_product_name", "title");
        String cartItemTwoPrice = ph.getTextFromCssElement("dt.item span.price");
        String cartItemThreeName = ph.getAttributeFromCssElement("dt.last_item a.cart_block_product_name", "title");
        String cartItemThreePrice = ph.getTextFromCssElement("dt.last_item span.price");

//        String cartShippingCost = $(".cart_block_shipping_cost").getText();
//        String cartTotalCost = $(".cart_block_total").getText();

        //collecting cost information
        String cartShippingCost = ph.getTextFromCssElement(".cart_block_shipping_cost");
        String cartTotalCost = ph.getTextFromCssElement(".cart_block_total");

        //hover an element
//        $(".shopping_cart > a:nth-child(1)").hover();
        ph.hoverOnCssElement(".shopping_cart > a:nth-child(1)");

        //collect a cart (three items' names and prices) information
        cartList.add(cartItemOneName);
        cartList.add(cartItemOnePrice);
        cartList.add(cartItemTwoName);
        cartList.add(cartItemTwoPrice);
        cartList.add(cartItemThreeName);
        cartList.add(cartItemThreePrice);

        //collect the cost information from the cart
        cartListTotal.add(cartShippingCost);
        cartListTotal.add(cartTotalCost);

        return page(MainPage.class);

    }

    public ArrayList<String> getCartList() {

        return cartList;
    }

    public ArrayList<String> getCartListTotal() {

        return cartListTotal;
    }


    public MainPage checkItemAndCartLists() {

        assertEquals(getItemList(), getCartList());
        return page(MainPage.class);
    }


    public CartPage orderProcessing() {

//        $(".shopping_cart > a:nth-child(1)").hover();
//        $("#button_order_cart > span:nth-child(1)").click();
        ph.hoverOnCssElement(".shopping_cart > a:nth-child(1)");
        ph.clickOnCssElement("#button_order_cart > span:nth-child(1)");

        return page(CartPage.class);

    }


    public MainPage logOut() {

//        $(".logout").click();
        ph.clickOnCssElement(".logout");

        return page(MainPage.class);

    }

    public SelenideElement isLogOutCorrect() {

        return $(".logout").shouldNot(Condition.exist);

    }

}
