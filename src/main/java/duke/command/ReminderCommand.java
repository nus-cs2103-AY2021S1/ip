package duke.command;

import duke.task.TaskList;

import duke.ui.Ui;

public class ReminderCommand implements Command {
    /**
     * Prints a list of deadlines/events coming in the week.
     *
     * @param tasks List of user's tasks.
     * @param ui UI of Duke.
     * @return String representing list of upcoming tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        TaskList upcomingTasks = tasks.getUpcomingTasks();
        return ui.displayUpcomingTasks(upcomingTasks);
    }

    /**
     * Tells Duke to keep on keeping on.
     *
     * @return true.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

