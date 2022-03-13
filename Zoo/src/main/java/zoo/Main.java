package zoo;

import com.beust.jcommander.JCommander;

public class Main {

    public static void main(String[] argv) {
        // Parse arguments
        ConfigArgs args = new ConfigArgs();
        JCommander.newBuilder()
                .addObject(args)
                .build()
                .parse(argv);
        String configFile = args.getConfigFile();
        String configType = args.getConfigType();

        // Create zoo
        Zoo zoo = new Zoo();
        // Add animals to the zoo
        zoo.addAnimals(configFile, configType);

        // Create user action trigger
        ActionTrigger trigger = new ActionTrigger(zoo);

        AnimalType herbivore = AnimalType.HERBIVORE;
        AnimalType carnivore = AnimalType.CARNIVORE;

        // All of the following actions are up to users choice
        zoo.printAllStates();
        trigger.setMorning();
        zoo.printAllStates();

        trigger.visit(herbivore);
        zoo.printAllStates();
//        trigger.visit(carnivore);
        trigger.feedAnimals(herbivore);
        zoo.printAllStates();

        trigger.setNight();
        zoo.printAllStates();

        trigger.setMorning();
        zoo.printAllStates();

        trigger.setThunder();
        zoo.printAllStates();
        trigger.feedAnimals(carnivore);
        zoo.printAllStates();

        trigger.feedAnimals(herbivore);
        zoo.printAllStates();

        trigger.setRain();
        zoo.printAllStates();

        trigger.feedAnimals(carnivore);
        zoo.printAllStates();
        trigger.drinkAnimals(herbivore);
        zoo.printAllStates();

        trigger.setNight();
        zoo.printAllStates();

        trigger.setMorning();
        zoo.printAllStates();
    }
}