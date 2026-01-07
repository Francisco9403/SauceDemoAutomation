package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

@Feature("Catálogo de Productos")
public class ProductsTest extends BaseTest {

    @Test(description = "Validar Ordenamiento por Precio (Low to High)")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verifica que al seleccionar 'Price (low to high)', los productos se ordenen matemáticamente correcto.")
    public void validatePriceSorting() {
        LoginPage login = new LoginPage();
        InventoryPage inventory = new InventoryPage();

        // 1. Login
        login.openPage();
        login.login("standard_user", "secret_sauce");

        // 2. Seleccionar filtro "Menor a Mayor" (lohi)
        inventory.selectSortOption("lohi");

        // 3. Obtener los precios reales de la web
        List<Double> currentPrices = inventory.getItemPrices();

        // 4. Crear una copia y ordenarla nosotros con Java para comparar
        List<Double> expectedPrices = new ArrayList<>(currentPrices);
        Collections.sort(expectedPrices); // Java ordena la lista de menor a mayor

        // 5. Verificar que la lista de la web sea IGUAL a la lista ordenada por Java
        Assert.assertEquals(currentPrices, expectedPrices, "¡El ordenamiento de precios falló!");
    }
}