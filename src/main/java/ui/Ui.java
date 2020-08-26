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
    private String line = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    private String outputFormat = "  %s\n";
    private boolean ongoing;
    private Parser parser;

    /**
     * It sets ongoing to false. ongoing signifies whether the Java Duke Program is running.
     *
     * @param parser
     */
    public Ui(Parser parser) {
        ongoing = false;
        this.parser = parser;
    }

    /**
     * Gets the interaction with the user running by scanning the input from the user first.
     */
    public void run() {
        ongoing = true;
        greeting();
        Scanner scanner = new Scanner(System.in);
        while (ongoing) {
            String input = scanner.nextLine();
            systemOutput(input);
        }
    }

    /**
     * The input from the user is then sent to the Parser and the resulting String from
     * calling these functions will be printed to the user. But if the command is "bye",
     * it calles the goodbye function and sets ongoing to false.
     *
     * @param input
     */
    public void systemOutput(String input) {
        System.out.println(line + "\n");
        try {
            String output = parser.scenarios(input);
            if (output.equals("bye")) {
                ongoing = false;
                goodBye();
            } else {
                System.out.println(output);
            }
        } catch (DukeException e) {
            System.out.printf(outputFormat, e.getMessage());
        }
        System.out.println("\n" + line);
    }

    /**
     * Greets the user.
     */
    public void greeting() {
        System.out.println(line + "\n         (^v^)");
        System.out.printf(outputFormat, "Hey there! I'm JavaDuke");
        System.out.printf(outputFormat, "What can I do for you?\n" + line);
    }

    /**
     * Says goodbye to the user.
     */
    public void goodBye() {
        System.out.printf(outputFormat, "          *(^v^)");
        System.out.printf(outputFormat, "Bye. Hope to see you again soon!");
    }

    /**
     * Shows the loading error message when an error occurs while loading the tasklist from
     * the file.
     */
    public void showLoadingError() {
        System.out.println("An error occurred while loading the task list. Please rerun the program again.");
    }
}
