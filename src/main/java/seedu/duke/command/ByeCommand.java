package seedu.duke.command;

import seedu.duke.Message;
import seedu.duke.Storage;
import seedu.duke.TaskList;

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
}
