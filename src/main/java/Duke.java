/**
 * Duke is an application that stores a user's tasks.
 */
public class Duke {
    public static final String HORIZONTAL_LINE = "____________________________________________________________" + "\n";
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor that creates a Duke object.
     * @param filePath the filepath in which the application's data will be saved.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.taskList = new TaskList();
    }

    /**
     * Starts Duke and begins taking in user input.
     */
    public void run() {
        Parser.echo(ui, taskList, storage);
    }

    public static void main(String[] args) {
        new Duke(".//.//.//savedTasks.txt").run();
    }
}