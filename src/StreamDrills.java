
import utilities.Dish;
import utilities.UnknownCountryOfOriginException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * To solve these you may need to go look at the stream java docs and look at what methods are available.
 * https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html
 */
public class StreamDrills {

    /**
     * Return only the dishes that are vegetarian.
     * @param menu every dish on the menu
     * @return a list of all of the vegetarian dishes on the menu
     */
    public static List<Dish> vegetarianDishes(List<Dish> menu) {
        return menu.stream()
                .filter(Dish::isVegetarian)
                .toList();
    }

    /**
     * Return all unique even numbers.
     * @param numbers a list of numbers
     * @return all of the unique, even numbers in the list
     */
    public static List<Integer> uniqueEvenNumbers(List<Integer> numbers) {
        return numbers.stream()
                .distinct()
                .filter(num -> num % 2 == 0)
                .toList();
    }

    /**
     * Return the length of each dish's name.
     * @param menu every dish on the menu
     * @return a list with the length of each dish's name.
     */
    public static List<Integer> lengthOfDishNames(List<Dish> menu) {
        return menu.stream()
                .map(dish -> dish.getName().length())
                .toList();
    }

    /**
     * Can a vegetarian eat anything at this restaurant????
     * @param menu every dish on the menu
     * @return true, if there is at least one vegetarian dish on the menu
     */
    public static boolean isMenuVegetarianFriendly(List<Dish> menu) {
        return !vegetarianDishes(menu).isEmpty();
    }

    /**
     * What's an example of a dish I can eat as a vegetarian?  If I can eat anything...
     * @param menu every dish on the menu
     * @return a vegetarian dish, if one exists on the menu
     */
    public static Optional<Dish> vegetarianDish(List<Dish> menu) {
        if (isMenuVegetarianFriendly(menu)) {
            return menu.stream()
                    .findFirst();
        }
        return Optional.empty();
    }

    /**
     * Is everything on the menu under 1000 calories?
     * @param menu every dish on the menu
     * @return true, if every dish on the menu is under 1,000 calories
     */
    public static boolean isEverythingUnder1000Calories(List<Dish> menu) {
        List<Dish> menuItems = menu.stream()
                .filter(item -> item.getCalories() < 1000)
                .toList();
        return menuItems.size() == menu.size();
    }

    /**
     * We want to validate our menu to make sure there is nothing over 1,000 calories.
     * @param menu every dish on the menu
     * @return true, if there isn't a dish on the menu over 1,000 calories
     */
    public static boolean isNothingOver1000Calories(List<Dish> menu) {
        List<Dish> menuItems = menu.stream()
                .filter(item -> item.getCalories() <= 1000)
                .toList();
        return menuItems.size() == menu.size();
    }

    /**
     * Return any 3 names of dishes where the calories are greater than 300.
     * @param menu every dish on the menu
     * @return the name of 3 dishes with more than 300 calories
     */
    public static List<String> threeHighCaloricDishNames(List<Dish> menu) {
        return menu.stream()
                .filter(item -> item.getCalories() > 300)
                .limit(3)
                .map(Dish::getName)
                .toList();
    }

    /**
     * How many dishes are on the menu?
     * @param menu every dish on the menu
     * @return the number of dishes on the menu
     */
    public static long howManyDishes(List<Dish> menu) {
        return menu.size();
    }

    /**
     * List the countries of origin for the menu.
     * @param menu every dish on the menu
     * @return the country of origin for every dish
     */
    public static Set<String> listCountriesOfOrigin(List<Dish> menu) {
        return menu.stream()
                .map(dish -> {
                    try {
                        return dish.getCountryOfOrigin();
                    } catch (UnknownCountryOfOriginException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toSet());
    }

}
