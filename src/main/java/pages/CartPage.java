package pages;

import com.codeborne.selenide.ElementsCollection; // Importar esto
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class CartPage {
    private final SelenideElement checkoutButton = $("#checkout");

    // CAMBIO 1: Usamos $$ para obtener la COLECCIÓN (lista) de items, no solo uno
    private final ElementsCollection cartItems = $$(".cart_item");

    // CAMBIO 2: Buscamos dentro de la lista el que tenga el texto exacto
    public void checkItemInCart(String productName) {
        cartItems.find(text(productName)).shouldBe(visible);
    }

    public void proceedToCheckout() {
        checkoutButton.click();
    }
}