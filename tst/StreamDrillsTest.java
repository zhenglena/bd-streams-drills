import utilities.Dish;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StreamDrillsTest {

    private static final String BLAND_MEAL = "Bland Meal";
    private static final String BETTER_MEAL = "Better Meal";
    private static final String BEST_MEAL = "Best Meal";
    private static final String THIS_IS_NOT_A_MEAL_ITS_A_SNACK = "Snack";
    private StreamDrills drills = new StreamDrills();

    @Test
    public void vegetarianDishes_emptyList() {
        assumeVegDishes(Collections.EMPTY_LIST, Collections.EMPTY_LIST);
    }

    @Test
    public void vegetarianDishes_oneNonVeg() {
        List<Dish> nonVeg = Collections.singletonList(Dish.builder().withIsVegetarian(false).build());
        assumeVegDishes(nonVeg, Collections.EMPTY_LIST);
    }

    @Test
    public void vegetarianDishes_oneVeg() {
        List<Dish> veg = Collections.singletonList(Dish.builder().withIsVegetarian(true).build());
        assumeVegDishes(veg, veg);
    }

    @Test
    public void vegetarianDishes_mixed() {
        List<Dish> mixed = Lists.newArrayList(Dish.builder().withIsVegetarian(true).build(),
            Dish.builder().withIsVegetarian(false).build());
        List<Dish> results = Lists.newArrayList(mixed);
        results.remove(1);
        assumeVegDishes(mixed, results);
    }

    @Test
    public void uniqueEvenNumbers_noEvens() {
        List<Integer> odds = Lists.newArrayList(1, 3, 5);
        List<Integer> results = Lists.newArrayList();
        assumeUniqueEvens(odds, results);
    }

    @Test
    public void uniqueEvenNumbers_allEvens() {
        List<Integer> odds = Lists.newArrayList(2, 2, 4, 4, 6, 8);
        List<Integer> results = Lists.newArrayList(2, 4, 6, 8);
        assumeUniqueEvens(odds, results);
    }

    @Test
    public void uniqueEvenNumbers_mixed() {
        List<Integer> odds = Lists.newArrayList(1, 3, 2, 2, 4, 4, 5, 8);
        List<Integer> results = Lists.newArrayList(2, 4, 8);
        assumeUniqueEvens(odds, results);
    }

    @Test
    public void uniqueEvenNumbers_empty() {
        assumeUniqueEvens(Collections.EMPTY_LIST, Collections.EMPTY_LIST);
    }

    @Test
    public void threeHighCaloricDishNames_empty() {
        assumeThreeHighCaloricDishNames(Collections.EMPTY_LIST, Collections.EMPTY_LIST);
    }

    @Test
    public void threeHighCaloricDishNames_oneOver() {
        List<Dish> dishes = Collections.singletonList(Dish.builder().withName(BLAND_MEAL).withCalories(500).build());
        assumeThreeHighCaloricDishNames(dishes, Collections.singletonList(BLAND_MEAL));
    }

    @Test
    public void threeHighCaloricDishNames_twoOver() {
        List<Dish> dishes = Lists.newArrayList(Dish.builder().withName(BLAND_MEAL).withCalories(500).build(),
            Dish.builder().withName(BETTER_MEAL).withCalories(700).build());
        assumeThreeHighCaloricDishNames(dishes, Lists.newArrayList(BLAND_MEAL, BETTER_MEAL));
    }

    @Test
    public void threeHighCaloricDishNames_threeOver() {
        List<Dish> dishes = Lists.newArrayList(Dish.builder().withName(BLAND_MEAL).withCalories(500).build(),
            Dish.builder().withName(BETTER_MEAL).withCalories(700).build(),
            Dish.builder().withName(BEST_MEAL).withCalories(1200).build());
        assumeThreeHighCaloricDishNames(dishes, Lists.newArrayList(BLAND_MEAL, BETTER_MEAL, BEST_MEAL));
    }

    @Test
    public void threeHighCaloricDishNames_fourDishes_threeOver() {
        List<Dish> dishes = Lists.newArrayList(Dish.builder()
                .withName(THIS_IS_NOT_A_MEAL_ITS_A_SNACK)
                .withCalories(250)
                .build(),
            Dish.builder().withName(BLAND_MEAL).withCalories(500).build(),
            Dish.builder().withName(BETTER_MEAL).withCalories(700).build(),
            Dish.builder().withName(BEST_MEAL).withCalories(1200).build());
        assumeThreeHighCaloricDishNames(dishes, Lists.newArrayList(BLAND_MEAL, BETTER_MEAL, BEST_MEAL));
    }

    @Test
    public void threeHighCaloricDishNames_fourOver() {
        List<Dish> dishes = Lists.newArrayList(Dish.builder()
                .withName(THIS_IS_NOT_A_MEAL_ITS_A_SNACK)
                .withCalories(300)
                .build(),
            Dish.builder().withName(BLAND_MEAL).withCalories(500).build(),
            Dish.builder().withName(BETTER_MEAL).withCalories(700).build(),
            Dish.builder().withName(BEST_MEAL).withCalories(1200).build());
        assumeThreeHighCaloricDishNamesSize(dishes, Lists.newArrayList(BLAND_MEAL, BETTER_MEAL, BEST_MEAL));
    }

    @Test
    public void howManyDishes_empty() {
        assumeHowManyDishes(Collections.EMPTY_LIST, 0);
    }

    @Test
    public void howManyDishes_one() {
        List<Dish> dishes = Collections.singletonList(Dish.builder().withName(BLAND_MEAL).withCalories(500).build());
        assumeHowManyDishes(dishes, 1);
    }

    @Test
    public void howManyDishes_two() {
        List<Dish> dishes = Lists.newArrayList(Dish.builder().withName(BLAND_MEAL).withCalories(500).build(),
            Dish.builder().withName(BETTER_MEAL).withCalories(700).build());
        assumeHowManyDishes(dishes, 2);
    }

    @Test
    public void howManyDishes_three() {
        List<Dish> dishes = Lists.newArrayList(Dish.builder().withName(BLAND_MEAL).withCalories(500).build(),
            Dish.builder().withName(BETTER_MEAL).withCalories(700).build(),
            Dish.builder().withName(BEST_MEAL).withCalories(1200).build());
        assumeHowManyDishes(dishes, 3);
    }

    @Test
    public void lengthOfDishNames_empty() {
        assumeLengthOfDishNames(Collections.EMPTY_LIST, Collections.EMPTY_LIST);
    }

    @Test
    public void lengthOfDishNames_one() {
        List<Dish> dishes = Collections.singletonList(Dish.builder().withName(BLAND_MEAL).build());
        assumeLengthOfDishNames(dishes, Lists.newArrayList(10));
    }

    @Test
    public void lengthOfDishNames_two() {
        List<Dish> dishes = Lists.newArrayList(Dish.builder().withName(BLAND_MEAL).build(),
            Dish.builder().withName(BETTER_MEAL).build());
        assumeLengthOfDishNames(dishes, Lists.newArrayList(10, 11));
    }

    @Test
    public void lengthOfDishNames_three() {
        List<Dish> dishes = Lists.newArrayList(Dish.builder().withName(BLAND_MEAL).build(),
            Dish.builder().withName(BETTER_MEAL).build(),
            Dish.builder().withName(BEST_MEAL).build());
        assumeLengthOfDishNames(dishes, Lists.newArrayList(10, 11, 9));
    }

    @Test
    public void isEverythingUnder100Calories_true() {
        List<Dish> dishes = Lists.newArrayList(Dish.builder().withName(BLAND_MEAL).withCalories(500).build(),
            Dish.builder().withName(BETTER_MEAL).withCalories(700).build(),
            Dish.builder().withName(BEST_MEAL).withCalories(900).build());
        assumeIsEverythingUnder1000Calories(dishes, true);
    }

    @Test
    public void isEverythingUnder100Calories_false() {
        List<Dish> dishes = Lists.newArrayList(Dish.builder().withName(BLAND_MEAL).withCalories(500).build(),
            Dish.builder().withName(BETTER_MEAL).withCalories(700).build(),
            Dish.builder().withName(BEST_MEAL).withCalories(1200).build());
        assumeIsEverythingUnder1000Calories(dishes, false);
    }

    @Test
    public void isNothingOver1000Calories_true() {
        List<Dish> dishes = Lists.newArrayList(Dish.builder().withName(BLAND_MEAL).withCalories(500).build(),
            Dish.builder().withName(BETTER_MEAL).withCalories(700).build(),
            Dish.builder().withName(BEST_MEAL).withCalories(1000).build());
        assumeIsNothingOver1000Calories(dishes, true);
    }

    @Test
    public void isNothingOver1000Calories_false() {
        List<Dish> dishes = Lists.newArrayList(Dish.builder().withName(BLAND_MEAL).withCalories(500).build(),
            Dish.builder().withName(BETTER_MEAL).withCalories(700).build(),
            Dish.builder().withName(BEST_MEAL).withCalories(1200).build());
        assumeIsNothingOver1000Calories(dishes, false);
    }

    @Test
    public void isMenuVegetarianFriendly_true_onlyItem() {
        List<Dish> mixed = Lists.newArrayList(Dish.builder().withIsVegetarian(true).build());
        assumeIsMenuVegetarianFriendly(mixed, true);
    }

    @Test
    public void isMenuVegetarianFriendly_true() {
        List<Dish> mixed = Lists.newArrayList(Dish.builder().withIsVegetarian(true).build(),
            Dish.builder().withIsVegetarian(false).build());
        assumeIsMenuVegetarianFriendly(mixed, true);
    }

    @Test
    public void isMenuVegetarianFriendly_false() {
        List<Dish> mixed = Lists.newArrayList(Dish.builder().withIsVegetarian(false).build(),
            Dish.builder().withIsVegetarian(false).build());
        assumeIsMenuVegetarianFriendly(mixed, false);
    }

    @Test
    public void vegetarianDish_noVegDishes() {
        List<Dish> mixed = Lists.newArrayList(Dish.builder().withIsVegetarian(false).build(),
            Dish.builder().withIsVegetarian(false).build());
        assumeVegetarianDish(mixed, Optional.empty());
    }

    @Test
    public void vegetarianDish_vegDishes() {
        Dish vegDish = Dish.builder().withIsVegetarian(true).build();
        List<Dish> mixed = Lists.newArrayList(vegDish);
        assumeVegetarianDish(mixed, Optional.of(vegDish));
    }

    @Test
    public void listCountriesOfOrigin_emptyList() {
        List<Dish> dishes = Arrays.asList();
        Set<String> countries = StreamDrills.listCountriesOfOrigin(dishes);
        assertTrue(countries.isEmpty());
    }

    @Test
    public void listCountriesOfOrigin_throwsException() {
        List<Dish> dishes =  Collections.singletonList(Dish.builder().withIsVegetarian(true).build());
        assertThrows(Exception.class, () -> StreamDrills.listCountriesOfOrigin(dishes));
    }

    private void assumeVegDishes(List<Dish> initial, List<Dish> expected) {
        List<Dish> actual = StreamDrills.vegetarianDishes(initial);
        assertEquals(expected,
            actual,
            String.format("When calling vegetarianDishes on %s, expected response of %s, but received %s.",
                initial,
                expected,
                actual));
    }

    private void assumeThreeHighCaloricDishNames(List<Dish> initial, List<String> expected) {

        List<String> actual = drills.threeHighCaloricDishNames(initial);
        assertEquals(expected,
            actual,
            String.format("When calling threeHighCaloricDishNames on %s, expected response of %s, but received %s.",
                initial,
                expected,
                actual));
    }

    private void assumeHowManyDishes(List<Dish> initial, long expected) {
        long actual = drills.howManyDishes(initial);
        assertEquals(expected,
            actual,
            String.format("When calling howManyDishes on %s, expected response of %d, but received %d.",
                initial,
                expected,
                actual));
    }

    private void assumeThreeHighCaloricDishNamesSize(List<Dish> initial, List<String> expected) {
        List<String> actual = drills.threeHighCaloricDishNames(initial);
        assertEquals(expected.size(),
            actual.size(),
            String.format("When calling threeHighCaloricDishNames on %s, expected 3 responses, but received %d.",
                initial,
                actual.size()));
    }

    private void assumeUniqueEvens(List<Integer> initial, List<Integer> expected) {
        List<Integer> actual = drills.uniqueEvenNumbers(initial);
        assertEquals(expected,
            actual,
            String.format("When calling uniqueEvenNumbers on %s, expected response of %s, but received %s.",
                initial,
                expected,
                actual));
    }

    private void assumeLengthOfDishNames(List<Dish> initial, List<Integer> expected) {

        List<Integer> actual = drills.lengthOfDishNames(initial);
        assertEquals(expected,
            actual,
            String.format("When calling lengthOfDishNames on %s, expected response of %s, but received %s.",
                initial,
                expected,
                actual));

    }

    private void assumeIsMenuVegetarianFriendly(List<Dish> initial, boolean expected) {
        boolean actual = drills.isMenuVegetarianFriendly(initial);
        assertEquals(expected,
            actual,
            String.format("When calling isMenuVegetarianFriendly on %s, expected response of %s, but received %s.",
                initial,
                expected,
                actual));
    }

    private void assumeIsEverythingUnder1000Calories(List<Dish> initial, boolean expected) {

        boolean actual = drills.isEverythingUnder1000Calories(initial);
        assertEquals(expected,
            actual,
            String.format("When calling isEverythingUnder1000Calories on %s, expected response of %s, but received %s.",
                initial,
                expected,
                actual));
    }

    private void assumeIsNothingOver1000Calories(List<Dish> initial, boolean expected) {
        boolean actual = drills.isNothingOver1000Calories(initial);
        assertEquals(expected,
            actual,
            String.format("When calling isNothingOver100Calories on %s, expected response of %s, but received %s.",
                initial,
                expected,
                actual));
    }

    private void assumeVegetarianDish(List<Dish> initial, Optional<Dish> expected) {
        Optional<Dish> actual = drills.vegetarianDish(initial);
        assertEquals(expected,
            actual,
            String.format("When calling vegetarianDish on %s, expected response of %s, but received %s.",
                initial,
                expected,
                actual));
    }
}
