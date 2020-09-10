package duke.logic.commands;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import duke.storage.Storage;
import duke.storage.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.ui.Ui;

public class UpcomingTasksCommand extends Command {
    /**
     * Shows the tasks that are upcoming in a week.
     *
     * @param tasks duke.tasks.Task list of all tasks.
     * @param ui duke.ui.Ui to deal with interaction with user.
     * @param storage duke.storage.Storage to load and save tasks.
     * @return The string message showing upcoming tasks if there are any, in a week.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        LocalDateTime now = LocalDateTime.now();
        String upcomingTasks = "";
        int numUpcoming = 0;

        for (int i = 0; i < tasks.getNumTasks(); i++) {
            Task current = tasks.getTask(i + 1);
            if (current instanceof Event) {
                long days = ChronoUnit.DAYS.between(now, ((Event) current).getTiming());
                if (days <= 7) {
                    numUpcoming++;
                    upcomingTasks = upcomingTasks + "\n " + numUpcoming + ". " + current.toString();
                }

            } else if (current instanceof Deadline) {
                long days = ChronoUnit.DAYS.between(now, ((Deadline) current).getBy());
                if (days <= 7) {
                    numUpcoming++;
                    upcomingTasks = upcomingTasks + "\n " + numUpcoming + ". " + current.toString();
                }
            }
        }

        return ui.showUpcomingTasks(upcomingTasks);
    }

    public boolean isExit() {
        return false;
    }
}
