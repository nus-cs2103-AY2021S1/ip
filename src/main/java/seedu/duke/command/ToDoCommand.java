package seedu.duke.command;

import java.io.IOException;

import seedu.duke.Message;
import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.duke.exception.DuplicateTaskException;
import seedu.duke.exception.InvalidCommandFormatException;
import seedu.duke.task.ToDo;

/**
 * Represents a <code>Command</code> telling Duke to create a new <code>ToDo</code>.
 */
public class ToDoCommand implements Command {
    private String command;

    public ToDoCommand(String command) {
        this.command = command;
    }

    @Override
    public Message execute(TaskList taskList, Storage storage) throws InvalidCommandFormatException, IOException {
        try {
            ToDo toDo = ToDo.of(this.command);
            taskList.add(toDo);
            storage.appendToFile(toDo);
            return Message.getTaskAdded(toDo);
        } catch (DuplicateTaskException e) {
            return new Message(e.getMessage());
        }
    }

    @Override
    public boolean isDone() {
        return false;
    }
}
