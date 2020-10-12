package duke.logic;

import duke.exception.DukeException;
import duke.model.Todo;

public class TodoCommandParser {
    /**
     * Parse Todo command.
     * @param input the input from the user.
     * @return a Todo object;
     * @throws DukeException handles the exception when running the Duke bot.
     */
    public static Todo run(String input) throws DukeException {
        if (input.length() <= 5) {
            // Exception: eg. todo
            throw new DukeException("      OOPS!!! The description of a todo cannot be empty.");
        }
        Todo task = new Todo(input.substring(5));
        return task;
    }
}
