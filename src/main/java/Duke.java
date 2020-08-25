/**
 * Represents the chat bot program
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initializes Duke with the file path of the data file to be used for storage
     * Also initializes the Ui and TaskList.
     * @param filePath The file path to the data file
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    /**
     * Runs the chat bot program continuously until the user gives the exit program command.
     */
    public void run() {
        ui.showWelcome();
        boolean exitProgram = false;
        while (!exitProgram) {
            try {
                ui.showBlankLine();
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                exitProgram = c.isExit();
            } catch (DukeException err) {
                ui.showError(err.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
}
