package duke.command;

import duke.TaskList;
import duke.Storage;
import duke.Ui;
import duke.task.ToDo;

/**
 * The TodoCommand class contains methods pertaining to the TodoCommand.
 */
public class TodoCommand extends Command {

    public ToDo todo;

    public TodoCommand(ToDo todo) {
        this.todo = todo;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        taskList.addTask(todo);
        ui.addTask(todo, taskList);
        storage.addData(todo.store());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
