package duke;

import duke.command.Command;
import duke.util.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Duke is a personal task management bot which users will interact with through
 * a messaging interface.
 * @author Andy Wu
 */
public class Duke {

    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    /**
     * Constructs the bot with a given file path for the txt file.
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

    /**
     * Constructs the bot.
     */
    public Duke() {
        TaskList temp;
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
        try {
            temp = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.sendMessage(e.getMessage());
            temp = new TaskList();
        }
        tasks = temp;
    }

    /**
     * Runs the bot by continuously reading user inputs,
     * responding to the input, and exiting only when
     * an exit signal has been given.
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

    /**
     * Gets the response message of the bot based on the user input.
     * @param input the user input.
     * @return the response message.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Gets the welcome message.
     * @return the message.
     */
    public String getWelcomeMessage() {
        return ui.getGuiWelcomeMessage();
    }

    /**
     * Driver to run the application in command line interface (CLI).
     * @param args optional and will be treated as the first user input.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}