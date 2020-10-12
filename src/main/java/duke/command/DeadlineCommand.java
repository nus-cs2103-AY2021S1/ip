package duke.command;

import java.io.IOException;

import duke.exception.DuplicateTaskException;
import duke.exception.InvalidCommandFormatException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Message;

/**
 * Represents a <code>Command</code> telling Duke to create a new <code>Deadline</code>.
 */
public class DeadlineCommand implements Command {
    private String command;

    public DeadlineCommand(String command) {
        this.command = command;
    }

    @Override
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

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof DeadlineCommand) {
            return this.command.equals(((DeadlineCommand) obj).command);
        } else {
            return false;
        }
    }
}
