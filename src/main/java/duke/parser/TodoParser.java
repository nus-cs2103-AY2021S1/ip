package duke.parser;

import duke.DukeException;
import duke.task.Task;
import duke.task.Todo;

public class TodoParser implements TaskCommandParser {
    private final String input;

    /**
     * The constructor method for the TodoParser object.
     * @param input the user's input.
     */
    public TodoParser(String input) {
        this.input = input;
    }

    /**
     * Checks if the task command is valid, then returns the task as a string if it is and throw a DukeException if
     * errors occur.
     * @return a String representing the created task.
     * @throws DukeException if there are errors in the input command.
     */
    public String checkIfValid() throws DukeException {
        boolean emptyDescription = input.length() == 5;
        if (emptyDescription) { // Checks if there is an input for the task command.
            throw new DukeException("Hey! Your Todo is empty >:(");
        } else if (input.indexOf(" ") != 4) { // Checks if the input is invalid.
            throw new DukeException("What are you even saying?!");
        } else {
            Task task = new Todo(input.substring(5));
            return task.toString();
        }
    }
}
