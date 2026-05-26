import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ParamBurgerReceiptTest {
    private String bunName;
    private float bunPrice;
    private Ingredient[] ingredients;
    private String expectedReceipt;

    public ParamBurgerReceiptTest(String bunName, float bunPrice, Ingredient[] ingredients, String expectedReceipt) {
        this.bunName = bunName;
        this.bunPrice = bunPrice;
        this.ingredients = ingredients;
        this.expectedReceipt = expectedReceipt;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getTestData() {
        return Arrays.asList(new Object[][]{
                // бургер с ингредиентами
                {"white bun", 50.0f,
                        new Ingredient[]{
                                new Ingredient(IngredientType.FILLING, "cutlet", 100.0f),
                                new Ingredient(IngredientType.SAUCE, "hot sauce", 30.0f)
                        }, "(==== white bun ====)\r\n" +
                        "= filling cutlet =\r\n" +
                        "= sauce hot sauce =\r\n" +
                        "(==== white bun ====)\r\n" +
                        "\r\nPrice: 230,000000\r\n"},
                // пустой бургер
                {"white bun", 40.0f,
                        new Ingredient[0],
                        "(==== white bun ====)\r\n" +
                        "(==== white bun ====)\r\n" +
                        "\r\nPrice: 80,000000\r\n"}
        });
    }

    @Test
    public void shouldGenerateCorrectReceipt() {
        Bun bun = new Bun(bunName, bunPrice);
        Burger burger = new Burger();
        burger.setBuns(bun);

        for (Ingredient ingredient : ingredients) {
            burger.addIngredient(ingredient);
        }

        String actualReceipt = burger.getReceipt();
        assertEquals("Чек должен соответствовать ожидаемому формату", expectedReceipt, actualReceipt);
    }
}
