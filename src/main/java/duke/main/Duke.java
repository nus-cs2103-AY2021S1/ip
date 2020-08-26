package duke.main;

import duke.command.Command;
import duke.exception.DukeException;

import java.util.Scanner;

/**
 * Duke is a chatbot that can help us manage and store our various kinds of Task.
 */
public class Duke {

    /** TaskList to store Tasks. */
    private final TaskList tasks;
    /** Storage to store data to hard disk */
    private final Storage storage;
    /** Ui for interacting with user. */
    private final Ui ui;

    /**
     * Constructs a Duke.
     */
    public Duke() {
        // Create storage
        storage = new Storage();

        // Read from hard disk
        tasks = storage.readFromHardDisk();

        // Initialize Ui
        ui = new Ui();
    }

    /**
     * Runs Duke.
     */
    public void run() {
        // Display greeting message
        ui.showLine();
        ui.showGreetingMessage();
        ui.showLine();

        // Process user input
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            String input = sc.nextLine();
            try {
                Command command = Parser.parse(input);
                ui.showLine();
                command.perform(tasks);
                ui.showLine();
                storage.writeToHardDisk(tasks);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showLine();
                System.out.println(e.getMessage());
                ui.showLine();
            }
        }
    }

    /**
     * Main method of duke.
     *
     * @param args Arguments.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
