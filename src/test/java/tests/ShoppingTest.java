package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import pages.*;

@Feature("Módulo de Compras")
public class ShoppingTest extends BaseTest {

    @Test(description = "E2E: Compra exitosa de un producto")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Flujo completo: Login -> Agregar Mochila -> Checkout -> Finalizar.")
    public void completePurchaseTest() {
        // Inicializamos las páginas que vamos a usar
        LoginPage login = new LoginPage();
        InventoryPage inventory = new InventoryPage();
        CartPage cart = new CartPage();
        CheckoutPage checkout = new CheckoutPage();

        // 1. Login
        login.openPage();
        login.login("standard_user", "secret_sauce");

        // 2. Catálogo: Agregar mochila
        inventory.checkTitleIsVisible();
        inventory.addToCart("Sauce Labs Backpack");
        inventory.goToCart();

        // 3. Carrito: Verificar y proceder
        cart.checkItemInCart("Sauce Labs Backpack");
        cart.proceedToCheckout();

        // 4. Checkout: Llenar datos (Pon tus datos si quieres)
        checkout.fillShippingInfo("Francisco", "Martinez", "6000");
        checkout.finishOrder();

        // 5. Validación final
        checkout.checkOrderComplete();
    }
}