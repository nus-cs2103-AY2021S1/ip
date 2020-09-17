package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Encapsulates information and methods that relate to a chat bot that functions as a task manager.
 */
public class Duke {

    /** Ui object that handles user interactions. */
    private final Ui ui;
    /** Storage object that handles file operations. */
    private final Storage storage;
    /** TaskList object containing the user's list of tasks. */
    private final TaskList tasks;

    /**
     * Creates and initialises a new Duke object that has a Ui, Storage and TaskList object.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasks = this.storage.readFile();
    }

    /**
     * Executes a Duke session for the bot to perform its intended functions.
     */
    public void run() {
        System.out.println(Ui.showWelcome());
        System.out.println(Ui.showLine());
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                System.out.println(Ui.showLine());
                Command command = Parser.parse(fullCommand);
                System.out.println(command.execute(tasks, ui, storage));
                isExit = command.isExit();
            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
            } finally {
                System.out.println(Ui.showLine());
            }
        }
    }

    /**
     * Retrieves the response by Duke when the user provides an input.
     *
     * @param input Input provided by the user.
     * @return String containing the response to the user input.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, ui, storage);
        } catch (DukeException ex) {
            return ex.getMessage();
        }
    }

    /**
     * Initialises a new Duke session.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        Duke session = new Duke();
        session.run();
    }
}
