package duke.command;

import java.io.IOException;

import duke.Message;
import duke.Storage;
import duke.TaskList;
import duke.exception.DuplicateTaskException;
import duke.exception.InvalidCommandFormatException;
import duke.task.Deadline;

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
