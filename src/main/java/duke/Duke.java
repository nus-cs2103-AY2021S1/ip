package main.java.duke;

import main.java.duke.command.Command;
import main.java.duke.command.ExitCommand;

import java.util.Scanner;

/**
 * Main class of duke
 */
public class Duke {

    /** Pathname of the local data file */
    final String PATH_NAME = "./data/duke_data.csv";

    /** Storage */
    Storage storage;

    /** Task list*/
    TaskList tasks;

    /** User Interface using command line */
    Ui ui;

    /**
     * Constructor
     */
    Duke() {
        this.storage = new Storage(PATH_NAME);
        this.tasks = storage.loadTasks();
        this.ui = new Ui();
    }

    /**
     * Main method of Duke
     *
     * @param args String arguments (not necessary)
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.runDukeOnCli();
    }

    /**
     * Runs Duke on the command line
     */
    public void runDukeOnCli() {

        ui.printHello();

        // Initialize scanner to receive user inputs
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            Command command = Parser.parse(input);
            String[] outputStrings = command.execute(storage, tasks, ui);
            ui.print(outputStrings);

            // Exit command exits the program
            if (command instanceof ExitCommand) {
                break;
            }
        }
    }

    String getStartupMessage() {
        String[] strings = ui.getHelloStrings();
        StringBuilder output;

        if (strings.length >= 1) {
            output = new StringBuilder(strings[0]);
            for (int i = 1; i < strings.length; i++) {
                output.append("\n");
                output.append(strings[i]);
            }
            return output.toString();
        } else {
            return "Hello!";
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        Command command = Parser.parse(input);

        String[] strings = command.execute(storage, tasks, ui);
        StringBuilder output;

        if (strings.length >= 1) {
            output = new StringBuilder(strings[0]);
            for (int i = 1; i < strings.length; i++) {
                output.append("\n");
                output.append(strings[i]);
            }
            return output.toString();
        } else {
            return "No response";
        }
    }
}
