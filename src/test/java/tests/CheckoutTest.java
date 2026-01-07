package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import pages.*;

@Feature("Validaciones de Checkout")
public class CheckoutTest extends BaseTest {

    @Test(description = "Error al comprar sin Código Postal")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Valida que el sistema bloquee la compra si falta el Zip Code.")
    public void checkoutMissingZipCodeTest() {
        LoginPage login = new LoginPage();
        InventoryPage inventory = new InventoryPage();
        CartPage cart = new CartPage();
        CheckoutPage checkout = new CheckoutPage();

        // Precondición: Estar en el checkout
        login.openPage();
        login.login("standard_user", "secret_sauce");
        inventory.addToCart("Sauce Labs Bike Light");
        inventory.goToCart();
        cart.proceedToCheckout();

        // Acción: Llenar to do MENOS el código postal
        checkout.fillShippingInfo("Francisco", "Tester", "");

        // Validación
        checkout.checkErrorMessage("Error: Postal Code is required");
    }
}