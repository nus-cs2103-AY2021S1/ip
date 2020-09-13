package dd;

import java.io.IOException;

import dd.commands.Command;
import dd.exception.DukeException;
import dd.parser.Parser;
import dd.storage.DataStorage;
import dd.tasks.TaskList;
import dd.ui.Ui;

/**
 * The main Duke class to create and manage a task list.
 */
public class Duke {

    private DataStorage ds;
    private Ui ui;
    private TaskList tasks;

    /**
     * Class Constructor.
     */
    public Duke() {
        this.ds = new DataStorage();
        this.ui = new Ui();
    }

    /**
     * Indicates results from loading data.
     *
     * @return String to indicate results from loading data.
     */
    public String initializeDuke() {
        String output;
        try {
            tasks = new TaskList(ds.loadData());
            output = ds.getLoadResults();
        } catch (IOException e) {
            output = ui.showLoadingError();
            tasks = new TaskList();
        }

        return output;
    }

    /**
     * Prints greeting to user.
     *
     * @return starting greeting of the system.
     */
    public String sendGreeting() {
        return ui.greeting();
    }

    /**
     * Takes in user input, parses into a command and executes
     * the next command till an exit command is given.
     *
     * @param input User input
     * @return Reply to user based on the input.
     */
    public String getResponse(String input) {
        String ddReply;

        try {
            Command c = Parser.parse(input);
            ddReply = c.execute(tasks, ui, ds);
        } catch (DukeException e) {
            ddReply = ui.showError(e.getMessage());
        }

        return ddReply;
    }
}
