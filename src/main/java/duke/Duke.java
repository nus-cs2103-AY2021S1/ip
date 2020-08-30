package duke;

import duke.command.Command;
import duke.exception.DukeException;

/** The duke bot that stores your tasks. */
public class Duke {

    /** Deals with interaction with the user. */
    private final Ui ui;
    /** Deals with saving and loading the tasks from the file. */
    private final Storage storage;
    /** Contains the task list and add/delete operations for the task list. */
    private TaskList tasks;

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

    /** Constructs a Duke bot with the default save location (data/duke.txt). */
    public Duke() {
        this("data");
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

    /** Runs the program. */
    public static void main(String[] args) {
        Duke bot = new Duke("data");
        bot.run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
