package Pages;

import Listeners.testNg_listeners_simple;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.log4testng.Logger;

import static Handler.alert_handling.allertH1;
import static Handler.synchronization_methods.*;
import static Listeners.testNg_listeners_simple.PassMessege;
import static Listeners.testNg_listeners_simple.failledMessege;
import static Utility.PasswordComplexityChecker.isPasswordComplex;

public class A0_HomePage {
    private WebDriver d;
    private static final Logger LOGGER = Logger.getLogger(testNg_listeners_simple.class);

    public A0_HomePage(WebDriver d) {
        this.d = d;
    }
    private By second_item_price =By.xpath("(//div[@class=\"productPrices__specialPrice\"])[2]/p");
    private By Outdoors_filed = By.xpath("//div[@class=\"siteMenu__item\"]//*[text()=\"Outdoors\"]");
    private By SecondItem_filed = By.xpath("(//div[@class=\"listing__card\"]/div)[2]//img");

    public void GoToOutDoor(){
        waitForElement(d,Outdoors_filed);
        d.findElement(Outdoors_filed).click();
        d.navigate().refresh();
    }
    public A1_AddToCartPage SelectSecondItem(){

        WebElement element = d.findElement(SecondItem_filed);
        waitForElement(d,SecondItem_filed);
        waitFor_Element_toBe_clickable(d,SecondItem_filed);
        JavascriptExecutor executor = (JavascriptExecutor)d;
        executor.executeScript("arguments[0].click();", element);
//        try {
//            Thread.sleep(4000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        return new A1_AddToCartPage(d);

    }

    public String get_price(){
        // Specify the character after which  want to remove parts
        char specificChar = '.';
        String originalString =d.findElement(second_item_price).getText().replace(" ","");

        // Find the index of the specific character
        int indexOfChar = originalString.indexOf(specificChar);

        if (indexOfChar != -1) {
            // Remove parts of the string after the specific character
            String modifiedString = originalString.substring(0, indexOfChar);

            System.out.println("Original String: " + originalString);
            System.out.println("Modified String: " + modifiedString);
            return modifiedString.replace(",","");
        } else {
            return "Specific character not found in the string.";
        }
    }
}
