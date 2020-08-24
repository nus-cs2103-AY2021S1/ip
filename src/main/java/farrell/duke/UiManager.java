package main.java.farrell.duke;

import java.util.Scanner;

/**
 * Encapsulates behavior for interacting with the user.
 * This class uses System.in to receive inputs from the user and System.out to display its output.
 */
public class UiManager {
    final String logo =
            " _\n" +
            "//\\\n" +
            "V  \\\n" +
            " \\  \\_\n" +
            "  \\,'.`-.\n" +
            "   |\\ `. `.\n" +
            "   ( \\  `. `-.                        _,.-:\\\n" +
            "    \\ \\   `.  `-._             __..--' ,-';/\n" +
            "     \\ `.   `-.   `-..___..---'   _.--' ,'/\n" +
            "      `. `.    `-._        __..--'    ,' /\n" +
            "        `. `-_     ``--..''       _.-' ,'\n" +
            "          `-_ `-.___        __,--'   ,'\n" +
            "             `-.__  `----\"\"\"    __.-'\n" +
            "                   `--..____..--'";

    Scanner scanner = new Scanner(System.in);

    /**
     * Checks if there is any outstanding user input.
     *
     * @return true if there outstanding user input.
     */
    public boolean hasUserInput() {
        return scanner.hasNextLine();
    }

    /**
     * Retrieves the next user input.
     *
     * @return The String that the user input.
     */
    public String getNextInput() {
        return scanner.nextLine();
    }

    /**
     * Displays the welcome message.
     */
    public void displayStartMessage() {
        System.out.println(logo);
        printInWindow("Hello, I'm a banana.\nWhat can I do for you?");
    }

    /**
     * Displays text inside a formatted window.
     *
     * @param text The text to display.
     */
    public void printInWindow(String text) {
        final String divider = "---------------------------------------------";
        System.out.println(divider);
        System.out.println(text);
        System.out.println(divider);
    }
}
