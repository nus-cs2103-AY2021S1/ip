package duke;

import duke.command.*;
import duke.exception.*;

/**
 * Represents the chat bot itself. Main class.
 */
public class Duke {
    private final Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Class constructor.
     *
     * @param filePath A string representing the destination file path.
     */
    public Duke(String filePath) {
        ui = new Ui();
        try {
            this.storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.printGeneralChatWindow(e.toString());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the program. Main method.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    /**
     * Starts the chat bot, continuously takes in user input and executes the relevant command.
     */
    public void run() {
        // Initial greeting, prompt user for commands
        ui.printWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String input = ui.readInput();
                ui.printBorder(); // Print top border
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printGeneralChatWindow(e.toString());
            } finally {
                ui.printBorder(); // Print bottom border
            }
        }

        ui.printLogo();
    }
}
