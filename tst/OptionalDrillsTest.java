import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import utilities.Car;
import utilities.Dish;
import utilities.Insurance;

public class OptionalDrillsTest {

    @Test
    public void getDishName() {
        Optional<String> optional = OptionalDrills.getDishName(Dish.builder().withName("I has a name").build());
        assertTrue(optional.isPresent());
        assertEquals("I has a name", optional.get());
    }

    @Test
    public void getDishName_noDish() {
        Optional<String> optional = OptionalDrills.getDishName(null);
        assertFalse(optional.isPresent());
    }

    @Test
    public void getExistingInsuranceName() {
        Optional<String> optional = OptionalDrills.getExistingInsuranceName(new Car(new Insurance("insurance")));
        assertTrue(optional.isPresent());
        assertEquals("insurance", optional.get());
    }

    @Test
    public void getExistingInsuranceName_noInsuranceName() {
        Optional<String> optional = OptionalDrills.getExistingInsuranceName(new Car(new Insurance()));
        assertFalse(optional.isPresent());
    }

    @Test
    public void findCheapestInsuanceName() {
        Optional<String> optional = OptionalDrills.findCheapestInsuranceName(new Car(new Insurance()));
        assertFalse(optional.isPresent());
    }

    @Test
    public void findCheapestInsuranceName_noCar() {
        Optional<Car> car = Optional.empty();
        Optional<String> optional = OptionalDrills.findCheapestInsuranceName(car);
        assertFalse(optional.isPresent());
    }

    @Test
    public void findCheapestInsuranceName_car() {
        Optional<String> optional = OptionalDrills.findCheapestInsuranceName(Optional.of(new Car(new Insurance())));
        assertTrue(optional.isPresent());
        assertEquals("Amazon utilities.Insurance", optional.get());
    }
}
