package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.ToDo;

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
