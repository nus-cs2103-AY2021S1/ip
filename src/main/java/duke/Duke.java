package main.java.duke;

import main.java.duke.command.Command;
import main.java.duke.exception.DukeException;

/**
 * Represents a Duke chat bot.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Executes the running of the chat bot.
     */
    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Runs the chat bot.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
