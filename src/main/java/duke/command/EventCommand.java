package duke.command;

import java.io.IOException;

import duke.Message;
import duke.Storage;
import duke.TaskList;
import duke.exception.DuplicateTaskException;
import duke.exception.InvalidCommandFormatException;
import duke.task.Event;

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
            Event event = Event.of(this.command);
            taskList.add(event);
            storage.appendToFile(event);
            return Message.getTaskAdded(event);
        } catch (DuplicateTaskException e) {
            return new Message(e.getMessage());
        }
    }

    @Override
    public boolean isDone() {
        return false;
    }
}
