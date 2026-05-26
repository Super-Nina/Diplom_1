import org.junit.Test;
import praktikum.Burger;
import praktikum.Ingredient;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class TestsForBurgerClass extends BaseIUTest {
// тестирование метода добавления ингредиента
    @Test
    public void shouldAddIngredientToBurger() {
        Burger burger = new Burger();
        burger.setBuns(selectedBun);
        burger.addIngredient(selectedIngredient);
        assertEquals(1, burger.ingredients.size());
    }
// тестирование метода удаления ингредиента
    @Test
    public void shouldRemoveIngredientFromBurger() {
    Burger burger = new Burger();
    burger.setBuns(selectedBun);
        // добавляем несколько ингредиентов
        for (int i = 0; i < 3; i++) {
            burger.addIngredient(selectedIngredient);
        }
    burger.removeIngredient(0);
    assertEquals(2, burger.ingredients.size());
    }
// тестирование метода перемещения ингредиентов
    @Test
    public void shouldMoveIngredientInBurger() {
        Burger burger = new Burger();
        burger.setBuns(selectedBun);

        String firstIngredientName = selectedIngredient.getName();

        List<Ingredient> allIngredients = getAllIngredients();
        Random random = new Random();
        Ingredient secondIngredient;
        // повторяем, пока не получим второй ингредиент, отличающийся от первого
        do {
            int randomIndex = random.nextInt(allIngredients.size());
            secondIngredient = allIngredients.get(randomIndex);
        } while (secondIngredient.equals(selectedIngredient));
        String secondIngredientName = secondIngredient.getName();

        burger.addIngredient(selectedIngredient);
        burger.addIngredient(secondIngredient);
        burger.moveIngredient(1,0);

        List<String> expectedOrder = List.of(secondIngredientName, firstIngredientName);
        List<String> actualOrder = new ArrayList<>();
        for (Ingredient ingredient : burger.ingredients) {
            actualOrder.add(ingredient.getName());
        }
        assertEquals("Порядок ингредиентов после перемещения не соответствует ожидаемому",
                expectedOrder, actualOrder);
    }

    // тестирование метода получения цены
    @Test
    public void shouldCalculateCorrectlyPriceOfBurger(){
        Burger burger = new Burger();
        burger.setBuns(selectedBun);
        burger.addIngredient(selectedIngredient);
        float expectedBurgerPrice = selectedBun.getPrice()*2 + selectedIngredient.getPrice();
        float actualBurgerPrice = burger.getPrice();
        assertEquals(expectedBurgerPrice,actualBurgerPrice,0.001f);
    }
}
