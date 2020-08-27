package duke;

import java.util.Scanner;

/**
 * Encapsulates behaviour for all UI related actions.
 */
public class UI {

    private static final String DIVIDER = "___________________________________________________________________________";
    
    private static final String LOGO = "             _        ______   _____    ______   _____  \n"
           + "     /\\     | |      |  ____| |  __ \\  |  ____| |  __ \\ \n"
           + "    /  \\    | |      | |__    | |__) | | |__    | |  | |\n"
           + "   / /\\ \\   | |      |  __|   |  _  /  |  __|   | |  | |\n"
           + "  / ____ \\  | |____  | |      | | \\ \\  | |____  | |__| |\n"
           + " /_/    \\_\\ |______| |_|      |_|  \\_\\ |______| |_____/ \n";
    
    private final Scanner sc;
    private boolean hasClosed;

    /**
     * Creates a new instance of the UI class.
     */
    public UI() {
        hasClosed = false;
        sc = new Scanner(System.in);
    }

    /**
     * Prints a given message to the console. The message will be enclosed with
     * two horizontal lines.
     *
     * @param message Message to be printed.
     */
    public void printToConsole(String message) {
        System.out.println(DIVIDER);
        System.out.println(message);
        System.out.println(DIVIDER);
    }

    /**
     * Greets the user.
     */
    public void greet() {
        System.out.println(LOGO);
        printToConsole("Hi I'm Alfred! How can I help you today?");
    }

    /**
     * Prints goodbye message and stops the program from reading further input.
     */
    public void close() {
        printToConsole("Goodbye!");
        hasClosed = true;
        sc.close();
    }
    
    public String readCommand() {
        return sc.nextLine();
    }
    
    public boolean hasNextCommand() {
        return !hasClosed && sc.hasNextLine();
    }
}
