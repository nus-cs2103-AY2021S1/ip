package ui;

import java.util.Scanner;

import parser.Parser;

import duke.DukeException;

/**
 * UI deals with interactions with the user in the Java Duke Program
 *
 * @author (Sruthi)
 */
public class Ui {
    private final String LINE_FORMAT = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    private final String OUTPUT_FORMAT = "  %s\n";
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
            systemOutput(input);
        }
    }

    /**
     * The input from the user is then sent to the Parser and the resulting String from
     * calling these functions will be printed to the user. But if the command is "bye",
     * it calles the goodbye function and sets ongoing to false.
     *
     * @param input input given by the user
     */
    public void systemOutput(String input) {
        System.out.println(LINE_FORMAT + "\n");
        try {
            String output = parser.scenarios(input);
            if (output.equals("bye")) {
                isOngoing = false;
                goodBye();
            } else {
                System.out.println(output);
            }
        } catch (DukeException e) {
            System.out.printf(OUTPUT_FORMAT, e.getMessage());
        }
        System.out.println("\n" + LINE_FORMAT);
    }

    /**
     * Greets the user.
     */
    public void greeting() {
        System.out.println(LINE_FORMAT + "\n         (^v^)");
        System.out.printf(OUTPUT_FORMAT, "Hey there! I'm JavaDuke");
        System.out.printf(OUTPUT_FORMAT, "What can I do for you?\n" + LINE_FORMAT);
    }

    /**
     * Says goodbye to the user.
     */
    public void goodBye() {
        System.out.printf(OUTPUT_FORMAT, "          *(^v^)");
        System.out.printf(OUTPUT_FORMAT, "Bye. Hope to see you again soon!");
    }

    /**
     * Shows the loading error message when an error occurs while loading the tasklist from
     * the file.
     */
    public void showLoadingError() {
        System.out.println("An error occurred while loading the task list. Please rerun the program again.");
    }
}
