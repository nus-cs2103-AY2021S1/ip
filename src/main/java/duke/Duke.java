package duke;

import java.util.Scanner;

/**
 * Entry point of the To Do List application.
 * Initialises the application and starts the interaction with the user.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke class, initialises objects
     * for Storage, TaskList and Ui.
     * @param filePath the location the data file would be retrieved from.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            // ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    /**
     * Prints the welcome message, takes in and parses user input.
     */
    public void run() {
        ui.greeting();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            Parser.parseUserInput(input, ui, tasks, storage);
        }
    }

}
