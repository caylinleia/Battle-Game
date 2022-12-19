import java.util.ArrayList;
import java.util.Random;

/**
 * Caylin Leia (Student ID: 261125917)
 */

public class Spell {
    private String name; // String attribute used as an access modifier for the spell's name
    private double minDamage; // Double attribute used as an access modifier for the spell's minimum damage
    private double maxDamage; // Double attribute used as an access modifier for the spell's maximum damage
    private double successChance; // Double attribute used as an access modifier for the spell's chance for success

    // Constructor that takes as input the name, minimum/maximum damage and chance of success for the spell
    public Spell(String name, double minDamage, double maxDamage, double successChance) {
        if ((minDamage < 0.0) || (minDamage > maxDamage) || (successChance < 0.0) || (successChance > 1.0)) { // Verifying to determine if the minimum damage is less than 0 or greater than the maximum damage, or if the chance of success is less than 0 or greater than 1
            throw new IllegalArgumentException("Error!"); // Throws an exception and prints an error message
        } else {
            this.name = name; // Refers to the current name being created for the spell
            this.minDamage = minDamage; // Refers to the minimum damage value being created for the spell
            this.maxDamage = maxDamage; // // Refers to the maximum damage value being created for the spell
            this.successChance = successChance; // Refers to the chance of success for the spell
        }
    }

    public String getName() { // Retrieves the name of the spell
        return this.name; // Returns the name of the spell
    }

    // Method used to retrieve the magic damage produced by the spell
    public double getMagicDamage(int seed) {
        Random generate = new Random(seed); // Random used to generate a random value with the given input as seed
        double random = generate.nextDouble();  // Double used to generate the spell's magic damage
        double random2 = generate.nextDouble(); // Second double used to generate the spell's magic damage
        double random3 = generate.nextDouble(); // Third double used to generate the spell's magic damage

        if (random3 > this.successChance) { // Verifying to determine if the random value of magic damage is above the chance of success
            return 0.0; // Returns 0 damage and the spell fails
        } else {
            //double magicDamage = (new Random().nextDouble() * (maxDamage - minDamage) + minDamage); // Double used to determine a random value of magic damage between the minimum and maximum damage
            //return magicDamage; // Returns the value of magic damage inflicted
            return (generate.nextDouble() * (maxDamage - minDamage)) + minDamage;
        }
    }

    // Method used to retrieve the name, minimum/maximum damage and chance of success
    public String toString() {
        return this.name + this.minDamage + this.maxDamage + this.successChance; // Returns the name, minimum/maximum damage and chance of success of the spell
    }
}
