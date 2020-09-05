package duke.gui;

import duke.command.Command;
import duke.util.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Duke is a personal chat bot with the following functionalities:
 *     (i) Adding and removing of Tasks to a list
 *     (ii) Checking Tasks as completed
 *     (iii) Viewing current task list
 *     (iv) Searching for tasks using query string
 *
 * @author Andy Wu
 */
public class Duke {

    /** List which stores the Tasks. */
    private final TaskList tasks;

    /** Reads and writes the txt file. */
    private final Storage storage;

    /** In charge of all user interface related operations */
    private final Ui ui;

    /**
     * Constructor for initializing the bot.
     * A file path is required to store the task list.
     * @param filePath the path of the txt file.
     */
    public Duke(String filePath) {
        TaskList temp;
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            temp = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.sendMessage(e.getMessage());
            temp = new TaskList();
        }
        tasks = temp;
    }

    public Duke() {
        TaskList temp;
        ui = new Ui();
        storage = new Storage("data/duke.txt");
        try {
            temp = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.sendMessage(e.getMessage());
            temp = new TaskList();
        }
        tasks = temp;
    }

    /**
     * THIS METHOD IS FOR THE CLI INTERFACE.
     * The main algorithm of the bot which runs indefinitely as long as
     * the running flag is true. The algorithm is summarized as:
     *
     *     0. Show the welcome message
     *     1. Read raw user input
     *     2. Parse user input and create the appropriate command
     *     3. Execute the command
     *     4. Repeat 1-3 until an exit command
     *     5. Show the exit message
     *
     * Throughout the process, DukeExceptions may be created but are
     * caught and handled by the respective classes.
     */
    public void run() {
        ui.showWelcome();
        boolean isRunning = true;
        while (isRunning) {
            String input = ui.readCommand();
            try {
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                isRunning = !c.isExit();
            } catch (DukeException e) {
                ui.sendMessage(e.getMessage());
            }
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public String getWelcomeMessage() {
        return ui.getGuiWelcomeMessage();
    }

    /**
     * Driver to create the chat bot object and run it in CLI mode.
     * @param args optional and will be treated as the first user input.
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}