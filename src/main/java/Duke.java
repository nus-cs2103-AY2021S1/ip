import java.util.Map;

public class Duke {

    // Attributes
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    // Constructor

    /**
     * Creates a new duke bot that saves list of tasks to /data/todo.txt.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("./data/todo.txt");
        this.tasks = storage.read();
    }

    /**
     * Creates a new duke bot that saves list of tasks to a given filepath.
     * @param filePath File path to save list of tasks to,
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = storage.read();
    }

    // Methods
    String getResponse(String fullCommand, Map<String, Runnable> runnables) {
        try {
            Command c = Parser.parse(fullCommand);
            return c.execute(tasks, ui, storage, runnables);
        } catch (DukeException e) {
            return e.toString();
        }
    }
}
