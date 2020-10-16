package duke.parser;

import duke.DukeException;
import duke.TaskList;

public class UntagParser implements IndexCommandParser {
    private String input;
    private TaskList lines;

    /**
     * The constructor for the UntagParser object.
     * @param input the user input.
     * @param lines the TaskList to be checked.
     */
    public UntagParser(String input, TaskList lines) {
        this.input = input;
        this.lines = lines;
    }

    /**
     * Checks if the index in the untag command is valid and returns it.
     * @return the index specified in the command.
     * @throws DukeException if there are errors in the command.
     */
    public int checkIfValid() throws DukeException {
        boolean missingIndex = input.length() <= 6;
        if (missingIndex) {
            throw new DukeException("Which task are you trying to untag?");
        } else {
            try {
                int index = Integer.parseInt(input.substring(6));
                if (index <= 0 || index > lines.getList().size()) { // Checks if the index exists in the TaskList.
                    throw new DukeException("No such task exists!");
                }
                return index;
            } catch (NumberFormatException e) { // Thrown by parseInt.
                throw new DukeException("What kind of task index is that?!");
            }
        }
    }
}
