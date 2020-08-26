package duke;

/**
 * Main class for duke to run.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initialises Duke.
     *
     * @param filePath A string of file path to store the task list.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Runs Duke.
     */
    public void run() {
        ui.logoMsg();
        ui.greetingMsg();
        Parser.parseUserInput(tasks);
        storage.writeTasks(tasks);
    }

    /**
     * Starts the program.
     * @param args Arges.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
