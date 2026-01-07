package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class InventoryPage {
    private final SelenideElement pageTitle = $(".title");
    private final ElementsCollection inventoryItems = $$(".inventory_item");
    private final SelenideElement cartLink = $(".shopping_cart_link");
    private final SelenideElement cartBadge = $(".shopping_cart_badge"); // El numerito rojo

    // Filtros
    private final SelenideElement sortDropdown = $(".product_sort_container");
    private final ElementsCollection itemPrices = $$(".inventory_item_price");

    // Menú Lateral (Logout)
    private final SelenideElement burgerMenuBtn = $("#react-burger-menu-btn");
    private final SelenideElement logoutLink = $("#logout_sidebar_link");

    public void checkTitleIsVisible() {
        pageTitle.shouldBe(visible).shouldHave(text("Products"));
    }

    public void addToCart(String productName) {
        // Busca el botón dentro del item específico
        inventoryItems.find(text(productName)).$(".btn_inventory").click();
    }

    public void removeFromCart(String productName) {
        // El botón cambia de nombre, pero la lógica es clic en el mismo lugar
        inventoryItems.find(text(productName)).$(".btn_inventory").click();
    }

    public void goToCart() {
        cartLink.click();
    }

    // NUEVO: Valida cuántos items dice el carrito que tiene
    public void checkCartBadge(String count) {
        cartBadge.shouldHave(text(count));
    }

    // NUEVO: Valida que el carrito esté vacío (sin numerito rojo)
    public void checkCartBadgeHidden() {
        cartBadge.shouldNotBe(visible);
    }

    public void selectSortOption(String value) {
        sortDropdown.selectOptionByValue(value);
    }

    public List<Double> getItemPrices() {
        return itemPrices.texts().stream()
                .map(text -> text.replace("$", ""))
                .map(Double::parseDouble)
                .collect(Collectors.toList());
    }

    // NUEVO: Lógica de Logout
    public void logout() {
        burgerMenuBtn.click();
        logoutLink.shouldBe(interactable).click();    }
}