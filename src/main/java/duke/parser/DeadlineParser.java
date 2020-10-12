package duke.parser;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Task;

public class DeadlineParser implements TaskCommandParser {
    private final String input;

    /**
     * The constructor for the DeadlineParser object
     * @param input the input by the user.
     */
    public DeadlineParser(String input) {
        this.input = input;
    }

    /**
     * checks if the deadline command is valid, then returns the string represented the created deadline task. Throws a
     * DukeException if there are errors in the command.
     * @return the String representing the created deadline task.
     * @throws DukeException thrown if there are errors in the command.
     */
    public String checkIfValid() throws DukeException {
        int byIndex = input.indexOf(" /by ");
        boolean containsBy = input.contains(" /by ");
        boolean missingDate = input.substring(input.indexOf(" /by ")).length() == 5;
        boolean missingTaskDescription = input.contains("deadline /by ");
        boolean validDateFormat = Deadline.checkDateFormat(input.substring(byIndex + 5));
        if (missingTaskDescription) {
            throw new DukeException("You aren't setting anything for your deadline?!");
        } else if (!containsBy || missingDate) {
            throw new DukeException("Oi, when is this deadline due??");
        } else if (!validDateFormat) {
            throw new DukeException("Hey, that date is not in the YYYY-mm-dd format!");
        } else {
            Task task = new Deadline(input.substring(9, byIndex), input.substring(byIndex + 5));
            return task.toString();
        }
    }
}
