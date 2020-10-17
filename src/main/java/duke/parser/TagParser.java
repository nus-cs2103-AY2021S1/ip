package duke.parser;

import duke.DukeException;
import duke.storage.TaskList;

public class TagParser implements IndexCommandParser {
    private String input;
    private TaskList lines;

    /**
     * The constructor method for the TagParser object.
     * @param input the tag command by the user.
     * @param lines the TaskList to be checked.
     */
    public TagParser(String input, TaskList lines) {
        this.input = input;
        this.lines = lines;
    }

    /**
     * Checks if the tag command is valid and throws a DukeException explaining the error if otherwise.
     * @return the index, which is valid, as written in the input command by the user.
     * @throws DukeException if there are errors in the command.
     */
    public int checkIfValid() throws DukeException {
        boolean missingTagDetails = input.length() <= 6
                || input.length() == 7 && input.lastIndexOf(" ") == 6;
        if (missingTagDetails) {
            throw new DukeException("Hey, your tag command is missing details! Check it again!");
        } else {
            try {
                return checkAndGetIndex();
            } catch (DukeException e) {
                throw new DukeException(e.getMessage());
            }
        }
    }

    /**
     * Checks if the index exists in TaskList.
     * @param index the index to be checked.
     * @return true if its valid, false otherwise.
     */
    public boolean checkValidIndex(int index) {
        return index >= 0 && index <= lines.getList().size();
    }

    /**
     * Checks if the command contains a single or double digit index and extracts it, throwing a DukException if there
     * are errors in the command.
     * @return The index.
     * @throws DukeException if there are errors in the command.
     */
    public int checkAndGetIndex() throws DukeException {
        String taskIndex = input.substring(4, 6);
        boolean singleDigit = taskIndex.contains(" ");
        if (singleDigit) { // if the tag index is a single digit
            try {
                int index = Integer.parseInt(taskIndex.substring(0, 1));
                if (!checkValidIndex(index)) {
                    throw new DukeException("No such task exists!");
                }
                return index;
            } catch (NumberFormatException e) { // Thrown by parseInt
                throw new DukeException("That is not a valid index input!");
            }
        } else {
            try {
                int index = Integer.parseInt(taskIndex.substring(0, 2));
                if (!checkValidIndex(index)) {
                    throw new DukeException("No such task exists!");
                }
                return index;
            } catch (NumberFormatException e) {
                throw new DukeException("That is not a valid index input!");
            }
        }
    }

    /**
     * Obtains the description of the tag in the command.
     * @param index the index written inside the command.
     * @return the description in the tag command.
     */
    public String getDescription(int index) {
        if (index < 10) { // Checks if its a double digit index or single digit.
            return input.substring(6);
        } else {
            return input.substring(7);
        }
    }
}
