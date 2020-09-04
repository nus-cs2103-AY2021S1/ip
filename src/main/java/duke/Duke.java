package duke;

/**
 * Main class to run Ui object and listen for user input.
 */
public class Duke {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    /**
     * Constructor to initialise Duke object.
     * @param filePath Location of Duke.ser
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Generates a response to user input.
     */
    String getResponse(String input) {
        return this.ui.initialise(tasks, storage, input);
    }
}
