package sel.automation.practice.components;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.*;

public class PageHelper {

    private ArrayList<String> arraylist = new ArrayList<>();

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

    public void addTwoToArray (ArrayList<String> al, String a, String b) {

        this.arraylist = al;
        al.add(a);
        al.add(b);

    }

    public void addSixToArray (ArrayList<String> al, String a, String b, String c, String d, String e, String f) {

        this.arraylist = al;
        al.add(a);
        al.add(b);
        al.add(c);
        al.add(d);
        al.add(e);
        al.add(f);

    }

    public void arrayCollecting (ArrayList<String> a1, String xpathEndPoint, String titleXpath, String priceXpath) {

        this.arraylist = a1;
        int size = $$x("" + xpathEndPoint + "" + titleXpath).size();

        for(int i = 1; i <= size; i++){
            a1.add($x("" + xpathEndPoint + "[" + i + "]" + titleXpath).getText());
            a1.add($x("" + xpathEndPoint + "[" + i + "]" + priceXpath).getText());

        }

    }

    public void arrayCollectingWithAttribute (ArrayList<String> a1, String xpathEndPoint, String titleXpath,
                                              String priceXpath, String attr) {

        this.arraylist = a1;
        int size = $$x("" + xpathEndPoint + "" + titleXpath).size();

        for(int i = 1; i <= size; i++){
            a1.add($x("" + xpathEndPoint + "[" + i + "]" + titleXpath).getAttribute(attr));
            a1.add($x("" + xpathEndPoint + "[" + i + "]" + priceXpath).getText());

        }

    }

}
