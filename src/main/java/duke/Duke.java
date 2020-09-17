package duke;

import java.io.IOException;

import duke.exception.InvalidDataException;
import duke.exception.InvalidTypeException;
import duke.misc.Parser;
import duke.misc.TaskList;

public class Duke {

    private TaskList taskList;

    /**
     * Constructor for Duke class
     */
    public Duke() {
        taskList = new TaskList();
    }

    /**
     * Function to get Duke's response to user inputs.
     *
     * @param input User input.
     * @return A string representing Duke's response.
     */
    public String getResponse(String input) {
        if (!taskList.isInitialised()) {
            try {
                taskList.initialise();
            } catch (IOException e) {
                return e.toString();
            } catch (InvalidTypeException e) {
                return e.toString();
            } catch (InvalidDataException e) {
                return e.toString();
            }
        }
        assert taskList.isInitialised();
        return Parser.allocate(input, taskList);
    }
}
