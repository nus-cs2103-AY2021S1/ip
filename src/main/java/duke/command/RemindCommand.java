package duke.command;

import java.util.stream.Collectors;

import duke.SaveManager;
import duke.TaskManager;
import duke.Ui;
import duke.task.Deadline;
import duke.time.DateTime;

/**
 * Class that represents user command to remind them about <code>Deadline</code>s that are due today.
 */
public class RemindCommand extends Command {

    /**
     * Filter all not done <code>Deadline</code>s from <code>taskManager</code> with date set to today, as returned by
     * <code>DateTime.getToday()</code>, then reminds the user about them.
     * Note: Only <code>DateTime</code> dated deadlines can be filtered. If the <code>Deadline</code> is
     * initialized with a <code>TimePoint</code> that relies on a description, it will not be filtered.
     *
     * @param ui Print-out and display manager.
     * @param taskManager <code>Task</code> manipulation manager.
     * @param saveManager Handles saving and loading.
     */
    @Override
    public void execute(Ui ui, TaskManager taskManager, SaveManager saveManager) {

        DateTime date = DateTime.getToday();

        // Get all deadlines due on date that have not been completed
        TaskManager deadlines = new TaskManager(taskManager
                .filter(task -> task instanceof Deadline)
                .filter(task -> !task.getDoneStatus())
                .getAllTasks()
                .stream()
                .map(deadline -> (Deadline) deadline)
                .filter(deadline -> deadline.getDeadline() instanceof DateTime)
                .filter(deadline -> ((DateTime) deadline.getDeadline()).hasSameDate(date))
                .collect(Collectors.toList()));

        if (deadlines.isEmpty()) {
            ui.queueMessageToDisplay("You have no deadlines for today :D");
        } else {
            ui.queueMessageToDisplay("~~ REMINDER!! - These deadlines are due today! ~~~");
            ui.queueMessageToDisplay("");
            ui.queueMessageToDisplay(deadlines.toString());

            assert deadlines.size() > 0;
        }

    }

}
