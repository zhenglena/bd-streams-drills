
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Test;
import utilities.Dish;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utilities.Dish.Type.*;

public class CollectingDrillsTest {
    private static final Dish SUSHI = Dish.builder()
        .withCalories(400)
        .withDishType(FISH)
        .withIsVegetarian(false)
        .withName("Sushi Plate")
        .build();

    private static final Dish STEAK = Dish.builder()
        .withCalories(900)
        .withDishType(MEAT)
        .withIsVegetarian(false)
        .withName("Steak And Potatoes")
        .build();

    private static final Dish CHICKEN = Dish.builder()
        .withCalories(1100)
        .withDishType(MEAT)
        .withIsVegetarian(false)
        .withName("Fried Chicken")
        .build();

    private static final Dish SALAD = Dish.builder()
        .withCalories(300)
        .withDishType(OTHER)
        .withIsVegetarian(true)
        .withName("Harvest Salad")
        .build();

    private static final List<Dish> ALL_DISHES = ImmutableList.of(SUSHI, STEAK, CHICKEN, SALAD);
    private static final Map<Dish.Type, List<Dish>> EXPECTED_DISH_TYPE_MAP =
        //              KEY,  VALUE
        ImmutableMap.of(FISH, ImmutableList.of(SUSHI),
                        MEAT, ImmutableList.of(STEAK, CHICKEN),
                        OTHER, ImmutableList.of(SALAD));

    @Test
    public void collectDishesByType_allTypes_mapsDishToType() {
        Map<Dish.Type, List<Dish>> dishTypeMap = CollectingDrills.collectDishesByType(ALL_DISHES);

        for (Dish.Type type : Dish.Type.values()) {
            assertTrue(dishTypeMap.containsKey(type), String.format("Expected collectDishesByType " +
                "to return a map with %s dish type as a key. Found: %s", type, dishTypeMap));
            List<Dish> dishesForType = dishTypeMap.get(type);

            for (Dish expectedDish : EXPECTED_DISH_TYPE_MAP.get(type)) {
                assertTrue(dishesForType.contains(expectedDish), String.format("Expected collectDishesByType " +
                    "to map %s dish to the %s dish type key. Found: %s", expectedDish, type, dishTypeMap));
            }
        }
    }

    @Test
    public void numberOfDishesByType_allTypes_mapsNumberOfDishToType() {
        Map<Dish.Type, Long> dishTypeMap = CollectingDrills.numberOfDishesByType(ALL_DISHES);

        for (Dish.Type type : Dish.Type.values()) {
            assertTrue(dishTypeMap.containsKey(type), String.format("Expected numberOfDishesByType " +
                "to return a map with %s dish type as a key. Found: %s", type, dishTypeMap));
            Long count = dishTypeMap.get(type);

            int expectedDishesCount = EXPECTED_DISH_TYPE_MAP.get(type).size();
            assertEquals(expectedDishesCount, count, String.format("Expected numberOfDishesByType " +
                    "to count %s dishes for the %s dish type key. Found: %s", expectedDishesCount, type, dishTypeMap));
        }
    }

    @Test
    public void vegetarianDishes_allTypes_mapsVegetarianDishes() {
        Map<Boolean, List<Dish>> vegetarianDishMap = CollectingDrills.vegetarianDishes(ALL_DISHES);

        List<Dish> vegetarianDishes = vegetarianDishMap.get(true);
        List<Dish> nonVegetarianDishes = vegetarianDishMap.get(false);

        assertEquals(vegetarianDishes.size(), 1, String.format("Expected a single vegetarian dish " +
            "to be mapped for the input %s", ALL_DISHES));
        assertTrue(vegetarianDishes.contains(SALAD), String.format("Expected vegetarianDishes " +
            "to map %s as a vegetarian dish.", SALAD));

        assertTrue(nonVegetarianDishes.contains(SUSHI), String.format("Expected vegetarianDishes " +
            "to map %s as a non-vegetarian dish.", SUSHI));
        assertTrue(nonVegetarianDishes.contains(STEAK), String.format("Expected vegetarianDishes " +
            "to map %s as a non-vegetarian dish.", STEAK));
        assertTrue(nonVegetarianDishes.contains(CHICKEN), String.format("Expected vegetarianDishes " +
            "to map %s as a non-vegetarian dish.", CHICKEN));
    }

    @Test
    public void averageCalories_mixedItems_returnsAverage() {
        // GIVEN
        double totalCalories = 0;
        for (Dish dish : ALL_DISHES) {
            totalCalories += dish.getCalories();
        }
        Double expectedAverageCalories = totalCalories / ALL_DISHES.size();

        // WHEN
        Double result = CollectingDrills.getAverageCalories(ALL_DISHES);

        // THEN
        assertEquals(expectedAverageCalories, result,
            String.format("Expected to the get an average of %s calories. Got %s instead.",
                expectedAverageCalories, result));

    }
}
