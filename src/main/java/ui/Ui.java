package ui;

import duke.DukeException;
import parser.Parser;

import java.util.Scanner;

/**
 * UI deals with interactions with the user in the Java Duke Program
 *
 * @author (Sruthi)
 */
public class Ui {
    private final String lineFormat = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    private final String outputFormat = "  %s\n";
    private boolean isOngoing;
    private final Parser parser;

    /**
     * It sets ongoing to false. ongoing signifies whether the Java Duke Program is running.
     *
     * @param parser parser that is formatting the user's input to the correct format
     */
    public Ui(Parser parser) {
        isOngoing = false;
        this.parser = parser;
    }

    /**
     * Gets the interaction with the user running by scanning the input from the user first.
     */
    public void run() {
        isOngoing = true;
        greeting();
        Scanner scanner = new Scanner(System.in);
        while (isOngoing) {
            String input = scanner.nextLine();
            System.out.println(lineFormat + "\n");
            System.out.println(systemOutput(input));
            System.out.println("\n" + lineFormat);
        }
    }

    /**
     * The input from the user is then sent to the Parser and the resulting String from
     * calling these functions will be printed to the user. But if the command is "bye",
     * it calles the goodbye function and sets ongoing to false.
     *
     * @param input input given by the user
     * @return String that needs to be printed to the user
     */
    public String systemOutput(String input) {
        try {
            String output = parser.scenarios(input);
            if (output.equals("bye")) {
                isOngoing = false;
                return goodBye();
            } else {
                return output;
            }
        } catch (DukeException e) {
            return "  " + e.getMessage() + "\n";
        }
    }

    /**
     * Greets the user.
     */
    public void greeting() {
        System.out.println(lineFormat + "\n         (^v^)");
        System.out.printf(outputFormat, "Hey there! I'm JavaDuke");
        System.out.printf(outputFormat, "What can I do for you?\n" + lineFormat);
    }

    /**
     * @return String that greets the user
     */
    public static String guiGreeting() {
        return "  (^v^)" + "  Hey there! I'm Lan Zhan\n" + "  What can I do for you?\n";
    }

    /**
     * Says goodbye to the user.
     */
    public String goodBye() {
        return "  " + "          *(^v^)" + "\n" + "  " + "Bye. Hope to see you again soon!" + "\n";
    }

    /**
     * Shows the loading error message when an error occurs while loading the tasklist from
     * the file.
     */
    public void showLoadingError() {
        System.out.println("An error occurred while loading the task list. Please rerun the program again.");
    }
}
