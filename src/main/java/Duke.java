/**
 * Starts Duke which a user can give
 * text commands to.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Creates a Duke object
     *
     * @param filePath  Location of file where data is stored.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = TaskList.generateTaskList(storage);
    }

    /**
     * Starts the Duke application.
     *
     * @param args Array of command-line arguments.
     */
    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }

    private void run() {
        ui.start(taskList);
    }
}
