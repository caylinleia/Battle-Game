import java.io.*;
import java.util.ArrayList;

/**
 * Caylin Leia (Student ID: 261125917)
 */

public class FileIO {
    /**
     * This program will allow the user to input information
     * from their keyboard to which the program will obtain
     * the information and create an input/output file
     */
    public static void main(String[] args) {
        String fileName = "src/player.txt" + "src/monster.txt"; // String used as a parameter to extract information about the player and monster characters
        writeCharacter("src/player.txt", "src/monster.txt"); // Method used to write information about the player and monster characters onto a file
    }

    // Method used to return the player character
    public static Character readCharacter(String fileName) {
        String name = ""; // String used as an input parameter for the character's name
        double attackValue = 0.0; // Double used as an input parameter for the character's attack value
        double maxHealth = 0.0; // Double used as an input parameter for the character's maximum health value
        double currHealth = 0.0; // Double used as an input parameter for the character's current health value
        int numWins = 0; // Integer used as an input parameter for the character's number of wins in the battle game
        ArrayList<String> fileInfo = new ArrayList<>(); // ArrayList used to permit null if an exception is raised when reading from the file

        try { // Block used to test and throw an exception if one is raised with the file or IO
            FileReader fr = new FileReader(fileName); // FileReader used to throw a "FileNotFoundException" if the filename doesn't exist
            BufferedReader br = new BufferedReader(fr); // BufferedReader used to open the file specified by the filename
            String readLine = br.readLine(); // String used to read the lines of a text in a filename
            int count = 0; // Integer used to manage the incremented count more easily

            while (readLine != null) { // Loop used to determine if an FileNotFoundException or IOException is raised
                fileInfo.add(readLine); // Adds a method used to return the value as true if the element is not null
                readLine = br.readLine(); // String used to throw a "IOException" if an IO error occurs
                count++; // Post increment count for any exception found
            }
            br.close(); // Closes and ends the buffer reader
            fr.close(); // Closes and ends the file reader

            for (int i = 0; i < count; i++) { // Loop used to go through the files to determine if there are any exceptions that need to be made
                if (i % 4 == 0) { // Verifying to determine if the first line of information in the file raises any exceptions
                    name = fileInfo.get(i); // Returns value to the String (name of character) if no exceptions are found in the loop and carries to the next line of information on the file
                } else if (i % 4 == 1) { // Verifying to determine if the second line of information in the file raises any exceptions
                    attackValue = Double.parseDouble(fileInfo.get(i)); // Returns (double) value to the double (attack value) if no exceptions are found in the loop and carries to the next line of information on the file
                } else if (i % 4 == 2) { // Verifying to determine if the third line of information in the file raises any exceptions
                    maxHealth = Double.parseDouble(fileInfo.get(i)); // Returns (double) value to the double (maximum health value) if no exceptions are found in the loop and carries to the next line of information on the file
                } else if (i % 4 == 3) { // Verifying to determine if the fourth line of information in the file raises any exceptions
                    numWins = Integer.parseInt(fileInfo.get(i)); // Returns value to the integer (number of wins) if no exceptions are found in the loop
                }
            }
        } catch (
                FileNotFoundException e) { // Throws an exception if no file is found
            System.out.println(e); // Prints an error message notifying the user that the file is not found
            return null; // Returns null if an exception is raised
        } catch (
                IOException ioe) { // Throws an exception if an input/output error is found
            System.out.println(ioe); // Prints an error message notifying the user that the input/output is not available
            return null; // Returns null if an exception is raised
        }
        Character createCharacter = new Character(name, attackValue, maxHealth, numWins); // Creates a new character that displays the name, attack value, health value and number of wins
        return createCharacter; // Returns value and the newly created character is set
    }

    // Method used to take a filename as input and return an ArrayList of spells
    public static ArrayList<String> readSpells(String fileName) {
        String name = ""; // String used as an input parameter for the spell's name
        double minDamage = 0.0; // Double used as an input parameter for the spell's minimum magic damage value
        double maxDamage = 0.0; // Double used as an input parameter for the spell's maximum magic damage value
        double successChance = 0.0; // Double used as an input parameter for the spell's chance of success
        ArrayList<String> fileInfo = new ArrayList<>(); // ArrayList used to create a new list for the files received
        ArrayList<String> spells = new ArrayList<>(); // ArrayList used to create a new list for the spells received
        String[] individualSpell = new String[4]; // Array used to store each individual spell in the ArrayList that can be returned by the method
        int i = 0; // Integer used to help search through the list for a specified spell
        int j = 0; // Integer used to help search through the list for a specified spell

        try { // Block used to test and throw an exception if one is raised with the file or IO
            FileReader fr = new FileReader(fileName); // FileReader used to throw a "FileNotFoundException" if the filename doesn't exist
            BufferedReader br = new BufferedReader(fr); // BufferedReader used to open the file specified by the filename
            String readLine = br.readLine(); // String used to read the lines of a text in a filename

            while(readLine != null) { // Loop used to determine if an FileNotFoundException or IOException is raised
                fileInfo.add(readLine); // Adds a method used to return the value as true if the element is not null
                readLine = br.readLine(); // String used to throw a "IOException" if an IO error occurs
            }
            br.close(); // Closes and ends the BufferReader
            fr.close(); // Closes and ends the FileReader

            for (; i < fileInfo.size(); i++) { // Loop used to continuously go through the individual spells that will be used in the game (if disabled, the game is played without spells)
                individualSpell = fileInfo.get(i).split("\t"); // Splits the list of spells into individual, separate spells
                spells.add(individualSpell[0]); // Adds the first individual spell from the spells list
                spells.add(individualSpell[1]); // Adds the second individual spell from the spells list
                spells.add(individualSpell[2]); // Adds the third individual spell from the spells list
                spells.add(individualSpell[3]); // Adds the fourth individual spell from the spells list

                for (; j < spells.size() ;) { // Loop used to continuously go through the spells list to extract information
                    name = spells.get(0 + j); // Extracts the name of the spell of the individual spell
                    minDamage = Double.parseDouble(spells.get(1 + j)); // Extracts the minimum magic damage value of the individual spell
                    maxDamage = Double.parseDouble(spells.get(2 + j)); // Extracts the maximum magic damage value of the individual spell
                    successChance = Double.parseDouble(spells.get(3 + j)); // Extracts the chance of success of the individual spell
                    j = j + 4; // Represents the information extracted from the loop for each individual spell
                }
            }
        } catch (FileNotFoundException e) { // Throws an exception if no file is found
            System.out.println(e + "null"); // Prints an error message notifying the user that the file is not found
        } catch (IOException ioe) { // Throws an exception if an input/output error is found
            System.out.println(ioe + "null"); // Prints an error message notifying the user that the input/output is not available
        }
        return spells; // Returns value if no exceptions are found
    }
    // Method used to take an input from the Character class to write and a String that indicates the filename to write to
    public static void writeCharacter(String name, String writeFile) {
        try { // Block used to test and throw an exception if one is raised with the file or IO
            BufferedReader file = new BufferedReader(new FileReader(name)); // BufferedReader used to write the file specified by the filename
            StringBuffer input = new StringBuffer(); // StringBuffer used to input multiple Strings to write the file

            if (file.readLine() != null) { // Verifying to determine if the file can be written properly
                input.append(readCharacter(name).getName()).append("\n"); // Adds the character's name to the file
                input.append(readCharacter(name).getAttackValue() + "\n"); // Adds the String value of the character's attack value to the file
                input.append(readCharacter(name).getMaxHealth() + "\n"); // Adds the String value of the character's health value to the file
                int numWins = (readCharacter(name).getNumWins() + 1); // Integer used to increase the number of wins by a character by 1
                input.append(numWins); // Adds the String value of the character's number of wins to the file
            }
            file.close(); // Closes and ends the BufferReader
            FileOutputStream fileExport = new FileOutputStream(writeFile); // FileOutputStream used to export and write the information from the game to the file
            fileExport.write(input.toString().getBytes()); // Writes the information from the buffer and encodes it onto an external file
            fileExport.close(); // Closes and ends the FileOutputStream
        } catch (IOException ioe) { // Throws an exception if an input/output error is found
            System.out.println(ioe); // Prints the exception that has been thrown
            System.out.println("Error: Failed to write to file: " + writeFile); // Prints an error message notifying the user that the file failed to be written
        }
        System.out.println("Successfully wrote to file: " + writeFile); // Prints a message notifying the user that the file has been successfully written
    }
}
