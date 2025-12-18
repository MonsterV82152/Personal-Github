import java.util.ArrayList;

enum Species {
    DOG,
    CAT,
    BIRD,
    FISH
}

enum Location {
    PARK,
    HOME,
    VET,
    GARDEN,
}

enum Hobbies {
    WALKING,
    READING,
    GAMING,
    COOKING
}

public class VirtualPet {
    String name;
    Species species;
    Location currentLocation;

    int hunger;
    int happiness;
    int health;
    int energy;
    int intelligence;
    int damage;

    ArrayList<Hobbies> hobbies;

    public VirtualPet(String name, Species species) {
        this.name = name;
        this.species = species;
        this.currentLocation = Location.HOME;

        this.hunger = 10;
        this.happiness = 10;
        this.health = 10;
        this.energy = 10;
        this.intelligence = 0;
        this.damage = 1;

        this.hobbies = new ArrayList<Hobbies>();
    }

    public String getName() {
        return this.name;
    }

    public Species getSpecies() {
        return this.species;
    }

    public String toString() {
        return "Name: " + this.name + "\nSpecies: " + this.species + "\nLocation: " + this.currentLocation +
                "\nHunger: " + this.hunger + "\nHappiness: " + this.happiness +
                "\nHealth: " + this.health + "\nEnergy: " + this.energy +
                "\nIntelligence: " + this.intelligence + "\nDamage: " + this.damage +
                "\nHobbies: " + this.hobbies.toString();
    }

    public void feed(int foodAmount) {
        this.hunger += foodAmount;
        if (this.hunger > 10) {
            this.health -= 1;
            this.hunger = 10;
        }
    }

    public int getEnergyLevel() {
        return this.energy;
    }

    public int getHappinessLevel() {
        return this.happiness;
    }

    public void play(Hobbies hobby) {
        if (this.hobbies.contains(hobby)) {
            this.happiness += 2;
            this.energy -= 2;
            if (this.happiness > 10) {
                this.happiness = 10;
            }
            if (this.energy < 0) {
                this.energy = 0;
            }
        }

    }

    public void updateStatus() {
        this.hunger -= 1;
        this.energy -= 1;
        if (this.hunger < 0) {
            this.hunger = 0;
            this.health -= 1;
        }
        if (this.energy < 0) {
            this.energy = 0;
            this.happiness -= 1;
        }
        if (this.happiness < 0) {
            this.happiness = 0;
            this.health -= 1;
        }
        if (this.health < 0) {
            this.health = 0;
        }
    }

}
