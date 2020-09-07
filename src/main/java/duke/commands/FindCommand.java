package duke.commands;

import duke.task.Task;
import duke.task.TaskManager;

import duke.utils.Messages;

import java.util.List;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public CommandOutput executeCommand(TaskManager taskManager) {
        List<Task> filteredTasks = taskManager.findTasksByKeyword(keyword);
        String filteredTasksOutput = outputResult(filteredTasks);
        return new CommandOutput(filteredTasksOutput, false);
    }

    private String outputResult(List<Task> filteredTasks) {
        StringBuilder filteredTasksOutput = new StringBuilder();
        boolean hasFilteredTasks = filteredTasks.size() > 0;
        if (hasFilteredTasks) {
            filteredTasksOutput.append(Messages.FILTERED_TASKS_STARTING_MESSAGE);
            for (Task task : filteredTasks) {
                String taskDescription = task.toString();
                filteredTasksOutput.append(taskDescription + "\n");
            }
        } else {
            filteredTasksOutput.append(Messages.NO_TASKS_UNDER_KEYWORD_MESSAGE);
        }
        return filteredTasksOutput.toString();
    }
}
