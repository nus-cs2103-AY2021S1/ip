/**
 * Main class that drives the application.
 */
public class Duke {

    /** Deals with input output of files. */
    private Storage storage;
    /** Task list. */
    private TaskList tasks;
    /** Deals with user input output. */
    private Ui ui;

    /**
     * Constructor for the main driver.
     *
     * @param filePath File path to load history, and to save history.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e);
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
    }

    /**
     * Creates new Duke object to start operations.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}







