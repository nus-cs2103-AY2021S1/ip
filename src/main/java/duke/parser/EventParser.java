package duke.parser;

import duke.DukeException;
import duke.task.Event;

public class EventParser implements TaskCommandParser {
    private String input;

    /**
     * The constructor method for the EventParser object.
     * @param input the command input for the Event command.
     */
    public EventParser(String input) {
        this.input = input;
    }

    /**
     * Checks if the event command is a valid command.
     * @return the string representing the created event task.
     * @throws DukeException if there are errors in the command.
     */
    public String checkIfValid() throws DukeException {
        int atIndex = input.indexOf(" /at ");
        boolean containsAt = input.contains(" /at ");
        boolean missingDate = true;
        if (atIndex >= 0) {
            missingDate = input.substring(input.indexOf(" /at ")).length() == 5;
        }
        boolean missingTaskDescription = input.contains("event /at ");
        if (missingTaskDescription) {
            throw new DukeException("Where is your event description?!");
        } else if (!containsAt || missingDate) {
            throw new DukeException("Oi, when is this event at??");
        } else {
            try {
                boolean validDateFormat = Event.checkDateFormat(input.substring(atIndex + 5));
                Event task = new Event(input.substring(6, atIndex), input.substring(atIndex + 5));
                return task.toString();
            } catch (DukeException e) {
                throw new DukeException(e.getMessage());
            }
        }
    }
}
