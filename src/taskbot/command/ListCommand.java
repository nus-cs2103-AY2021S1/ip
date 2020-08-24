package taskbot.command;

import taskbot.task.TaskList;
import taskbot.ui.Ui;

public class ListCommand extends Command {
    public ListCommand() {
        super(false);
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.listTasks();
    }
}
