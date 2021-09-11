package dobby.command;

import java.time.format.DateTimeParseException;

import dobby.DobbyException;
import dobby.TaskList;
import dobby.task.Deadline;

public class DeadlineCommand implements Command {

    @Override
    public String parseInput(TaskList tasks, String text) throws DobbyException {
        String message = "";
        assert text.startsWith("deadline") : "Deadline command must start with deadline";
        try {
            int commandLen = "deadline".length();
            text = text.substring(commandLen + 1).trim();

            if (text.indexOf("/by") < 0 && text.length() >= 1) { // non-empty description and /by missing
                throw new DobbyException("Incorrect usage of command.\nDeadline details cannot be empty. "
                        + "Please try again.");
            } else if (text.indexOf("/by") == 0) { // no description, by given
                throw new DobbyException("Incorrect usage of command.\nDescription cannot be empty. "
                        + "Please try again.");
            }

            String by = text.substring(text.indexOf("/by") + 4).trim();
            String description = text.substring(0, text.indexOf("/by") - 1).trim();
            Deadline deadline = new Deadline(description, by);

            boolean hasTime = by.lastIndexOf(' ') > 0;
            if (hasTime) {
                boolean isCorrectFormat = by.substring(1 + by.lastIndexOf(' ')).length() > 4;
                if (isCorrectFormat) {
                    throw new DobbyException("Incorrect usage of command.\n"
                            + "Details of time should be in 24hr format with only 4 digits. "
                            + "Please try again.");
                }
            }

            tasks.addToList(deadline);
            message = "Great! I've added the following task:\n  " + deadline.getDescription()
                    + String.format("\nNow you have %d task%s in the list.", tasks.getSize(),
                    tasks.getSize() > 1 ? "s" : "");
        } catch (StringIndexOutOfBoundsException e) {
            if (text.startsWith("deadline") || text == null) { // description empty
                throw new DobbyException("Incorrect usage of command.\nDescription cannot be empty. "
                        + "Please try again.");
            } else { // no deadline details specified
                throw new DobbyException("Incorrect usage of command.\nDeadline details cannot be empty. "
                        + "Please try again.");
            }
        } catch (DateTimeParseException e) {
            throw new DobbyException("Incorrect usage of command.\nThe format of the date in incorrect. "
                    + "Please try again.");
        } catch (DobbyException e) { // empty description or /by missing
            return e.getMessage();
        }
        assert message != null : "Return message to user cannot be empty";
        return message;
    }
}
