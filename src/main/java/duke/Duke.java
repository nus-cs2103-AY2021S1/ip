package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initialises Duke.
     *
     * @param filePath the filepath of data.txt.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readFile());
        } catch (DukeException e) {
            ui.displayErrorMessage(e);
            tasks = new TaskList();
        }
    }

    /**
     * Generates the welcome message before user inputs any.
     *
     * @return A welcome message.
     */
    public String welcome() {
        return ui.displayWelcome();
    }


    /**
     * Generates the response message based on user input.
     * Parses users input to generate a command and execute the task.
     *
     * @return A response message.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String output = c.execute(tasks, ui, storage);
            return output;
        } catch (DukeException ex) {
            return ex.getMessage();
        }
    }
}
