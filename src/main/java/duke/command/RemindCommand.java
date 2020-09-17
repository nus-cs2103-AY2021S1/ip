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
     * Get all not done <code>Deadline</code>s from <code>taskManager</code> with date set to today, as returned by
     * <code>DateTime.getToday()</code>, then reminds the user about them.
     * Note: Only <code>DateTime</code> dated deadlines will be displayed. If the <code>Deadline</code> is
     * initialized with a <code>TimePoint</code> that relies on a description, it will be filtered away.
     *
     * @param ui Print-out and display manager.
     * @param taskManager <code>Task</code> manipulation manager.
     * @param saveManager Handles saving and loading.
     */
    @Override
    public void execute(Ui ui, TaskManager taskManager, SaveManager saveManager) {



        TaskManager deadlines = filterDeadlinesFromTaskManager(taskManager);

        // Output message to screen depending on number of deadlines present.
        if (deadlines.isEmpty()) {
            ui.queueMessageToDisplay("You have no deadlines for today :D");
        } else {
            ui.queueMessageToDisplay("~~ REMINDER!! - These deadlines are due today! ~~~");
            ui.queueMessageToDisplay("");
            ui.queueMessageToDisplay(deadlines.toString());

            assert deadlines.size() > 0;
        }

    }

    private TaskManager filterDeadlinesFromTaskManager(TaskManager taskManager) {

        DateTime date = DateTime.getToday();

        return new TaskManager(taskManager
                .filter(task -> task instanceof Deadline)
                .filter(task -> !task.getDoneStatus())
                .getAllTasks()
                .stream()
                .map(deadline -> (Deadline) deadline)
                .filter(deadline -> deadline.getDeadline() instanceof DateTime)
                .filter(deadline -> ((DateTime) deadline.getDeadline()).hasSameDate(date))
                .collect(Collectors.toList()));

    }

}
