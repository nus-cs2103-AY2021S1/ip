package duke;

import duke.command.Command;
import duke.exception.InvalidInputException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;


/**
 * Represents Duke class, which is the control class of Duke and
 * includes the logic of Duke.
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Creates a duke object and initializes storage, taskList and ui.
     */
    public Duke() {
        storage = new Storage("data/tasks.txt", "data");
        taskList = storage.read();
        ui = new Ui(taskList);
    }

    /**
     * Returns the ui object.
     *
     * @return The ui object.
     */
    public Ui getUi() {
        return ui;
    }

    /**
     * Returns duke's response based on the user's input.
     *
     * @param userInput The user's input.
     * @return Duke's response.
     */
    public String getResponse(String userInput) {
        try {
            Command command = Parser.parse(userInput);
            return command.execute(storage, taskList, ui);
        } catch (InvalidInputException e) {
            return e.getMessage();
        }
    }


}
