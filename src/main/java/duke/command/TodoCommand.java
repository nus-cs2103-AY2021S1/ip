package duke.command;

import duke.task.Todo;
import duke.task.TaskList;
import duke.Ui;
import duke.Storage;

/**
 * Command for adding a new <code>Todo</code> task to a <code>TaskList</code>.
 */
public class TodoCommand extends Command {
    String desc;

    public TodoCommand(String desc) {
        this.desc = desc;
    }

    public String execute(TaskList taskList, Storage storage) {
        Todo todo = new Todo(desc);
        taskList.addTask(todo);
        return (Ui.showAddTask(todo) + Ui.showNumberOfTasksLeft(taskList));
    }

    public boolean isExit() {
        return false;
    }
}
