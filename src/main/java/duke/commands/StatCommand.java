package duke.commands;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import duke.data.task.Task;
import duke.utils.Messages;

/**
 * Displays the number of tasks that have been completed.
 */
public class StatCommand extends Command {

    public static final String COMMAND_WORD = "stat";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays the number of tasks that have been completed.\n"
            + "\tExample: " + COMMAND_WORD;

    @Override
    public CommandResult execute() {
        List<Task> allTasks = super.taskList.getList();
        return new CommandResult(getMessageForTasksStatistics(allTasks));
    }

    private String getMessageForTasksStatistics(List<Task> allTasks) {
        return String.format(Messages.MESSAGE_STATISTICS, calculateStats(allTasks), allTasks.size());
    }

    private int calculateStats(List<Task> allTasks) {
        AtomicInteger numOfDoneTasks = new AtomicInteger();
        allTasks.forEach(task -> {
            if (task.taskIsDone()) {
                numOfDoneTasks.getAndIncrement();
            }
        });
        return numOfDoneTasks.get();
    }

}
