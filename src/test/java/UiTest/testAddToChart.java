package UiTest;

import BasePackage.setup_cross;
import BasePackage.setup_simple;
import Pages.A1_AddToCartPage;
import Pages.A2_CheckoutPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
public class testAddToChart extends setup_simple {
    @Test
    public void Add_site_Happy_Senario2() {
        SoftAssert softAssert = new SoftAssert();

        Login_P_Obj.GoToOutDoor();
        String PriceOfSecondItem= Login_P_Obj.get_price();
        A1_AddToCartPage AddToCartPage_Obj = Login_P_Obj.SelectSecondItem();
        AddToCartPage_Obj.Add_Item_to_chart();
        A2_CheckoutPage CheckoutPage_Obj= AddToCartPage_Obj.ClickCheckout();
        softAssert.assertTrue(CheckoutPage_Obj.IstotalPriceEqualToTheItemPrice(PriceOfSecondItem));
        softAssert.assertAll();
    }

}
