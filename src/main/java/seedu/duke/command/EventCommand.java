package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.Message;
import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.duke.task.Event;

/**
 * Represents a <code>Command</code> telling Duke to create a new <code>Event</code>.
 */
public class EventCommand implements Command {
    private String command;

    public EventCommand(String command) {
        this.command = command;
    }

    public Message execute(TaskList taskList, Storage storage) throws DukeException {
        Event event = Event.of(this.command);
        taskList.add(event);
        storage.appendToFile(event);
        return Message.getTaskAdded(event);
    }

    public boolean isDone() {
        return false;
    }
}
