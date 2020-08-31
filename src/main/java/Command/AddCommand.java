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
    public void act(TaskList list) {
        list.addTask(task);
        Ui.addTaskMessage(task, list.size());
    }
}
