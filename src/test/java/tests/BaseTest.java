package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTest {

    @BeforeClass
    public void setup() {
        // 1. Configuraciones generales
        Configuration.browser = "chrome";
        Configuration.timeout = 10000; // 10 segundos de espera

        // 2. DETECCIÓN INTELIGENTE DE ENTORNO (CI vs LOCAL)
        // Verificamos si Maven nos pasó la propiedad "-Dselenide.headless=true"
        // o si el sistema operativo parece ser un servidor Linux de CI.
        boolean isHeadless = Boolean.parseBoolean(System.getProperty("selenide.headless"))
                || System.getProperty("os.name").toLowerCase().contains("linux");

        ChromeOptions options = new ChromeOptions();

        // 3. Opciones Anti-Popups (Tus trucos)
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-password-manager-reauthentication");
        options.addArguments("--disable-save-password-bubble");

        if (isHeadless) {
            // === CONFIGURACIÓN PARA GITHUB ACTIONS / DOCKER ===
            Configuration.headless = true;
            System.out.println("🤖 MODO CI DETECTADO: Ejecutando Headless (Sin pantalla)");

            // Argumentos CRÍTICOS para que Chrome no crashee en Linux/Docker
            options.addArguments("--no-sandbox"); // Obligatorio para root/docker
            options.addArguments("--disable-dev-shm-usage"); // Evita problemas de memoria compartida
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080"); // Forzamos tamaño aunque no haya pantalla
        } else {
            // === CONFIGURACIÓN PARA TU PC (LOCAL) ===
            Configuration.headless = false;
            Configuration.browserSize = "1920x1080";
            System.out.println("🖥️ MODO LOCAL: Ejecutando con interfaz gráfica");
        }

        // Pasamos las opciones combinadas a Selenide
        Configuration.browserCapabilities = options;

        // 4. Integración con Allure
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false));
    }

    @AfterMethod
    public void tearDown() {
        // Cerramos el navegador para garantizar limpieza total entre tests
        closeWebDriver();
    }
}