package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

@Feature("Login Tests")
public class LoginTest extends BaseTest {

    @Test(description = "Validar Login Exitoso")
    @Owner("Francisco Martinez")
    @Description("Verifica que un usuario estándar pueda ingresar al inventario.")
    public void successfulLoginTest() {
        LoginPage loginPage = new LoginPage();
        InventoryPage inventoryPage = new InventoryPage();

        loginPage.openPage();
        loginPage.login("standard_user", "secret_sauce");

        inventoryPage.checkTitleIsVisible();
    }

    @Test(description = "Validar Login Fallido (Usuario Bloqueado)")
    @Description("Verifica que aparezca el mensaje de error correcto.")
    public void lockedUserTest() {
        LoginPage loginPage = new LoginPage();

        loginPage.openPage();
        loginPage.login("locked_out_user", "secret_sauce");

        loginPage.checkErrorMessage("Epic sadface: Sorry, this user has been locked out.");
    }
}