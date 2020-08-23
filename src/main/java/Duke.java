/**
 * Represents the Duke bot.
 */
public class Duke {
    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;

    /**
     * Creates a Duke bot.
     * @param name The name of the text file data is stored at
     */
    public Duke(String name) {
        this.ui = new Ui();
        this.taskList = new TaskList();
        this.storage = new Storage(taskList, name);
    }

    /**
     * Runs the Duke bot program
     */
    public void run() {
        ui.startUp(taskList, storage);
        Parser.parseInput(taskList, storage);
    }

    public static void main(String[] args) {
        new Duke("duke").run();
    }
}
