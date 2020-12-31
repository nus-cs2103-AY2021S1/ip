package duke;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * Represents a Duke bot.
 */
public class Duke {

    /**
     * The Storage associated with the Duke object.
     */
    private Storage storage;

    /**
     * The TaskList associated with the Duke object.
     */
    private TaskList tasks;

    /**
     * The Ui associated with the Duke object.
     */
    private Ui ui;


    /**
     * Creates a new instance of a Duke object with attributes defined
     * in the parameters.
     * Initializes the Ui, Storage and TaskList.
     *
     * @param filePath Path of the file where the data is written to.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.print(e.getMessage());
        }
    }

    /**
     * Creates a new instance of a Duke object with
     * a predefined file path.
     * Initializes the Ui, Storage and TaskList.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.print(e.getMessage());
        }
    }

    /**
     * Runs the bot.
     * Operations are executed based on the input.
     */
    public void run() {
        Ui ui = new Ui();
        ui.greet();
        boolean isRunning = true;
        while (isRunning) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isRunning = c.isRunning();
            } catch (DukeException e) {
                ui.print(e.getMessage());
            }
        }
    }

    /**
     * Gets response for a user input.
     *
     * @param input User input.
     * @return Returns a Duke response as a String.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.print(e.getMessage());
        }
    }


    /**
     * Runs the program.
     */
    public static void main(String[] args) {
        Duke duke = new Duke("data/tasks.txt");
        duke.run();
    }
}
