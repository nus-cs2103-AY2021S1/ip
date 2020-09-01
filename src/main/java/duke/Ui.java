package duke;

import java.util.Scanner;

/**
 * Handles interactions with the user while the user is using DukeBot.
 */
public class Ui {

    private static final String DIVIDER = "----------------------------------------";
    /** Scanner object to read user inputs. */
    private final Scanner scanner;

    /**
     * Creates and initialises a new Ui object to deal with user interactions
     * using a scanner object to read user inputs.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints a greeting to welcome the user when a new session with DukeBot
     * is initialised.
     */
    public static String showWelcome() {
        String text = "     ______  _____  __  ____     __     _______  \n"
                + "    |__  __||  __ | | \\ |  |    /_ \\    |   ___|   \n"
                + "       | |  | | | | |  \\|  |   //_\\ \\   |  |___   \n"
                + "    _  | |  | | | | | |\\   |  / ____ \\  |____  |   \n"
                + "   | |_| |  | |_| | | | \\  | / /    \\ \\ _____| |  \n"
                + "   |_____|  |_____| |_|  \\_|/_/      \\_\\|______|  \n"
                + "                     _____   ______  ________        \n"
                + "                     |  _ \\ |  _  | |__   __|       \n"
                + "                     | |_| || | | |    | |           \n"
                + "                     |    / | | | |    | |           \n"
                + "                     |  _ \\ | |_| |    | |          \n"
                + "                     | |_| ||     |    | |           \n"
                + "                     |_____/|_____|    |_|           \n";

        String greeting = "Hello! I am JonasBot! Nice to meet you :) \n"
                + text + "  \nI am a task manager bot that will keep track of all your tasks. \n"
                + "  \nTo view a list of all my commands, input '/commands' \n"
                + "  \nNow that you are familiar with the commands, how may I assist you today?";

        // this.showLine();
        return (greeting);
        //System.out.println(greeting);
        // this.showLine();
    }

    /**
     * Scans for user inputs.
     *
     * @return String representing the user input.
     */
    public String readCommand() {
        String message = scanner.nextLine();
        return message;
    }

    /**
     * Prints an error message.
     *
     * @param error String representing the error message.
     */
    public String showError(String error) {
        return error;
    }

    /**
     * Prints a reply to respond to user inputs.
     *
     * @param message String representing the message to send to users.
     */
    public String printReply(String message) {
        return message;
    }

    /**
     * Prints a farewell message when the session with DukeBot is terminated.
     */
    public String showFarewell() {
        String farewellMessage = "GoodBye and I hope to see you soon! Have a fantastic day! ";
        // System.out.println(farewellMessage);
        scanner.close();
        return farewellMessage;
    }

    /**
     * Prints a line to divide and segment the chat text.
     */
    public void showLine() {
        System.out.println(Ui.DIVIDER);
    }
}
