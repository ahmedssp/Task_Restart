package Pages;

import Handler.synchronization_methods;
import Listeners.testNg_listeners_simple;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.log4testng.Logger;

import static Handler.synchronization_methods.*;
import static Listeners.testNg_listeners_simple.PassMessege;
import static Listeners.testNg_listeners_simple.failledMessege;

public class A1_AddToCartPage {
    private WebDriver d;
    private static final Logger LOGGER = Logger.getLogger(testNg_listeners_simple.class);
    private By Click_logout_filed = By.id("logoutButton");
    ;

    public A1_AddToCartPage(WebDriver d) {
        this.d = d;
    }


    private By Add_Item_to_chart_filed =By.xpath("//button[@class=\"button-with-icon default-bg fullWidth\"]");
    private By CART_BUTTON_checkout_filed = By.id("HEADER_CART_BRIEF_GO_TO_CART_BUTTON");
    private By click_CART_icon_filed = By.id("HEADER_CART_BRIEF_ICON");
    public void Add_Item_to_chart(){
        waitForElement(d,click_CART_icon_filed);
        waitFor_Element_toBe_clickable(d,click_CART_icon_filed);
        d.findElement(Add_Item_to_chart_filed).click();
       waitForElementTobeEqual(d,"1",click_CART_icon_filed);
    }

    public A2_CheckoutPage ClickCheckout() {

        waitForElement(d,click_CART_icon_filed);
        waitFor_Element_toBe_clickable(d,click_CART_icon_filed);
        d.findElement(click_CART_icon_filed).click();
        waitForElement(d,CART_BUTTON_checkout_filed);
        waitFor_Element_toBe_clickable(d,CART_BUTTON_checkout_filed);
        d.findElement(CART_BUTTON_checkout_filed).click();

        return new A2_CheckoutPage(d);
    }
}
