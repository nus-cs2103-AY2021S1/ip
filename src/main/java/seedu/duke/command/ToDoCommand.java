package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.duke.Ui;
import seedu.duke.task.ToDo;

/**
 * Represents a <code>Command</code> telling Duke to create a new <code>ToDo</code>.
 */
public class ToDoCommand implements Command {
    String command;

    public ToDoCommand(String command) {
        this.command = command;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ToDo toDo = ToDo.of(this.command);
        taskList.add(toDo);
        storage.appendToFile(toDo);
        ui.showTaskAdded(toDo);
    }

    public boolean isDone() {
        return false;
    }
}
