package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class LoginPage {
    // Selectores
    private final SelenideElement usernameInput = $("#user-name");
    private final SelenideElement passwordInput = $("#password");
    private final SelenideElement loginButton = $("#login-button");
    private final SelenideElement errorMessage = $("h3[data-test='error']");

    // Acciones
    public LoginPage openPage() {
        open("https://www.saucedemo.com/");
        return this;
    }

    public void login(String username, String password) {
        usernameInput.setValue(username);
        passwordInput.setValue(password);
        loginButton.click();
    }

    // Validaciones
    public void checkErrorMessage(String expectedMessage) {
        errorMessage.shouldHave(text(expectedMessage));
    }
}