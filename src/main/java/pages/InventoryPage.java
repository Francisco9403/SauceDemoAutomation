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

    // Elementos nuevos para el Sort (Ordenamiento)
    private final SelenideElement sortDropdown = $(".product_sort_container");
    private final ElementsCollection itemPrices = $$(".inventory_item_price");

    public void checkTitleIsVisible() {
        pageTitle.shouldBe(visible).shouldHave(text("Products"));
    }

    public void addToCart(String productName) {
        inventoryItems.find(text(productName)).$(".btn_inventory").click();
    }

    public void goToCart() {
        cartLink.click();
    }

    // NUEVO: Selecciona una opción del filtro (ej: "lohi" para low to high)
    public void selectSortOption(String value) {
        sortDropdown.selectOptionByValue(value);
    }

// ... imports ...

    // REEMPLAZA ESTE MÉTODO COMPLETO
    public List<Double> getItemPrices() {
        // 1. Obtenemos la lista de Textos (List<String>) directamente de Selenide
        return itemPrices.texts().stream()
                // 2. Procesamos esa lista de textos estándar
                .map(text -> text.replace("$", "")) // Quita el signo $
                .map(Double::parseDouble)           // Convierte texto a número
                .collect(Collectors.toList());
    }

}