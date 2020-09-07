package duke.commands;

import duke.task.Task;
import duke.task.TaskManager;
import duke.utils.Messages;

import java.time.LocalDate;
import java.util.List;

public class OverdueCommand extends Command {
    private static final LocalDate TODAY = LocalDate.now();
    @Override
    public CommandOutput executeCommand(TaskManager taskManger) {
        List<Task> overdueTasks = taskManger.getAllTasksBeforeQueryDate(TODAY);
        String allOverdueTasksOutput = outputResult(overdueTasks);
        return new CommandOutput(allOverdueTasksOutput.toString(), false);
    }

    private String outputResult(List<Task> overdueTasks) {
        StringBuilder allOverdueTasksOutput = new StringBuilder();
        boolean hasOverdueTasks = overdueTasks.size() > 0;
        if (hasOverdueTasks) {
            allOverdueTasksOutput.append("Here are your overdue tasks:\n");
            for (int i = 0; i < overdueTasks.size(); i++) {
                String taskDescriptionInListFormat = String.format("%d) %s\n", (i + 1), overdueTasks.get(i));
                allOverdueTasksOutput.append(taskDescriptionInListFormat);
            }
        } else {
            allOverdueTasksOutput.append(Messages.NO_OVERDUE_TASKS_MESSAGE);
        }
        return allOverdueTasksOutput.toString();
    }
}
