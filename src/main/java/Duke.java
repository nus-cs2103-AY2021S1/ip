/**
 * Duke is the application that the user is aware of.
 */
public class Duke {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Creates a new Duke.
     *
     * @param filePath file path where a file containing taskList from last execution of Duke is stored.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.initializeTasks());
    }

    /**
     * Starts an execution of Duke.
     * There is a Welcome, a series of Commands and finally a Goodbye from Duke.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException | TaskException e) {
                ui.showResponse(e.getMessage());
            }
        }
        ui.showGoodbye();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
