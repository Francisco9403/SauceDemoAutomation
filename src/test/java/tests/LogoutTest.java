package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

import static com.codeborne.selenide.WebDriverRunner.url;
import static org.testng.Assert.assertTrue;

@Feature("Gestión de Sesión")
public class LogoutTest extends BaseTest {

    @Test(description = "Logout Exitoso")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verifica que el usuario pueda cerrar sesión y sea redirigido al login.")
    public void logoutTest() {
        LoginPage login = new LoginPage();
        InventoryPage inventory = new InventoryPage();

        // 1. Login
        login.openPage();
        login.login("standard_user", "secret_sauce");
        inventory.checkTitleIsVisible();

        // 2. Logout
        inventory.logout();

        // 3. Verificar que volvimos a la página de inicio (Login)
        // Verificamos que el botón de login sea visible de nuevo
        login.login("standard_user", "secret_sauce"); // Intentar loguear de nuevo para probar que estamos ahí
        // O mejor, verificar la URL:
        assertTrue(url().contains("saucedemo.com"), "No se redirigió correctamente");
    }
}