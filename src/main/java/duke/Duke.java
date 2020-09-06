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

    private static final String byeMessage = "bye";

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private boolean canCloseWindow = false;

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
     * Sets the canCloseWindow to true if the response of
     * duke is the exit command.
     *
     * @param userInput The user's input.
     * @return Duke's response.
     */
    public String getResponse(String userInput) {
        try {
            Command command = Parser.parse(userInput);
            String response = command.execute(storage, taskList, ui);
            if (command.isExit()) {
                canCloseWindow = true;
            }
            return response;
        } catch (InvalidInputException e) {
            return e.getMessage();
        }
    }

    /**
     * Checks if the GUI window can be closed, by checking
     * whether the Duke displays the exit message.
     *
     * @return true if the GUI window can be closed, false otherwise.
     */
    public boolean canCloseWindow() {
        return canCloseWindow;
    }


}
