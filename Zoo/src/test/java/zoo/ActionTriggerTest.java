package zoo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ActionTriggerTest {

    private static Zoo zoo;
    private static ActionTrigger trigger;

    private AnimalType carnivore = AnimalType.CARNIVORE;
    private AnimalType herbivore = AnimalType.HERBIVORE;

    private static final String CONFIG_TYPE = "json";

    @BeforeAll
    static void setup() {

        zoo = new Zoo();
        String fileFile = ZooTest.class.getClassLoader().getResource("zooAnimals."+CONFIG_TYPE).getPath();
        zoo.addAnimals(fileFile, CONFIG_TYPE);
        trigger = new ActionTrigger(zoo);
    }

    @Test
    void visit() {
        trigger.visit(herbivore);
        assertEquals(zoo.getAllHerbivoreState(), AnimalState.MAKE_NOISE);
        assertEquals(zoo.getAllCarnivoreState(), AnimalState.MAKE_NOISE);

        trigger.visit(carnivore);
        assertEquals(zoo.getAllCarnivoreState(), AnimalState.MAKE_NOISE);
    }

    @Test
    void feedAnimals() {
        trigger.feedAnimals(carnivore);
        assertEquals(zoo.getAllCarnivoreState(), AnimalState.CALM);
        trigger.feedAnimals(herbivore);
        assertEquals(zoo.getAllHerbivoreState(), AnimalState.CALM);
    }

    @Test
    void drinkAnimals() {
        trigger.drinkAnimals(carnivore);
        assertEquals(zoo.getAllCarnivoreState(), AnimalState.CALM);
        trigger.drinkAnimals(herbivore);
        assertEquals(zoo.getAllHerbivoreState(), AnimalState.CALM);
    }

    @Test
    void setNight() {
        zoo.setAllHerbivoreState(AnimalState.CALM);
        zoo.setAllCarnivoreState(AnimalState.CALM);
        trigger.setNight();
        assertEquals(zoo.getAllHerbivoreState(), AnimalState.SLEEP);
        assertEquals(zoo.getAllCarnivoreState(), AnimalState.SLEEP);

        zoo.setAllHerbivoreState(AnimalState.CALM);
        zoo.setAllCarnivoreState(AnimalState.MAKE_NOISE);
        trigger.setNight();
        assertNotEquals(zoo.getAllHerbivoreState(), AnimalState.SLEEP);
        assertNotEquals(zoo.getAllCarnivoreState(), AnimalState.SLEEP);
    }

    @Test
    void setMorning() {
        zoo.setAllHerbivoreState(AnimalState.SLEEP);
        zoo.setAllCarnivoreState(AnimalState.SLEEP);
        trigger.setMorning();
        assertEquals(zoo.getAllHerbivoreState(), AnimalState.CALM);
        assertEquals(zoo.getAllCarnivoreState(), AnimalState.CALM);

        zoo.setAllHerbivoreState(AnimalState.MAKE_NOISE);
        zoo.setAllCarnivoreState(AnimalState.CALM);
        trigger.setMorning();
        assertNotEquals(zoo.getAllHerbivoreState(), AnimalState.CALM);
        assertEquals(zoo.getAllCarnivoreState(), AnimalState.CALM);
    }

    @Test
    void setThunder() {
        trigger.setThunder();
        assertEquals(zoo.getAllHerbivoreState(), AnimalState.MAKE_NOISE);
        assertEquals(zoo.getAllCarnivoreState(), AnimalState.MAKE_NOISE);
    }

    @Test
    void setRain() {
        trigger.setRain();
        assertEquals(zoo.getAllHerbivoreState(), AnimalState.MAKE_NOISE);
        assertEquals(zoo.getAllCarnivoreState(), AnimalState.MAKE_NOISE);
    }
}