package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.task.TaskList;

/**
 * Represents a functional chat-bot that helps to keep track of todo, deadline and event tasks.
 */
public class Duke {

    /** Handles all User Interaction elements of the chat-bot. */
    protected Ui ui;

    /** Handles any read/write requests. */
    private final Storage storage;

    /** Container for the storage of tasks. */
    private TaskList tasks;


    /**
     * Loads tasks into a task list from a local file that is read in based on an user-specified filepath.
     *
     * @param filePath The filepath to find the local file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            System.out.println(ui.showLoadingError() + e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Takes in user input, parses the input and executes the correct command to process the tasks.
     * Exits only when an ExitCommand is generated based on user input.
     */
    public void run() {
        System.out.println(ui.showWelcome());
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = new Parser(fullCommand).parse();
                String returnString = c.execute(tasks, ui, storage);
                if (returnString != null) {
                    System.out.println(returnString);
                }
                isExit = c.isExit();
            } catch (DukeException e) {
                System.out.println(ui.showError(e.getMessage()));
            }
        }
        System.out.println("Goodbye! Hope you have a great one!");
    }

    /**
     * Takes in user input, parses the input and executes the correct command to process the tasks.
     * Used for running the program GUI.
     * Exits only when an ExitCommand is generated based on user input.
     *
     * @param input The user input in the form of a string.
     * @return The response to the user based on the user input.
     */
    public String run(String input) {
        try {
            Command c = new Parser(input).parse();
            if (!c.isExit()) {
                return c.execute(tasks, ui, storage);
            }
        } catch (DukeException e) {
            return (ui.showError(e.getMessage()));
        }
        return "Thanks for using me! Goodbye!";
    }

    /**
     * Generate a response to user input.
     *
     * @param input User input.
     * @return The response to the user input.
     */
    protected String getResponse(String input) {
        return new Duke("data/tasks.txt").run(input);
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

}
