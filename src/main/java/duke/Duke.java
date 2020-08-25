package duke;

import duke.exception.DukeException;
import duke.command.Command;

/** The duke bot that stores your tasks. */
public class Duke {

    /** Deals with interaction with the user. */
    private final Ui ui;

    /** Contains the task list and add/delete operations for the task list */
    private TaskList tasks;

    /** Deals with saving and loading the tasks from the file. */
    private final Storage storage;

    /**
     * Constructs a Duke bot.
     *
     * @param filePath The filepath to store the data in.
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

    /** Runs the bot, accepts tasks and saves them into the file. */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();

                ui.showLine(); // show the divider line ("_______")
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

    public static void main(String[] args) {
        Duke bot = new Duke("data");
        bot.run();
    }
}