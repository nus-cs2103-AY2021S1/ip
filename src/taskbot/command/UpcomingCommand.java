package taskbot.command;

import taskbot.task.TaskList;
import taskbot.ui.Ui;

public class UpcomingCommand extends Command {
    public int days;

    public UpcomingCommand(int days) {
        super(false);
        this.days = days;
    }

    public int getDays() {
        return days;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.getUpcoming(days);
    }

    @Override
    public boolean equals(Object obj) {
        // Check if obj is compared with itself
        if (obj == this) {
            return true;
        }

        // Check if obj is an instance of this class
        if (!(obj instanceof UpcomingCommand)) {
            return false;
        }

        // Compare taskIndex and return accordingly
        return days == ((UpcomingCommand) obj).getDays();
    }
}
