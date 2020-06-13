package sel.automation.practice.pages.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class MenuBlock extends ElementsContainer {

    private static final By LOGIN  = By.cssSelector(".login");
    private static final By WOMEN_MENU  = By.cssSelector(".sf-menu > li:nth-child(1) > a:nth-child(1)");
    private static final By DRESSES_MENU = By.cssSelector(".sf-menu > li:nth-child(2) > a:nth-child(1)");
    private static final By TSHIRTS_MENU = By.cssSelector(".sf-menu > li:nth-child(3) > a:nth-child(1)");


    public void blockToLogin() {

        $(LOGIN).click();
    }

    public void womenMenuBlock() {

        $(WOMEN_MENU).click();
    }

    public void dressesMenuBlock() {

        $(DRESSES_MENU).click();
    }

    public void tshirtsMenuBlock() {

        $(TSHIRTS_MENU).click();
    }


}
