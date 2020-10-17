package duke.parser;

import duke.DukeException;
import duke.storage.TaskList;


public class DoneParser implements IndexCommandParser {
    private final String input;
    private TaskList lines;

    /**
     * The constructor for the DoneParser object. It takes in a String and a TaskList in order to parse the String's
     * command.
     * @param input The command to be input
     * @param lines The TaskList to be manipulated
     */
    public DoneParser(String input, TaskList lines) {
        this.input = input;
        this.lines = lines;
    }

    /**
     * Checks if the input is a valid "done" command. Returns the integer in the command if it is a valid task index,
     * and throws DukeExceptions if otherwise.
     * @return A valid task index
     * @throws DukeException if there are errors in the command
     */
    public int checkIfValid() throws DukeException {
        try {
            int itemNumber = Integer.parseInt(input.substring(input.indexOf(" ") + 1));
            boolean invalidIndex = lines.getNumberOfItems() < itemNumber || itemNumber <= 0;
            if (input.length() <= 5) { // This condition is to check if the done command is empty
                throw new DukeException("You did not specify which task you are done with!");
            } else if (invalidIndex) { // This condition is to check if the index is within lines.
                throw new DukeException("Hey, no such task exists!");
            } else {
                return itemNumber;
            }
        } catch (NumberFormatException e) { // Thrown by parseInt which signifies
            throw new DukeException("Invalid index input for the done command!");
        }
    }
}
