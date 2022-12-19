import java.util.*;

/**
 * Caylin Leia (Student ID: 261125917)
 */

public class BattleGame {
    /**
     * This program will simulate a battle game
     * where the player will alternate turns with the monster
     * via attack commands inputted by the user
     */
    private static int seed = 123; // Integer attribute used to set the seed as a random number value
    private static Random rand = new Random(); // Random used to generate a random object created with no seed
    private static int fixSeed = rand.nextInt(); // Integer attribute used to fix a seed for debugging purposes
    // Character player; // Class used to create and display the player character
    // Character monster; // Class used to create and display the monster character

    public static void main(String[] args) {
        String playerFile = "src/player.txt"; // String used to extract the information about the player character from the text file
        String monsterFile = "src/monster.txt"; // String used to extract the information about the monster character from the text file
        String spellsFile = "src/spells.txt"; // String used to extract the information about the spells list from the text file

        try { // Block used to test and throw an exception if there is no file found for the player or monster characters and spells
            playGame(playerFile, monsterFile, spellsFile); // Method used to play the game using the player, monster and spells files
        } catch (Exception e) { // Throws an exception if an error is found
            System.out.println("Error: The game cannot be played"); // Prints an error message notifying the user that the game cannot be played
        }
    }

    // Method used to play the game
    public static void playGame(String player, String monster, String spells) {
        Scanner scan = new Scanner(System.in); // Scanner used to obtain the input of a command in the game

        try { // Block used to test and throw an exception if one is raised with playing as the character or monster
            FileIO.readCharacter(player); // Obtaining information about the player character from the FileIO class
            System.out.println("Name: " + FileIO.readCharacter(player).getName()); // Prints the name of the player character
            System.out.println("Health: " + FileIO.readCharacter(player).getMaxHealth()); // Prints the health value of the player character
            System.out.println("Attack: " + FileIO.readCharacter(player).getAttackValue()); // Prints the attack value of the player character
            System.out.println("Number of Wins: " + FileIO.readCharacter(player).getNumWins()); // Prints the number of wins achieved by the player character

            FileIO.readCharacter(monster); // Obtaining information about the monster character from the FileIO class
            System.out.println("\nName: " + FileIO.readCharacter(monster).getName()); // Prints the name of the monster character
            System.out.println("Health: " + FileIO.readCharacter(monster).getMaxHealth()); // Prints the health value of the monster character
            System.out.println("Attack: " + FileIO.readCharacter(monster).getAttackValue()); // Prints the attack value of the monster character
            System.out.println("Number of Wins: " + FileIO.readCharacter(monster).getNumWins()); // Prints the number of wins achieved by the monster character

            String playerAttackStr = ""; // String used to display the attack done by the player
            String monsterAttackStr = ""; // String used to display the attack done by the monster
            double playerAttackDouble; // Double used to display the amount of damage the player's attack inflicted
            double monsterAttackDouble; // Double used to display the amount of damage the monster's attack inflicted
            double playerHealth = FileIO.readCharacter(player).getMaxHealth(); // Double used to display the amount of health the player has
            double monsterHealth = FileIO.readCharacter(monster).getMaxHealth(); // Double used to display the amount of health the monster has

            int round = 0; // Integer used to manage the incremented count for the number of rounds more easily
            int playerNumWins = 0; // Integer used to manage the incremented count for the number of wins from the player mor easily
            int monsterNumWins = 0; // Integer used to manage the incremented count for the number of wins from the monster more easily

            if (FileIO.readSpells(spells).isEmpty()) { // Verifying to determine if the method contains any spells to be used in the game
                System.out.println("\nThe game will be played without spells."); // Prints a message notifying the user that the game will be played without spells

                while (true) { // Loop used to run the game repeatedly until the game ends
                    playerAttackStr = String.format("%1$.2f", FileIO.readCharacter(player).getAttackDamage(fixSeed)); // String used to display a random attack value from the player
                    monsterAttackStr = String.format("%1$.2f", FileIO.readCharacter(monster).getAttackDamage(fixSeed)); // String used to display a random attack value from the monster
                    playerAttackDouble = Double.parseDouble(playerAttackStr); // Double used to return a new double to display the attack value from the player
                    monsterAttackDouble = Double.parseDouble(monsterAttackStr); // Double used to return a new double to display the attack value from the monster
                    playerHealth = playerHealth - monsterAttackDouble; // Determining the amount of health from the player that is lost due to the attack inflicted by the monster
                    String playerHealthStr = String.format("%1$.2f", playerHealth); // String used to display the remaining health value of the player
                    monsterHealth = monsterHealth - playerAttackDouble; // Determining the amount of health from the monster that is lost due to the attack inflicted by the player
                    String monsterHealthStr = String.format("%1$.2f", monsterHealth); // String used to display the remaining health value of the monster

                    System.out.println("\nEnter a command: "); // Prints a prompt for the user to enter a game command
                    String command = scan.next(); // String used as an input for the user to enter a game command
                    //scan.next(); // Scanner reads the user's input to determine what occurs onwards

                    if (command.equals("attack")) { // Verifying to determine what will occur next if the user inputs an attack command
                        if (round % 2 == 0) { // Verifying to determine the outcome for the player before alternating turns with the monster
                            if (monsterHealth <= 0) { // Verifying to determine the outcome once the monster's health value is 0
                                System.out.println(FileIO.readCharacter(player).getName() + " attacks for " + playerAttackStr + " damage!"); // Prints a message declaring how much damage the player has inflicted towards the monster
                                System.out.println(FileIO.readCharacter(monster).getName() + " was knocked out!\n" + "\nFantastic! You killed the monster!"); // Prints a message declaring that the monster has been defeated and that the player has won
                                playerNumWins++; // Post increment count for the number of wins achieved by the player
                                System.out.println(FileIO.readCharacter(player).getName() + " has won " + playerNumWins + " times."); // Prints a message to notify how many times the player won during the game
                                FileIO.writeCharacter(player, "src/player.txt"); // Writes and saves the number of wins for the player to indicate which character has own more often
                                break; // Breaks the loop
                            }
                            System.out.println(FileIO.readCharacter(player).getName() + " attacks for " + playerAttackStr + " damage!"); // Prints a message declaring how much damage the player has inflicted towards the monster
                            System.out.println(FileIO.readCharacter(monster).getName() + " current health is " + monsterHealthStr + ".\n"); // Prints a message declaring how much health the monster has remaining
                            round++; // Post incremented count added to completed rounds
                        }
                        if (round % 2 == 1) { // Verifying to determine the outcome for the monster after alternating turns with the player
                            if (playerHealth <= 0) { // Verifying to determine the outcome once the player's health value is at 0
                                System.out.println(FileIO.readCharacter(monster).getName() + " attacks for " + monsterAttackStr + " damage!"); // Prints a message declaring how much damage the monster has inflicted towards the player
                                System.out.println(FileIO.readCharacter(player).getName() + " was knocked out!\n" + "\nOh no! You lost!"); // Prints a message declaring that the player has been defeated and has lost
                                monsterNumWins++; // Post increment count for the number of wins achieved by the monster
                                System.out.println(FileIO.readCharacter(monster).getName() + " has won: " + monsterNumWins + " times."); // Prints a message to notify how many times the monster has won during the game
                                FileIO.writeCharacter(monster, "src/monster.txt"); // Writes and saves the number of wins for the monster to indicate which character has own more often
                                break; // Breaks the loop
                            }
                            System.out.println(FileIO.readCharacter(monster).getName() + " attacks for " + monsterAttackStr + " damage!"); // Prints a message declaring how much damage the monster has inflicted towards the player
                            System.out.println(FileIO.readCharacter(player).getName() + " current health is " + playerHealthStr + "."); // Prints a message declaring how much health the player has remaining
                            round++; // Post incremented count added to completed rounds
                        }
                    } else if (command.equals("quit")) { // Verifying to determine if the user wants to quit the game
                        System.out.println("Goodbye!"); // Prints a message to notify the user that they are exiting the game
                        break; // Breaks the loop and exits the game
                    } else {
                        System.out.println("Error: Please enter a command to either attack or quit the game"); // Prints an error message notifying the user that their input was invalid, and they need to either enter an attack command or quit the game
                    }
                }
            } else {
                int i = 0; // Integer used to help search through the list for a specified spell
                System.out.println("\nHere are the available spells:"); // Prints a message to notify the user the list of spells that are available for use in the game

                for (; i < FileIO.readSpells(spells).size(); i = i + 4) { // Loop used to read the list of spells available for use in the game
                    System.out.print("Name: "); // Prints the name of the spell
                    System.out.print(FileIO.readCharacter(player).setSpells(FileIO.readSpells(spells))[i / 4].getName() + "\t"); // Prints a message indicating the name of the spell from the spell list that the player can use
                    System.out.print("Damage: "); // Prints the name of the spell's magic damage
                    System.out.print(Double.parseDouble(FileIO.readSpells(spells).get(i + 1)) + " - "); // Prints a message indicating the minimum magic damage value of the specified spell
                    System.out.print(Double.parseDouble(FileIO.readSpells(spells).get(i + 2)) + "\t"); // Prints a message indicating the maximum magic damage value of the specified spell
                    System.out.print("Chance: "); // Prints the spell's chance of success
                    System.out.print(Double.parseDouble(FileIO.readSpells(spells).get(i + 3)) * 100 + "%\n"); // Prints a message indicating the spell's chance of success of the specified spell and displays it as a percentage
                }
                while (true) { // Loop used to run the game repeatedly (with spells) until the game ends
                    playerAttackStr = String.format("%1$.2f", FileIO.readCharacter(player).getAttackDamage(fixSeed)); // String used to display a random attack value from the player
                    monsterAttackStr = String.format("%1$.2f", FileIO.readCharacter(monster).getAttackDamage(fixSeed)); // String used to display a random attack value from the monster
                    playerAttackDouble = Double.parseDouble(playerAttackStr); // Double used to return a new double to display the attack value from the player
                    monsterAttackDouble = Double.parseDouble(monsterAttackStr); // Double used to return a new double to display the attack value from the monster
                    playerHealth = playerHealth - monsterAttackDouble; // Determining the amount of health from the player that is lost due to the attack inflicted by the monster
                    String playerHealthStr = String.format("%1$.2f", playerHealth); // String used to display the remaining health value of the player
                    String monsterHealthStr = String.format("%1$.2f", monsterHealth); // String used to display the remaining health value of the monster

                    System.out.println("\nEnter a command: "); // Prints a prompt for the user to enter a game command
                    String command = scan.next(); // String used as an input for the user to enter a game command
                    //scan.next(); // Scanner reads the user's input to determine what occurs onwards
                    //double magicDamage = FileIO.readCharacter(player).castSpell(command, fixSeed); // Double used to display a random magic damage value from the player

                    if (command.equals("attack")) { // Verifying to determine what will occur next if the user inputs an attack command
                        //monsterHealth = monsterHealth - playerAttackDouble; // Determining the amount of health from the monster that is lost due to the magic damage inflicted by the player
                        //monsterHealthStr = String.format("%1$.2f", monsterHealth); // String used to display a random magic damage value from the player inflicted towards the monster
                        if (round % 2 == 0) { // Verifying to determine the outcome for the player before alternating turns with the monster
                            if (monsterHealth <= 0.0) { // Verifying to determine the outcome once the monster's health value is 0
                                System.out.println(FileIO.readCharacter(player).getName() + " attacks for " + playerAttackStr + " damage!"); // Prints a message declaring how much damage the player has inflicted towards the monster
                                System.out.println(FileIO.readCharacter(monster).getName() + " was knocked out!\n" + "\nFantastic! You killed the monster!"); // Prints a message declaring that the monster has been defeated and that the player has won
                                playerNumWins++; // Post increment count for the number of wins achieved by the player
                                System.out.println(FileIO.readCharacter(player).getName() + " has won " + playerNumWins + " times."); // Prints a message to notify how many times the player won during the game
                                FileIO.writeCharacter(player, "src/player.txt"); // Writes and saves the number of wins for the player to indicate which character has won more often
                                break; // Breaks the loop
                            }
                            System.out.println(FileIO.readCharacter(player).getName() + " attacks for " + playerAttackStr + " damage!"); // Prints a message declaring how much damage the player has inflicted towards the monster
                            System.out.println(FileIO.readCharacter(monster).getName() + " current health is " + monsterHealthStr + ".\n"); // Prints a message declaring how much health the monster has remaining
                            round++; // Post incremented count added to completed rounds
                        }
                        if (round % 2 == 1) { // Verifying to determine the outcome for the monster after alternating turns with the player
                            if (playerHealth <= 0.0) { // Verifying to determine the outcome once the player's health value is at 0
                                System.out.println(FileIO.readCharacter(monster).getName() + " attacks for " + monsterAttackStr + " damage!"); // Prints a message declaring how much damage the monster has inflicted towards the player
                                System.out.println(FileIO.readCharacter(player).getName() + " was knocked out!\n" + "\nOh no! You lost!"); // Prints a message declaring that the player has been defeated and has lost
                                monsterNumWins++; // Post increment count for the number of wins achieved by the monster
                                System.out.println(FileIO.readCharacter(monster).getName() + " has won: " + monsterNumWins + " times."); // Prints a message to notify how many times the monster has won during the game
                                FileIO.writeCharacter(monster, "src/monster.txt"); // Writes and saves the number of wins for the monster to indicate which character has won more often
                                break; // Breaks the loop
                            }
                            System.out.println(FileIO.readCharacter(monster).getName() + " attacks for " + monsterAttackStr + " damage!"); // Prints a message declaring how much damage the monster has inflicted towards the player
                            System.out.println(FileIO.readCharacter(player).getName() + " current health is " + playerHealthStr + ".\n"); // Prints a message declaring how much health the player has remaining
                            round++; // Post incremented count added to completed rounds
                        }
                    } else if (command.equals("quit")) { // Verifying to determine if the user wants to quit the game
                        System.out.println("Goodbye!"); // Prints a message to notify the user that they are exiting the game
                        break; // Breaks the loop and exits the game
                    } else { // Verifying to determine if the user inputs neither an attack nor quit command that they cast a spell instead
                        double magicDamage = FileIO.readCharacter(player).castSpell(command, fixSeed); // Double used to display a random magic damage value from the player
                        String magicDamageStr = String.format("%1$.2f", magicDamage); // String used to display a random value of magic damage from the player

                        if (magicDamage == 0.0) { // Verifying to determine if the magic damage returned is equal to 0
                            if (playerHealth <= 0 && monsterHealth >= 0) { // Verifying to determine how much magic damage the player inflicts on the monster
                                System.out.println(FileIO.readCharacter(player).getName() + " tried to cast " + command + ", but they failed.\n"); // Prints a message declaring that the spell the player tried to cast failed
                                System.out.println(FileIO.readCharacter(monster).getName() + " attacks for " + monsterAttackStr + " damage!"); // Prints a message declaring how much damage the monster has inflicted towards the player
                                System.out.println(FileIO.readCharacter(player).getName() + " was knocked out!\n" + "\n Oh no! You lost!"); // Prints a message declaring that the player has been defeated and has lost
                                monsterNumWins++; // Post increment count for the number of wins achieved by the monster
                                System.out.println(FileIO.readCharacter(monster).getName() + " has won: " + monsterNumWins + " times."); // Prints a message to notify how many times the monster has won during the game
                                FileIO.writeCharacter(monster, "src/monster.txt"); // Writes and saves the number of wins for the monster to indicate which character has won more often
                                break; // Breaks the loop
                            }
                            System.out.println(FileIO.readCharacter(player).getName() + " casts " + command + " dealing " + magicDamage + " damage!"); // Prints a message declaring which spell the player cast and how much damage it inflicted towards the monster
                            System.out.println(FileIO.readCharacter(monster).getName() + " current health is " + monsterHealthStr + ".\n"); // Prints a message declaring how much health the monster has remaining
                            System.out.println(FileIO.readCharacter(monster).getName() + " attacks for " + monsterAttackStr + " damage!"); // Prints a message declaring how much damage the monster has inflicted towards the player
                            System.out.println(FileIO.readCharacter(player).getName() + " current health is " + playerHealthStr + ".\n"); // Prints a message declaring how much health the player has remaining
                        } else if (magicDamage == -1) { // Verifying to determine if the damage returned is less than 0
                            if (playerHealth <= 0 && monsterHealth >= 0) { // Verifying to determine how much magic damage the player inflicts on the monster
                                System.out.println(FileIO.readCharacter(player).getName() + " tried to cast " + command + ", but they don't know that spell.\n"); // Prints a message declaring which spell the player cast and that it was unsuccessful due to not knowing specified spell
                                System.out.println(FileIO.readCharacter(monster).getName() + " attacks for " + monsterAttackStr + " damage!"); // Prints a message declaring how much damage the monster has inflicted towards the player
                                System.out.println(FileIO.readCharacter(player).getName() + " was knocked out!\n" + "\nOh no! You lost!"); // Prints a message declaring that the player has been defeated and has lost
                                monsterNumWins++; // Post increment count for the number of wins achieved by the monster
                                System.out.println(FileIO.readCharacter(monster).getName() + " has won: " + monsterNumWins + " times."); // Prints a message to notify how many times the monster won during the game
                                FileIO.writeCharacter(monster, "src/monster.txt"); // Writes and saves the number of wins for the monster to indicate which character has won more often
                                break;
                            }
                            System.out.println(FileIO.readCharacter(player).getName() + " tried to cast " + command + ", but they failed.\n"); // Prints a message declaring that the spell the player tried to cast failed
                            System.out.println(FileIO.readCharacter(monster).getName() + " attacks for " + monsterAttackStr + " damage!"); // Prints a message declaring how much damage the monster has inflicted towards the player
                            System.out.println(FileIO.readCharacter(player).getName() + " current health is " + playerHealthStr + ".\n"); // Prints a message declaring how much health the player has remaining
                        } else { // Verifying to determine if the spell cast was successful
                            monsterHealth = monsterHealth - magicDamage; // Determining the amount of health from the monster that is lost due to the magic damage inflicted by the player
                            monsterHealthStr = String.format("%1$.2f", monsterHealth); // String used to display a random magic damage value from the player inflicted towards the monster

                            if (playerHealth <= 0 && monsterHealth <= 0) { // Verifying to determine if the spell was successfully cast out and the player wins
                                System.out.println(FileIO.readCharacter(player).getName() + " casts " + command + " dealing " + magicDamageStr + " damage!"); // Prints a message declaring which spell the player cast and how much damage it inflicted towards the monster
                                System.out.println(FileIO.readCharacter(monster).getName() + " was knocked out!\n" + "\nFantastic! You killed the monster!"); // Prints a message declaring that the monster has been defeated and that the player has won
                                playerNumWins++; // Post increment count for the number of wins achieved by the player
                                System.out.println(FileIO.readCharacter(player).getName() + " has won: " + playerNumWins + " times."); // Prints a message to notify how many times the player won during the game
                                FileIO.writeCharacter(player, "src/player.txt"); // Writes and saves the number of wins for the player to indicate which character has won more often
                                break; // Breaks the loop
                            }
                            if (playerHealth <= 0 && monsterHealth >= 0) { // Verifying to determine if the spell casts and the monster wins
                                System.out.println(FileIO.readCharacter(player).getName() + " casts " + command + " dealing " + magicDamageStr + " damage!"); // Prints a message declaring which spell the player cast and how much damage it inflicted towards the monster
                                System.out.println(FileIO.readCharacter(monster).getName() + " current health is " + monsterHealthStr + ".\n"); // Prints a message declaring how much health the monster has remaining
                                System.out.println(FileIO.readCharacter(monster).getName() + " attacks for " + monsterAttackStr + " damage!"); // Prints a message declaring how much damage the monster has inflicted towards the player
                                System.out.println(FileIO.readCharacter(player).getName() + " was knocked out!\n" + "\nOh no! You lost!"); // Prints a message declaring that the player has been defeated and has lost
                                monsterNumWins++; // Post increment count for the number of wins achieved by the monster
                                System.out.println(FileIO.readCharacter(monster).getName() + " has won: " + monsterNumWins + " times."); // Prints a message to notify how many times the monster won during the game
                                FileIO.writeCharacter(monster, "src/monster.txt"); // Writes and saves the number of wins for the player to indicate which character has won more often
                                break; // Breaks the loop
                            }
                            System.out.println(FileIO.readCharacter(player).getName() + " casts " + command + " dealing " + magicDamageStr + " damage!"); // Prints a message declaring which spell the player cast and how much damage it inflicted towards the monster
                            System.out.println(FileIO.readCharacter(monster).getName() + " current health is " + monsterHealthStr + ".\n"); // Prints a message declaring how much health the monster has remaining
                            System.out.println(FileIO.readCharacter(monster).getName() + " attacks for " + monsterAttackStr + " damage!"); // Prints a message declaring how much damage the monster has inflicted towards the player
                            System.out.println(FileIO.readCharacter(player).getName() + " current health is " + playerHealthStr + ".\n"); // Prints a message declaring how much health the player has remaining
                        }
                    }
                }
            }
        } catch (
                Exception e) { // Throws an exception if any errors occur while playing the game
            System.out.println(e); // Prints a message notifying the user of the exception that has been thrown
            System.out.println("Error: The game cannot be played"); // Prints an error message notifying the user that the game is unplayable
        }
        scan.close(); // Closes and ends the scanner
    }
}