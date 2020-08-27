package duke;


/**
 * Represents the Duke bot, which has a task list, a storage space and a user interface.
 */
public class Duke {

    /** Storage space to save and load task list */
    private Storage storage;

    /** List of tasks saved by Duke */
    private Tasklist tasks;

    /** User interface for Duke */
    private Ui ui;

    /**
     * Creates a Duke object.
     *
     * @param filepath The path of the file where the
     *                 list of tasks is stored in a .txt file.
     */
    public Duke(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        try {
            tasks = storage.load();
        } catch (DukeException dukeException) {
            ui.showError(dukeException.getMessage());
            tasks = new Tasklist();
        }
    }

    /**
     * Runs the Duke bot.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command command = Parser.parseCommand(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

}
