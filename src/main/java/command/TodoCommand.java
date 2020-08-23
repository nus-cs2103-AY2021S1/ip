package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;
import task.Task;
import task.TodoTask;

public class TodoCommand extends Command {
    String todo;

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task todoTask = new TodoTask(this.todo);
        taskList.addTask(todoTask);
        ui.showAdd(todoTask, taskList.getSize());
    }

    public TodoCommand(String todo) {
        super(CommandType.Todo);
        this.todo = todo;
    }

}
