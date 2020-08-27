package Duke;

import Command.Command;

/**
 * Duke is the main class that runs the Spanish Duke program.
 * @author Joshua
 */
public class Duke {

    /**
     * The storage that will contain the saved data for Duke.
     * The tasks that are currently in the TaskList.
     * The ui that is interacting with the user.
     */
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates an instance of Duke from the saved data in the file path where it
     * was stored.
     * @param filePath the file path where data is stored.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load(ui));
    }

    /**
     * Initializes Duke.
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
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Runs the Duke program.
     */
    public static void main(String[] args) {
        new Duke("D:/uni/CS2103T/Duke(iP)/ip/data/tasks.txt").run();
    }
}
