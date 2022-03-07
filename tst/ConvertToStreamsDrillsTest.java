

import org.junit.jupiter.api.Test;
import utilities.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utilities.Dish.Type.*;

public class ConvertToStreamsDrillsTest {
    private static final User USER1 = User.builder().withName("Sonia").withAge(29).build();
    private static final User USER2 = User.builder().withName("Halston").withAge(67).build();
    private static final User USER3 = User.builder().withName("Navid").withAge(29).build();
    private static final User USER4 = User.builder().withName("Asim").withAge(18).build();
    private static final User USER5 = User.builder().withName("Jude").withAge(18).build();

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

    @Test
    public void returnSquareRootTest1() {
        assertReturnSquareRoot(Arrays.asList(), Arrays.asList());
    }

    @Test
    public void returnSquareRootTest2() {
        assertReturnSquareRoot(Arrays.asList(0), Arrays.asList((double) 0));
    }

    @Test
    public void returnSquareRootTest3() {
        assertReturnSquareRoot(Arrays.asList(16), Arrays.asList((double) 4));
    }

    @Test
    public void returnSquareRootTest4() {
        assertReturnSquareRoot(Arrays.asList(5), Arrays.asList(Math.sqrt(5)));
    }

    @Test
    public void returnSquareRootTest5() {
        assertReturnSquareRoot(Arrays.asList(3, 9, -4, 29),
            Arrays.asList(Math.sqrt(3), Math.sqrt(9), Math.sqrt(-4), Math.sqrt(29)));
    }

    @Test
    public void returnSquareRootTest6() {
        assertReturnSquareRoot(Arrays.asList(3, 3, 3, 3),
            Arrays.asList(Math.sqrt(3), Math.sqrt(3), Math.sqrt(3), Math.sqrt(3)));
    }

    @Test
    public void getAgeFromUsersTest1() {
        assertGetAgeFromUsers(Arrays.asList(), Arrays.asList());
    }

    @Test
    public void getAgeFromUsersTest2() {
        assertGetAgeFromUsers(Arrays.asList(USER1), Arrays.asList(29));
    }

    @Test
    public void getAgeFromUsersTest3() {
        assertGetAgeFromUsers(Arrays.asList(USER1, USER1, USER1), Arrays.asList(29, 29, 29));
    }

    @Test
    public void getAgeFromUsersTest4() {
        assertGetAgeFromUsers(Arrays.asList(USER1, USER2, USER3, USER4, USER5), Arrays.asList(29, 67, 29, 18, 18));
    }

    @Test
    public void getDistinctAgesTest1() {
        assertGetDistinctAges(Arrays.asList(), Arrays.asList());
    }

    @Test
    public void getDistinctAgesTest2() {
        assertGetDistinctAges(Arrays.asList(USER1), Arrays.asList(29));
    }

    @Test
    public void getDistinctAgesTest3() {
        assertGetDistinctAges(Arrays.asList(USER1, USER1), Arrays.asList(29));
    }

    @Test
    public void getDistinctAgesTest4() {
        assertGetDistinctAges(Arrays.asList(USER1, USER3), Arrays.asList(29));
    }

    @Test
    public void getDistinctAgesTest5() {
        assertGetDistinctAges(Arrays.asList(USER1, USER2), Arrays.asList(29, 67));
    }

    @Test
    public void getDistinctAgesTest6() {
        assertGetDistinctAges(Arrays.asList(USER1, USER2, USER3, USER4, USER5), Arrays.asList(29, 67, 18));
    }

    @Test
    public void getLimitedUserListTest1() {
        assertGetLimitedUserList(Arrays.asList(), 0, Arrays.asList());
    }

    @Test
    public void getLimitedUserListTest2() {
        assertGetLimitedUserList(Arrays.asList(), 5, Arrays.asList());
    }

    @Test
    public void getLimitedUserListTest3() {
        assertGetLimitedUserList(Arrays.asList(USER1), 1, Arrays.asList(USER1));
    }

    @Test
    public void getLimitedUserListTest4() {
        assertGetLimitedUserList(Arrays.asList(USER1), 0, Arrays.asList());
    }

    @Test
    public void getLimitedUserListTest5() {
        assertGetLimitedUserList(Arrays.asList(USER1), 5, Arrays.asList(USER1));
    }

    @Test
    public void getLimitedUserListTest6() {
        assertGetLimitedUserList(Arrays.asList(USER1, USER1, USER1), 0, Arrays.asList());
    }

    @Test
    public void getLimitedUserListTest7() {
        assertGetLimitedUserList(Arrays.asList(USER1, USER1, USER1), 1, Arrays.asList(USER1));
    }

    @Test
    public void getLimitedUserListTest8() {
        assertGetLimitedUserList(Arrays.asList(USER1, USER1, USER1), 2, Arrays.asList(USER1, USER1));
    }

    @Test
    public void getLimitedUserListTest9() {
        assertGetLimitedUserList(Arrays.asList(USER1, USER1, USER1), 3, Arrays.asList(USER1, USER1, USER1));
    }

    @Test
    public void getLimitedUserListTest10() {
        assertGetLimitedUserList(Arrays.asList(USER1, USER1, USER1), 5, Arrays.asList(USER1, USER1, USER1));
    }

    @Test
    public void getLimitedUserListTest11() {
        assertGetLimitedUserList(Arrays.asList(USER1, USER2, USER3, USER4, USER5), 0, Arrays.asList());
    }

    @Test
    public void getLimitedUserListTest12() {
        assertGetLimitedUserList(Arrays.asList(USER1, USER2, USER3, USER4, USER5), 1, Arrays.asList(USER1));
    }

    @Test
    public void getLimitedUserListTest14() {
        assertGetLimitedUserList(Arrays.asList(USER1, USER2, USER3, USER4, USER5),
            4,
            Arrays.asList(USER1, USER2, USER3, USER4));
    }

    @Test
    public void getLimitedUserListTest15() {
        assertGetLimitedUserList(Arrays.asList(USER1, USER2, USER3, USER4, USER5),
            5,
            Arrays.asList(USER1, USER2, USER3, USER4, USER5));
    }

    @Test
    public void getLimitedUserListTest16() {
        assertGetLimitedUserList(Arrays.asList(USER1, USER2, USER3, USER4, USER5),
            6,
            Arrays.asList(USER1, USER2, USER3, USER4, USER5));
    }

    @Test
    public void countUserOlderThan25Test1() {
        assertCountUsersOlderThen25(Arrays.asList(), 0);
    }

    @Test
    public void countUserOlderThan25Test2() {
        assertCountUsersOlderThen25(Arrays.asList(USER1), 1);
    }

    @Test
    public void countUserOlderThan25Test3() {
        assertCountUsersOlderThen25(Arrays.asList(USER4), 0);
    }

    @Test
    public void countUserOlderThan25Test4() {
        assertCountUsersOlderThen25(Arrays.asList(USER1, USER1), 2);
    }

    @Test
    public void countUserOlderThan25Test5() {
        assertCountUsersOlderThen25(Arrays.asList(USER4, USER4), 0);
    }

    @Test
    public void countUserOlderThan25Test6() {
        assertCountUsersOlderThen25(Arrays.asList(USER1, USER2, USER3, USER4, USER5), 3);
    }

    @Test
    public void findAny_noUsersWithName() {
        List<User> names = Arrays.asList(USER1, USER2, USER3, USER4);
        assertFalse(ConvertToStreamsDrills.findAny(names, "Joe").isPresent());
    }

    @Test
    public void findAny_emptyList() {
        List<User> names = Arrays.asList();
        assertFalse(ConvertToStreamsDrills.findAny(names, "Joe").isPresent());
    }

    @Test
    public void findAny_oneUserWithName() {
        List<User> names = Arrays.asList(USER1, USER2, USER3, USER4);
        assertTrue(ConvertToStreamsDrills.findAny(names, "Navid").isPresent());
    }

    @Test
    public void findAny_manyUsersWithName() {
        List<User> names = Arrays.asList(USER1, USER3, USER2, USER3, USER4, USER3, USER3);
        assertTrue(ConvertToStreamsDrills.findAny(names, "Navid").isPresent());
    }

    @Test
    public void sortDishesByCalories_emptyList() {
        List<Dish> dishes = Arrays.asList();
        List<String> result = ConvertToStreamsDrills.sortDishesByCalories(dishes);
        assertTrue(result.isEmpty());
    }

    @Test
    public void sortDishesByCalories_allUnder400() {
        List<Dish> dishes = Arrays.asList(SALAD, SALAD, SALAD);
        List<String> result = ConvertToStreamsDrills.sortDishesByCalories(dishes);
        assertEquals(Arrays.asList("Harvest Salad", "Harvest Salad", "Harvest Salad"), result);
    }

    @Test
    public void sortDishesByCalories_allOver400() {
        List<Dish> dishes = Arrays.asList(SUSHI, CHICKEN, STEAK);
        List<String> result = ConvertToStreamsDrills.sortDishesByCalories(dishes);
        assertTrue(result.isEmpty());
    }

    @Test
    public void sortDishesByCalories_randomDishes() {
        List<Dish> dishes = Arrays.asList(SUSHI, SALAD, SALAD, STEAK, SALAD);
        List<String> result = ConvertToStreamsDrills.sortDishesByCalories(dishes);
        assertEquals(Arrays.asList("Harvest Salad", "Harvest Salad", "Harvest Salad"), result);
    }

    @Test
    public void newDeck() {
        List<Card> deck = ConvertToStreamsDrills.newDeck();
        assertEquals(52, deck.size());
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                assertTrue(deck.contains(new Card(suit, rank)));
            }
        }
    }

    public void assertReturnSquareRoot(List<Integer> initial, List<Double> expected) {
        List<Double> actual = ConvertToStreamsDrills.returnSquareRoot(new ArrayList<>(initial));
        assertEquals(expected,
            actual,
            String.format("When calling returnSquareRoot on %s, expected response of %s, but received %s.",
                initial,
                expected,
                actual));
    }

    public void assertGetAgeFromUsers(List<User> initial, List<Integer> expected) {
        List<Integer> actual = ConvertToStreamsDrills.getAgeFromUsers(new ArrayList<>(initial));
        assertEquals(expected,
            actual,
            String.format("When calling getAgeFromUsers on %s, expected response of %s, but received %s.",
                initial,
                expected,
                actual));
    }

    public void assertGetDistinctAges(List<User> initial, List<Integer> expected) {
        List<Integer> actual = ConvertToStreamsDrills.getDistinctAges(new ArrayList<>(initial));
        assertEquals(expected,
            actual,
            String.format("When calling getDistinctAges on %s, expected response of %s, but received %s.",
                initial,
                expected,
                actual));
    }

    public void assertGetLimitedUserList(List<User> initial, int limit, List<User> expected) {
        List<User> actual = ConvertToStreamsDrills.getLimitedUserList(new ArrayList<>(initial), limit);
        assertEquals(expected,
            actual,
            String.format("When calling getLimitedUserList on %s, expected response of %s, but received %s.",
                initial,
                expected,
                actual));
    }

    public void assertCountUsersOlderThen25(List<User> initial, long expected) {
        long actual = ConvertToStreamsDrills.countUsersOlderThan25(new ArrayList<>(initial));
        assertEquals(expected,
            actual,
            String.format("When calling countUsersOlderThan25 on %s, expected response of %s, but received %s.",
                initial,
                expected,
                actual));
    }
}
