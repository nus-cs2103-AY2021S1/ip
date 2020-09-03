package dobby.command;

import java.time.format.DateTimeParseException;

import dobby.DobbyException;
import dobby.TaskList;
import dobby.task.Deadline;

public class DeadlineCommand implements Command {

    protected static final String USAGE = "deadline _description_ /by dd/mm/yyyy hhhh(optional)";

    @Override
    public String parseInput(TaskList tasks, String text) throws DobbyException {
        String message = "";
        String by = "";
        try {
            text = text.substring(9).trim();

            if (text.indexOf("/by") < 0 && text.length() >= 1) { // non-empty description and /by missing
                throw new DobbyException("Incorrect usage of command.\nDeadline details cannot be empty. "
                        + "Please try again.\n  " + USAGE);
            } else if (text.indexOf("/by") == 0) { // no description, by given
                throw new DobbyException("Incorrect usage of command.\nDescription cannot be empty. "
                        + "Please try again.\n  " + USAGE);
            }

            by = text.substring(text.indexOf("/by") + 4).trim();
            text = text.substring(0, text.indexOf("/by") - 1).trim();
            Deadline deadline = new Deadline(text, by);

            if (by.substring(1 + by.lastIndexOf(' ')).length() > 4) {
                throw new DobbyException("Incorrect usage of command.\n"
                        + "Details of time should be in 24hr format with only 4 digits. "
                        + "Please try again.\n  " + USAGE);
            }
            tasks.addToList(deadline);
            message = "Great! I've added the following task:\n  " + deadline.getDescription()
                    + String.format("\nNow you have %d task%s in the list.", tasks.getSize(),
                    tasks.getSize() > 1 ? "s" : "");
        } catch (StringIndexOutOfBoundsException e) {
            if (text.startsWith("deadline") || text == null) { // description empty
                throw new DobbyException("Incorrect usage of command.\nDescription cannot be empty. "
                        + "Please try again.\n  "
                        + USAGE);
            } else { // no deadline details specified
                throw new DobbyException("Incorrect usage of command.\nDeadline details cannot be empty. "
                        + "Please try again.\n  "
                        + USAGE);
            }
        } catch (DateTimeParseException e) {
            throw new DobbyException("Incorrect usage of command.\nThe format of the date in incorrect. "
                    + "Please try again.\n  "
                    + USAGE);
        } catch (DobbyException e) { // empty description or /by missing
            return e.getMessage();
        }
        return message;
    }
}
