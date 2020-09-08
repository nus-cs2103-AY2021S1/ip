package duke.commands;

import duke.task.Task;
import duke.task.TaskManager;
import duke.utils.Messages;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class OverdueCommand extends Command {
    private static final LocalDate TODAY = LocalDate.now();
    @Override
    public CommandOutput executeCommand(TaskManager taskManger) {
        List<Task> tasksBeforeQueryDate = taskManger.getAllTasksBeforeQueryDate(TODAY);
        String allOverdueTasksOutput = outputResult(tasksBeforeQueryDate);
        return new CommandOutput(allOverdueTasksOutput.toString(), false);
    }

    private String outputResult(List<Task> tasksBeforeQueryDate) {
        StringBuilder allOverdueTasksOutput = new StringBuilder();
        List<Task> overdueTasks = tasksBeforeQueryDate.stream()
                .filter(task -> !task.getIsDone())
                .collect(Collectors.toList());
        boolean hasOverdueTasks = overdueTasks.size() > 0;
        if (hasOverdueTasks) {
            allOverdueTasksOutput.append("Here are your overdue tasks:\n");
            for (int i = 0; i < tasksBeforeQueryDate.size(); i++) {
                String taskDescriptionInListFormat = String.format("%d) %s\n", i + 1, overdueTasks.get(i).toString());
                allOverdueTasksOutput.append(taskDescriptionInListFormat);
            }
        } else {
            allOverdueTasksOutput.append(Messages.NO_OVERDUE_TASKS_MESSAGE);
        }
        return allOverdueTasksOutput.toString();
    }
}
