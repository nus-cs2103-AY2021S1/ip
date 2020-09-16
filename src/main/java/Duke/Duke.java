package duke;

import command.Command;

/**
 * Duke is the main class that runs the Spanish Duke program.
 *
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
     *
     * @param filePath the file path where data is stored.
     */
    public Duke(String filePath) throws DukeException {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load(ui));
    }

    /**
     * Gets a response from what was input by the user.
     *
     * @return the output from Duke.
     */
    public String getResponse(String input) throws DukeException {
        String output;
        Command c = Parser.parse(input);
        output = c.execute(tasks, ui, storage);
        return output;
    }
}
