package duke.command;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.exception.DuplicateTaskException;
import duke.exception.InvalidCommandFormatException;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.TaskList;
import duke.ui.Message;

/**
 * Represents a <code>Command</code> telling Duke to create a new <code>Event</code>.
 */
public class EventCommand implements Command {
    private String command;

    public EventCommand(String command) {
        this.command = command;
    }

    @Override
    public Message execute(TaskList taskList, Storage storage) throws InvalidCommandFormatException, IOException {
        try {
            Event event = createEvent();
            taskList.add(event);
            storage.appendToFile(event);
            return Message.getTaskAdded(event);
        } catch (DuplicateTaskException e) {
            return new Message(e.getMessage());
        }
    }

    Event createEvent() throws InvalidCommandFormatException {
        if (command.length() <= 6) {
            throw new InvalidCommandFormatException("Event cannot be empty.");
        }
        String[] split = command.substring(6).trim().split("\\s+/at\\s+");
        if (split.length != 2) {
            throw new InvalidCommandFormatException("Wrong format for event command.");
        }
        try {
            return new Event(split[0], LocalDate.parse(split[1]));
        } catch (DateTimeParseException e) {
            throw new InvalidCommandFormatException("Please enter a valid date in the yyyy-mm-dd format.");
        }
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof EventCommand) {
            return this.command.equals(((EventCommand) obj).command);
        } else {
            return false;
        }
    }
}
