import java.util.ArrayList;
import java.util.Random;

/**
 * Caylin Leia (Student ID: 261125917)
 */

public class Character {
    /**
     * This program will represent a character in our battle game
     * and will represent both a monster and player,
     * each with their own attributes
     */
    private String name; // String attribute used as an access modifier for the character's name
    private double attackValue; // Double attribute used as an access modifier for the character's attack value
    private double maxHealth; // Double attribute used as an access modifier for the character's maximum health value
    private double currHealth; // Double attribute used as an access modifier for the character's current health value
    private int numWins; // Integer attribute used as an access modifier for the character's number of wins in the battle game
    private ArrayList<String> spells = new ArrayList<>(); // Arraylist used as an access modifier to create and store the spells from the file
    static Spell[] spellList = new Spell[4]; // Array used to distribute the list of spells throughout the class

    // Constructor that represents the name, attack value, maximum health and number of wins in the battle game for the character
    public Character(String name, double attackValue, double maxHealth, int numWins) {
        this.name = name; // Refers to the current name being created for the character
        this.attackValue = attackValue; // Refers to the current attack value being created for the character
        this.maxHealth = maxHealth; // Refers to the current maximum health value being created for the character
        this.numWins = numWins; // Refers to the number of wins in the battle game for the character
    }

    public String getName() { // Retrieves the name of the character
        return this.name; // Returns the name of the character
    }

    public double getAttackValue() { // Retrieves the attack value of the character
        return this.attackValue; // Returns the attack value of the character
    }

    public double getMaxHealth() { // Retrieves the maximum health value of the character
        return this.maxHealth; // Returns the maximum health value of the character
    }

    public double getCurrHealth() { // Retrieves the current health of the character
        return this.currHealth; // Returns the current health value of the character
    }

    public int getNumWins() { // Retrieves the number of wins in the battle game
        return this.numWins; // Returns the number of wins in the battle game
    }

    public String toString() { // String to keep track of the health values of each character
        return (this.name + ": Health: " + this.currHealth); // Returns the name of the character and their current health
    }

    // Method used to calculate how much attack damage a character does when they attack
    public double getAttackDamage(int seed) {
        Random generate = new Random(seed); // Random used to generate a random value with the given input as seed
        double random = generate.nextDouble(); // Double used to generate the character's attack damage
        double random2 = generate.nextDouble(); // Second double used to generate the character's attack damage
        double random3 = generate.nextDouble(); // Third double used to generate the character's attack damage
        double attackValue = (0.7 + (new Random().nextDouble() * (1 - 0.7))); // Double used to calculate the character's attack value and multiply it by a random value between 0.7 (inclusive) and 1.0 (exclusive)
        return this.attackValue = (this.attackValue * attackValue); // Returns the attack value of the character
    }

    // Method used to take the damage done to the character
    public double takeDamage(double damage) {
        return this.currHealth = (this.currHealth - this.attackValue); // Returns the current health of the character
    }

    // Method used to increase the number of wins by the character by one
    public void increaseWins(int numWins) {
        numWins++; // Post increment count for the number of wins achieved by the character
    }

    public Spell[] setSpells(ArrayList<String> copySpellList) {
        String name = ""; // String used as an input parameter for the spell's name
        double minDamage = 0.0; // Double used as an input parameter for the spell's minimum magic damage value
        double maxDamage = 0.0; // Double used as an input parameter for the spell's maximum magic damage value
        double successChance = 0.0; // Double used as an input parameter for the spell's chance of success
        int j = 0; // Integer used to help copy the spells contained in the input parameter into a new ArrayList

        for (; j < copySpellList.size(); ) { // Loop used to continuously copy the spells onto the ArrayList while still allowing a return
            name = copySpellList.get(0 + j); // Retrieves the name of the spell name from the spell list
            minDamage = Double.parseDouble(copySpellList.get(1 + j)); // Retrieves the minimum magic damage value from the spell list
            maxDamage = Double.parseDouble(copySpellList.get(2 + j)); // Retrieves the maximum magic damage value from the spell list
            successChance = Double.parseDouble(copySpellList.get(3 + j)); // Retrieves the chance of success from the spell list
            j = j + 4; // Loop continues to copy the spells onto the spell list for all four attributes

            spellList[j / 4 - 1] = new Spell(name, minDamage, maxDamage, successChance); // Creates a new spell that gets added to the spell list
        }
        return spellList; // Returns the list of spells
    }

    // Method used to print out one spell per line
    public void displaySpells(ArrayList<String> copySpellList) {
        System.out.println(copySpellList); // Prints one spell per line from the copied list of spells
        // TODO: See if there is a way to use Spell.toString() for this method
    }

    public double castSpell(String spellName, int spellCast) {
        int i = 0; // Integer used to help search through the list for a specified spell

        for (; i < 4; i++) { // Loop used to continuously go through the list of spells to find the name matching the input
            if (spellName.equalsIgnoreCase(spellList[i].getName())) { // Verifying to determine if the spell name that is inputted matches any of the spells in the list
                return spellList[i].getMagicDamage(spellCast); // Returns the specified spell that is inputted if available and randomly assigns a magic damage value on the spell cast
            }
        }
        System.out.println(); // Prints an empty line for formatting purposes
        return -1; // Returns value if the spell cannot be found
    }
}

