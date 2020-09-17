package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;


/**
 * A chatbot that helps the user keep track of todo, deadline and event tasks
 * by storing them in a list.
 */
public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    /**
     * Constructs a Duke object and initialises the TaskList, Ui and Storage.
     * @param directoryPath location of the save file containing the list of tasks.
     */
    public Duke (String directoryPath) {
        tasks = new TaskList();
        ui = new Ui();
        storage = new Storage(directoryPath);
        try {
            storage.loadList(tasks);
        } catch (Exception e) {
            ui.printError(e);
        }
    }

    /**
     * Saves the current list of tasks into storage.
     */
    public void save() {
        storage.storelist(tasks);
    }

    /**
     * Prints the welcome message.
     * @return A string representing Duke starting up.
     */
    public String welcome() {
        return ui.greet();
    }

    /**
     * Executes the command entered by the user and generates a response.
     * @param input The command entered by the user.
     * @return A string representing the relevant response to the command entered.
     */
    public String getResponse(String input) {
        try {
            Command nextCommand = Parser.parse(input);
            return nextCommand.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.toString();
        }
    }
}
