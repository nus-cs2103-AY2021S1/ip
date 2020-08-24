package taskbot.command;

import taskbot.task.TaskList;
import taskbot.ui.Ui;

public class TodoCommand extends Command {
    private String task;
    public TodoCommand(String task) {
        super(false);
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.addTodoTask(task);
    }
}
