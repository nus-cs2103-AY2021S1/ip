package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Message;

/**
 * Represents a <code>Command</code> telling Duke to stop running.
 */
public class ByeCommand implements Command {
    public Message execute(TaskList taskList, Storage storage) {
        return Message.getGoodbye();
    }

    public boolean isDone() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ByeCommand;
    }
}
