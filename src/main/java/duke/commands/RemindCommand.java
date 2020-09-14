package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.tasklist.TaskList;
import duke.tasklist.tasklistfilter.TaskListFilter;
import duke.tasklist.tasklistfilter.WeekFilter;

/**
 * Display deadlines/events that occur this week..
 */
public class RemindCommand implements Command {

    /**
     * Displays deadlines/events that occur this week.
     *
     * @param tasks   TaskList.
     * @param ui      Ui.
     * @param storage Storage.
     * @return The reminder message by the Ui.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskListFilter weekFilter = new WeekFilter();
        return ui.currentWeekTasks(tasks.findTasks(weekFilter));
    }
}
