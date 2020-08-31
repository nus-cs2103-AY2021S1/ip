package seedu.duke;

/**
 * Represents the chat bot program
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean exitProgram;

    /**
     * Initializes Duke with the file path of the data file to be used for storage
     * Also initializes the Ui and TaskList.
     * @param filePath The file path to the data file
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.exitProgram = false;
        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (DukeException e) {
            this.tasks = new TaskList();
        }
    }

    /**
     * Returns the output of Duke based on the user's input and decide whether to exit program
     */
    public String getResponse(String input) {
        ui.clearOutputMessage();
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            this.exitProgram = c.isExit();
        } catch (DukeException err) {
            ui.showError(err.getMessage());
        }
        return ui.getOutputMessage();
    }

    public boolean getExitProgram() {
        return this.exitProgram;
    }
}
