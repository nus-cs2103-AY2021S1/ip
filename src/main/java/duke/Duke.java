package duke;

import duke.command.Command;

/**
 * The Duke program is a chat bot which will keep track of
 * tasks from the user input and store the them in a list.
 *
 * @author yuxuan.
 * @version v0.3.
 * @since 2020-08-15.
 */
public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructor for Duke.
     *
     * @param filePath The path of the file which stores the task list.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Reads user input and executes the type of reply accordingly.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Initialises Duke and runs the chat bot.
     *
     * @param args an array of command-line arguments for Duke to read.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}