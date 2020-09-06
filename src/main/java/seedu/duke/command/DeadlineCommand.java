package seedu.duke.command;

import java.io.IOException;

import seedu.duke.Message;
import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.duke.exception.DuplicateTaskException;
import seedu.duke.exception.InvalidCommandFormatException;
import seedu.duke.task.Deadline;

/**
 * Represents a <code>Command</code> telling Duke to create a new <code>Deadline</code>.
 */
public class DeadlineCommand implements Command {
    private String command;

    public DeadlineCommand(String command) {
        this.command = command;
    }

    public Message execute(TaskList taskList, Storage storage) throws InvalidCommandFormatException, IOException {
        try {
            Deadline deadline = Deadline.of(this.command);
            taskList.add(deadline);
            storage.appendToFile(deadline);
            return Message.getTaskAdded(deadline);
        } catch (DuplicateTaskException e) {
            return new Message(e.getMessage());
        }

    }

    public boolean isDone() {
        return false;
    }
}
