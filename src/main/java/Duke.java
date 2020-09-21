import java.io.FileNotFoundException;

/**
 * Represents a Duke robot that deals with multiple tasks.
 */
public class Duke {
    /**
     * The <code>Storage</code> used in Duke.
     */
    private Storage storage;
    /**
     * The list of tasks.
     */
    private TaskList tasks;
    /**
     * The user interface.
     */
    private Ui ui;
    /**
     * The <code>Parser</code> used in Duke.
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
            System.out.println(ui.showLoadingError());
            this.tasks = new TaskList();
        }
    }

    /**
     * Creates a new <code>Duke</code>.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("data");
        try {
            this.tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            System.out.println(ui.showLoadingError());
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
        new Duke().run();
    }

    /**
     * Generates a response to user input.
     * @param input the user command.
     * @return a response message to user.
     */
    public String getResponse(String input) {
        this.parser = new Parser(ui, tasks, storage);
        String response = "";
        try {
            response = parser.handleCommand(input);
        } catch (DukeException e) {
            response = ui.showCommandError(e);
        }
        return response;
    }
}