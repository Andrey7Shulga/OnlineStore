package sel.automation.practice.pages.components;

import static com.codeborne.selenide.Selenide.*;

public class PageHelper {

    public void clickOnCssElement (String element) {

        $("" + element + "").click();

    }

    public void clickOnXpathElement (String element) {

        $x("" + element + "").click();

    }

    public void setValueToXpathElement (String element, String value) {

        $x("" + element + "").clear();
        $x("" + element + "").setValue(value);

    }

    public void setValueToCssElement (String element, String value) {

        $("" + element + "").clear();
        $("" + element + "").setValue(value);

    }

    public void hoverOnCssElement (String element) {

        $("" + element + "").hover();

    }

    public void hoverOnXpathElement (String element) {

        $x("" + element + "").hover();

    }

    public String getTextFromCssElement (String element) {

        return $("" + element + "").getText();

    }

    public String getTextFromXpathElement (String element) {

        return $x("" + element + "").getText();

    }

    public String getAttributeFromCssElement (String element, String attribute) {

        return $("" + element + "").getAttribute(attribute);

    }

    public String getAttributeFromXpathElement (String element, String attribute) {

        return $x("" + element + "").getAttribute(attribute);
    }

    public int getSizeFromCss (String element) {

        return $$("" + element + "").size();

    }

    public int getSizeFromXpath (String element) {

        return $$x("" + element + "").size();

    }


}
