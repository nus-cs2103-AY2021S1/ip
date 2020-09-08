package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.tasklist.tasklistfilter.SearchFilter;
import duke.tasklist.TaskList;
import duke.tasklist.tasklistfilter.TaskListFilter;
import duke.tasklist.tasklistfilter.WeekFilter;

import java.time.LocalDateTime;

/**
 * Handles the filtering of tasks using a keyword in the chatbot.
 */
public class RemindCommand implements Command {

    /**
     * Displays the filtered list of tasks with the keyword.
     * @param tasks TaskList.
     * @param ui Ui.
     * @param storage Storage.
     * @return The find message by the Ui.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskListFilter weekFilter = new WeekFilter();
        String intro = ui.intro();
        return intro + '\n' + ui.currentWeekTasks(tasks.findTasks(weekFilter));
    }
}
