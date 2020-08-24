package taskbot.command;

import taskbot.task.TaskList;
import taskbot.ui.Ui;

public class UpcomingCommand extends Command {
    public int days;
    public UpcomingCommand(int days) {
        super(false);
        this.days = days;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.getUpcoming(days);
    }
}
