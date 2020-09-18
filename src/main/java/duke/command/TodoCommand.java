package duke.command;

import duke.command.Command;
import duke.task.Todo;
import duke.task.TaskList;
import duke.Ui;
import duke.Storage;

public class TodoCommand extends Command {
    String desc;

    public TodoCommand(String desc) {
        this.desc = desc;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Todo todo = new Todo(desc);
        taskList.addTask(todo);
        return (ui.showAddTask(todo) + ui.showNumberOfTasksLeft(taskList));
    }

    public boolean isExit() {
        return false;
    }
}
