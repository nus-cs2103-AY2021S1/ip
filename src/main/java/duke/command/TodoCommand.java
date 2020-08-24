package duke.command;

import duke.TaskList;
import duke.Storage;
import duke.Ui;
import duke.task.ToDo;

public class TodoCommand extends Command {
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        ToDo todo = ui.getToDo();
        taskList.addTask(todo);
        ui.addTask(todo, taskList);
        storage.addData(todo.store());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
