package duke.commands;

import duke.task.Task;
import duke.task.TaskManager;
import duke.utils.Messages;

import java.util.List;

public class ListAllCompletedTasksCommand extends Command {
    @Override
    public CommandOutput executeCommand(TaskManager taskManger) {
        List<Task> completedTasks = taskManger.getAllCompletedTasks();
        String allCompletedTasksOutput = outputResult(completedTasks);
        return new CommandOutput(allCompletedTasksOutput.toString(), false);
    }

    private String outputResult(List<Task> completedTasks) {
        StringBuilder allCompletedTasksOutput = new StringBuilder();
        boolean hasCompletedTasks = completedTasks.size() > 0;
        if (hasCompletedTasks) {
            allCompletedTasksOutput.append("Here are all your completed tasks:\n");
            for (int i = 0; i < completedTasks.size(); i++) {
                Task completedTask = completedTasks.get(i);
                String taskDescriptionInListFormat = String.format("%d) %s\n", (i + 1), completedTask.toString());
                allCompletedTasksOutput.append((taskDescriptionInListFormat));
            }
        } else {
            allCompletedTasksOutput.append(Messages.NO_COMPLETED_TASKS_MESSAGE);
        }
        return allCompletedTasksOutput.toString();
    }
}
