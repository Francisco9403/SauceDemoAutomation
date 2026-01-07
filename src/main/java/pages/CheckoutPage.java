package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class CheckoutPage {
    private final SelenideElement firstNameInput = $("#first-name");
    private final SelenideElement lastNameInput = $("#last-name");
    private final SelenideElement zipInput = $("#postal-code");
    private final SelenideElement continueButton = $("#continue");
    private final SelenideElement finishButton = $("#finish");
    private final SelenideElement completeHeader = $(".complete-header");
    private final SelenideElement errorMessage = $("h3[data-test='error']");

    public void fillShippingInfo(String first, String last, String zip) {
        firstNameInput.setValue(first);
        lastNameInput.setValue(last);
        zipInput.setValue(zip);
        continueButton.click();
    }

    public void finishOrder() {
        finishButton.click();
    }

    // Valida el mensaje de "Gracias por su compra"
    public void checkOrderComplete() {
        completeHeader.shouldBe(visible).shouldHave(text("Thank you for your order!"));
    }

    public void checkErrorMessage(String expectedText) {
        errorMessage.shouldBe(visible).shouldHave(text(expectedText));
    }
}