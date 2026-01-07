package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

@Feature("Login con Múltiples Datos")
public class LoginDataTest extends BaseTest {

    // ESTO ES EL DATA PROVIDER: Una "fábrica" de datos
    @DataProvider(name = "usuarios")
    public Object[][] usuariosProvider() {
        return new Object[][] {
                { "standard_user", "secret_sauce", true },        // Usuario Feliz
                { "problem_user", "secret_sauce", true },         // Usuario con Bugs (pero loguea)
                { "locked_out_user", "secret_sauce", false },     // Usuario Bloqueado (no debería loguear)
                { "invalid_user", "wrong_pass", false }           // Usuario Inexistente
        };
    }

    @Test(description = "DDT: Verificar login con diferentes tipos de usuario", dataProvider = "usuarios")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Ejecuta el mismo test repetidas veces con distintas combinaciones de usuario/contraseña.")
    public void loginDataDrivenTest(String username, String password, boolean shouldLogin) {
        LoginPage login = new LoginPage();
        InventoryPage inventory = new InventoryPage();

        login.openPage();
        login.login(username, password);

        if (shouldLogin) {
            // Si esperamos entrar, verificamos el título
            inventory.checkTitleIsVisible();
        } else {
            // Si NO esperamos entrar, verificamos que siga apareciendo el botón de login o un error
            // (Aquí reutilizamos el método de error que ya tienes o validamos url)
            login.checkErrorMessage("Epic sadface"); // Nota: El mensaje puede variar, esto es un ejemplo genérico
        }
    }
}