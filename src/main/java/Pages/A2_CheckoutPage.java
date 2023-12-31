package Pages;

import Listeners.testNg_listeners_simple;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.log4testng.Logger;

import static Handler.synchronization_methods.waitForElement;
import static Handler.synchronization_methods.waitFor_Element_toBe_clickable;

public class A2_CheckoutPage {
    private WebDriver d;
    private static final Logger LOGGER = Logger.getLogger(testNg_listeners_simple.class);

    public A2_CheckoutPage(WebDriver d) {
        this.d = d;
    }

    private By total_price_filed = By.xpath("//div[@class=\"summary__total\"]//b");

    public boolean IstotalPriceEqualToTheItemPrice(String price) {
        waitForElement(d, total_price_filed);
        waitFor_Element_toBe_clickable(d, total_price_filed);
        return d.findElement(total_price_filed).getText().replace("EGP", "").replace(" ", "").equals(price);
    }
}
