package duke;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * Represents a Duke chat bot.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a new instance of Duke object.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Creates a new instance of Duke object.
     * Initializes the TaskList, Ui and Storage.
     *
     * @param filePath Path of file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(ui.showLoadingError());
            tasks = new TaskList();
        }
    }

    /**
     * Executes the running of the chat bot.
     */
    public void run() {
        System.out.println(Ui.greet());
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                System.out.println(ui.showLine());
                Command c = Parser.parse(fullCommand);
                System.out.println(c.execute(tasks, ui, storage));
                isExit = c.isExit();
            } catch (DukeException e) {
                System.out.println(ui.showError(e.getMessage()));
            } finally {
                System.out.println(ui.showLine());
            }
        }
    }

    /**
     * Parses user inputs into commands and returns it as a string.
     *
     * @param input User input.
     * @return String of executed command.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }

    /**
     * Runs the chat bot.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
