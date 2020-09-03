package duke;

/**
 * Main class that drives the application.
 */
public class Duke {

    /**
     * Deals with input output of files.
     */
    private Storage storage;
    /**
     * Task list.
     */
    private TaskList tasks;
    /**
     * Deals with user input output.
     */
    private Ui ui;

    /**
     * Constructor for the main driver.
     *
     * @param filePath File path to load history, and to save history.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e);
            tasks = new TaskList();
        }
    }

    String getWelcome() {
        return ui.showWelcome();
    }

    /**
      * Generate a response to user input.
     */
    String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        }  catch (DukeException e) {
            return ui.showError(e);
        }
    }

    boolean isExit(String input) {
        try {
            Command c = Parser.parse(input);
            return c.isExit();
        }  catch (DukeException e) {
            return false;
        }
    }
}
