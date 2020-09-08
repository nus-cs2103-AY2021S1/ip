package dobby.command;

import java.time.format.DateTimeParseException;

import dobby.DobbyException;
import dobby.TaskList;
import dobby.task.Event;

public class EventCommand implements Command {

    protected static final String USAGE = "event _description_ /at dd/mm/yyyy hhhh(optional)";

    @Override
    public String parseInput(TaskList tasks, String text) throws DobbyException {
        String message;
        assert text.startsWith("event") : "Event command must start with event";
        try {
            int commandLen = "event".length();
            text = text.substring(commandLen + 1).trim();

            if (text.indexOf("/at") < 0 && text.length() >= 1) { // non-empty description and /by missing
                throw new DobbyException("Incorrect usage of command.\nSchedule details cannot be empty. "
                        + "Please try again.\n  " + USAGE);
            } else if (text.indexOf("/at") == 0) { // no description, by given
                throw new DobbyException("Incorrect usage of command.\nDescription cannot be empty. "
                        + "Please try again.\n  " + USAGE);
            }

            String at = text.substring(text.indexOf("/at") + 4).trim();
            String description = text.substring(0, text.indexOf("/at") - 1).trim();
            Event event = new Event(description, at);

            if (at.lastIndexOf(' ') > 0) {
                if (at.substring(1 + at.lastIndexOf(' ')).length() > 4) {
                    throw new DobbyException("Incorrect usage of command.\n"
                            + "Details of time should be in 24hr format with only 4 digits. Please try again.\n  "
                            + USAGE);
                }
            }

            tasks.addToList(event);
            message = "Great! I've added the following task:\n  " + event.getDescription()
                    + String.format("\nNow you have %d task%s in the list.", tasks.getSize(),
                    tasks.getSize() > 1 ? "s" : "");
        } catch (StringIndexOutOfBoundsException e) {
            if (text.startsWith("event") || text == null) { // description empty
                throw new DobbyException("Incorrect usage of command.\nDescription cannot be empty. "
                        + "Please try again.\n  " + USAGE);
            } else { // no schedule details specified
                throw new DobbyException("Incorrect usage of command.\nSchedule details cannot be empty. "
                        + "Please try again.\n  " + USAGE);
            }
        } catch (DateTimeParseException e) {
            throw new DobbyException("Incorrect usage of command.\nThe format of the date in incorrect. "
                    + "Please try again.\n  " + USAGE);
        } catch (DobbyException e) { // empty description or /at missing
            return e.getMessage();
        }
        assert message != null : "Return message to user cannot be empty";
        return message;
    }
}
