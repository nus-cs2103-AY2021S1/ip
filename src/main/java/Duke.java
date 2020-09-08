
/**
 * Duke is a bot that functions as a user's task manager.
 */
public class Duke {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;
    private Parser parser;

    /**
     * Constructor that creates a Duke object.
     * @param file the file task sessions will be saved in
     */
    public Duke(String file) {
        this.ui = new Ui();
        this.storage = new Storage(file);
        this.taskList = new TaskList();
        this.parser = new Parser(ui, taskList, storage);
        storage.load(taskList, ui);
    }

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(".//SAVED-TASKS.txt");
        this.taskList = new TaskList();
        this.parser = new Parser(ui, taskList, storage);
        storage.load(taskList, ui);

    }


    public String getResponse(String input) {
        return parser.action(input);
    }
}

