package duke.command;

import duke.*;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.Todo;

public class TodoCommand extends Command{

    String command;

    public TodoCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = new Todo(command);
        taskList.addTask(task);
        ui.printAddTask(taskList);
        storage.save(task);
    }
}
