package duke.command;

import duke.task.ToDo;
import duke.task.Task;
import duke.storage.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

import java.util.ArrayList;

public class TodoCommand extends Command {

    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> lib = tasks.getTaskList();

        ToDo todo = new ToDo(description);
        tasks.addTodo(todo);
        ui.printAddStatements(todo.toString(), lib.size());
    }

    @Override
    public boolean isDone() {
        return false;
    }

}
