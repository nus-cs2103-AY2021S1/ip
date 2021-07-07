package duke.commands;

import java.time.LocalDate;

import java.util.List;
import java.util.stream.Collectors;

import duke.task.Task;
import duke.task.TaskManager;

import duke.utils.Messages;

/**
 * Represents the command which will list out all overdue {@code Task}s.
 */
public class OverdueCommand extends Command {
    private static final LocalDate TODAY = LocalDate.now();

    /**
     * Retrieves all {@code Task} that are overdue and returns an output containing the overdue {@code Task}.
     *
     * @param taskManger the {@code TaskManager} object that contains the list of {@code Task}s.
     * @return the output from executing the command.
     */
    @Override
    public CommandOutput executeCommand(TaskManager taskManger) {
        List<Task> tasksBeforeQueryDate = taskManger.getAllTasksBeforeQueryDate(TODAY);
        String allOverdueTasksOutput = outputResult(tasksBeforeQueryDate);
        return new CommandOutput(allOverdueTasksOutput, false);
    }

    private String outputResult(List<Task> tasksBeforeQueryDate) {
        StringBuilder allOverdueTasksOutput = new StringBuilder();
        List<Task> overdueTasks = tasksBeforeQueryDate.stream()
                .filter(task -> !task.getIsDone())
                .collect(Collectors.toList());
        boolean hasOverdueTasks = overdueTasks.size() > 0;
        if (hasOverdueTasks) {
            allOverdueTasksOutput.append("Here are your overdue tasks:\n");
            int listNumber = 1;
            for (Task overdueTask : overdueTasks) {
                String taskDescription = overdueTask.toString();
                String format = String.format("%d) %s\n", listNumber, taskDescription);
                allOverdueTasksOutput.append(format);
                listNumber++;
            }
        } else {
            allOverdueTasksOutput.append(Messages.NO_OVERDUE_TASKS_MESSAGE);
        }
        return allOverdueTasksOutput.toString();
    }
}
