package duke;

/**
 * The main class for Project Duke.
 */
public class Duke {
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Project Duke.
     * @param filePath The file path where the user's task list would be stored.
     */
    public Duke(String filePath) {
        Storage storage = new Storage(filePath);
        try {
            tasks = storage.load();
            ui = new Ui();
        } catch (DukeException e) {
            assert false;
            ui.showLoadingError(e);
        }
    }

    /**
     * Initializes the Duke User Interface with the user's previously saved tasks (if any)
     */
    public void run() {
        ui.initializeDukeUI(tasks);
    }

    /**
     * The main method for Project Duke.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
