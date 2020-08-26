/**
 * Duke is a bot that functions as a user's task manager.
 */
public class Duke {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    /**
     * Constructor that creates a Duke object.
     * @param file the file task sessions will be saved in
     */
    public Duke(String file) {
        this.ui = new Ui();
        this.storage = new Storage(file);
        this.taskList = new TaskList();
    }

    /**
     * Runs Duke's user input scanning that only terminates when a "bye" command is given.
     */
    public void run() {
        Parser.action(taskList, ui, storage);
    }

    public static void main(String[] args) {
        new Duke(".//SAVED-TASKS.txt").run();
    }
}

