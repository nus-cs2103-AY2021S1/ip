package duke;

import java.io.IOException;

import duke.command.Command;
import duke.exception.DukeException;
import duke.tool.Parser;
import duke.tool.TaskList;
import duke.ui.Ui;


/**
 * Represents the duke system.
 */
public class Duke {

    /** Storage of the system */
    private final Storage storage;

    /** Task list that stores tasks */
    private TaskList tasks;

    /** User interface to interact with user */
    private  Ui ui = null;

    /**
     * Creates new Duke chat bot from given storage path.
     *
     * @param filePath Data file path.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
            //ui = new Ui(storage, tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Duke() {
        this("data/duke.txt");
    }


    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, storage);

            return c.getResponse();
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
