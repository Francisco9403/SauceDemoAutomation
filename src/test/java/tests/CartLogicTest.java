package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

@Feature("Lógica del Carrito")
public class CartLogicTest extends BaseTest {

    @Test(description = "Añadir y Quitar Productos (Badge Count)")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verifica que el contador rojo del carrito aumente y disminuya correctamente.")
    public void addRemoveItemsTest() {
        LoginPage login = new LoginPage();
        InventoryPage inventory = new InventoryPage();

        login.openPage();
        login.login("standard_user", "secret_sauce");

        // 1. Validar carrito vacío al inicio
        inventory.checkCartBadgeHidden();

        // 2. Agregar 1 producto -> Contador debe ser '1'
        inventory.addToCart("Sauce Labs Backpack");
        inventory.checkCartBadge("1");

        // 3. Agregar otro producto -> Contador debe ser '2'
        inventory.addToCart("Sauce Labs Bike Light");
        inventory.checkCartBadge("2");

        // 4. Quitar el primer producto -> Contador debe bajar a '1'
        inventory.removeFromCart("Sauce Labs Backpack");
        inventory.checkCartBadge("1");

        // 5. Quitar el último -> Carrito vacío de nuevo
        inventory.removeFromCart("Sauce Labs Bike Light");
        inventory.checkCartBadgeHidden();
    }
}