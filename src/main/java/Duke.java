import java.io.FileNotFoundException;

/**
 * Represents a Duke robot that deals with multiple tasks.
 */
public class Duke {
    /**
     * The <code>Storage</code> used.
     */
    private Storage storage;
    /**
     * The list of task.
     */
    private TaskList tasks;
    /**
     * The user interface.
     */
    private Ui ui;
    /**
     * The <code>Parser</code> used.
     */
    private Parser parser;

    /**
     * Creates a new <code>Duke</code> with the given <code>filePath</code>.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs Duke.
     */
    public void run() {
        this.parser = new Parser(ui, tasks, storage);
        parser.run();
    }

    /**
     * Runs Duke with the given filePath.
     */
    public static void main(String[] args) {
        new Duke("C:\\Users\\e0316059\\Desktop\\Duke\\src\\main\\java\\data\\Duke.txt").run();
    }
}
