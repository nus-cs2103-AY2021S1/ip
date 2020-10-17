package duke.parser;

import duke.DukeException;
import duke.task.Deadline;

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
        boolean missingDate = true;
        if (byIndex >= 0) {
            missingDate = input.substring(input.indexOf(" /by ")).length() == 5;
        }
        boolean missingTaskDescription = input.contains("deadline /by ");
        if (missingTaskDescription) {
            throw new DukeException("Where is the description of your deadline?!");
        } else if (!containsBy || missingDate) {
            throw new DukeException("Oi, when is this deadline due??");
        } else {
            try {
                boolean validDateFormat = Deadline.checkDateFormat(input.substring(byIndex + 5));
                Deadline task = new Deadline(input.substring(9, byIndex), input.substring(byIndex + 5));
                return task.toString();
            } catch (DukeException e) {
                throw new DukeException(e.getMessage());
            }
        }
    }
}
