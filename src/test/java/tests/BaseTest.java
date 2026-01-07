package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.chrome.ChromeOptions; // Importación nueva
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTest {

    @BeforeClass
    public void setup() {
        // 1. Configuraciones básicas de Selenide
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
        Configuration.timeout = 10000; // Damos un poco más de tiempo (10s)

        // 2. TRUCO: Configuraciones anti-popups de Chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");  // Modo incógnito (evita caché y cookies viejas)
        options.addArguments("--disable-notifications"); // Bloquea notificaciones
        options.addArguments("--disable-password-manager-reauthentication");
        options.addArguments("--disable-save-password-bubble"); // Bloquea "Guardar contraseña"

        // Pasamos estas opciones a Selenide
        Configuration.browserCapabilities = options;

        // 3. Integración con Allure
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false));
    }

    // ESTO ES LO NUEVO:
    // En lugar de limpiar cookies al principio, cerramos el navegador al final.
    // Esto garantiza que el siguiente test empiece desde cero absoluto.
    @AfterMethod
    public void tearDown() {
        closeWebDriver();
    }
}