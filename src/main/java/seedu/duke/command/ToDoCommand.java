package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.Message;
import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.duke.task.ToDo;

/**
 * Represents a <code>Command</code> telling Duke to create a new <code>ToDo</code>.
 */
public class ToDoCommand implements Command {
    private String command;

    public ToDoCommand(String command) {
        this.command = command;
    }

    public Message execute(TaskList taskList, Storage storage) throws DukeException {
        ToDo toDo = ToDo.of(this.command);
        taskList.add(toDo);
        storage.appendToFile(toDo);
        return Message.getTaskAdded(toDo);
    }

    public boolean isDone() {
        return false;
    }
}
