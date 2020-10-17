package duke.parser;

import duke.DukeException;
import duke.storage.TaskList;

public class DeleteParser implements IndexCommandParser {
    private final String input;
    private TaskList lines;

    /**
     * The constructor for the DeleteParser object.
     * @param input the command string.
     * @param lines the TaskList to be used for reference.
     */
    public DeleteParser(String input, TaskList lines) {
        this.input = input;
        this.lines = lines;
    }

    /**
     * Checks if the delete command is valid.
     * @return the index of the task as mentioned in the command.
     * @throws DukeException if the command or index is invalid, a DukeException describing the error is thrown.
     */
    public int checkIfValid() throws DukeException {
        try {
            int itemNumber = Integer.parseInt(input.substring(input.indexOf(" ") + 1)); // The task index in the list
            boolean invalidIndex = lines.getNumberOfItems() < itemNumber || itemNumber <= 0;
            if (input.length() <= 7) { // This condition is to check if the delete command is empty
                throw new DukeException("You did not specify which task you are deleting!");
            } else if (invalidIndex) { // This condition is to check if the index is within the lines TaskList
                throw new DukeException("Hey, no such task exists!");
            } else {
                return itemNumber;
            }
        } catch (NumberFormatException e) { // Thrown by parseInt
            throw new DukeException("Invalid index input for the delete command!");
        }
    }
}
