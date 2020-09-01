package Command;

import DukeComponent.Ui;
import TaskList.TaskList;
import Tasks.Task;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        super(CommandType.ADD);
        this.task = task;
    }

    @Override
    public String act(TaskList list) {
        list.addTask(task);
        return Ui.addTaskMessage(task, list.size());
    }
}
