package duke.command;

import java.util.ArrayList;

import duke.storage.Storage;
import duke.storage.TaskList;
import duke.task.Task;
import duke.task.ToDo;
import duke.ui.Ui;

/**
 * Encapsulates a Command which creates a new ToDo task to add to the task
 * list.
 */
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
