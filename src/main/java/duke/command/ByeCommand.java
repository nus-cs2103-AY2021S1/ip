package duke.command;

import duke.Message;
import duke.Storage;
import duke.TaskList;

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
