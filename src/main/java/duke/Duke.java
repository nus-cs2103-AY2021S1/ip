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
    String getResponse(String input) throws Exception {
        return this.ui.initialise(tasks, storage, input);
    }

    /**
     * Main method
     * @param args Parameters
     * @throws Exception File not found exception
     */
    public static void main(String[] args) throws Exception {
        // remember to change filepath to "../../../data/duke.txt" during jar build
        // Duke duke = new Duke("data/duke.txt");
        // duke.run();
    }
}
