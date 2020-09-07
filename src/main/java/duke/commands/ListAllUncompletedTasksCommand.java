package duke.commands;

import duke.task.Task;
import duke.task.TaskManager;
import duke.utils.Messages;

import java.util.List;

public class ListAllUncompletedTasksCommand extends Command {

    @Override
    public CommandOutput executeCommand(TaskManager taskManger) {
        List<Task> uncompletedTasks = taskManger.getAllUncompletedTasks();
        String allUncompletedTasksOutput = outputResult(uncompletedTasks);
        return new CommandOutput(allUncompletedTasksOutput, false);
    }

    private String outputResult (List<Task> uncompletedTasks) {
        StringBuilder allUncompletedTasksOutput = new StringBuilder();
        boolean hasUncompletedTasks = uncompletedTasks.size() > 0;
        if (hasUncompletedTasks) {
            allUncompletedTasksOutput.append("Here are all your uncompleted tasks:\n");
            for (int i = 0; i < uncompletedTasks.size(); i++) {
                Task uncompletedTask = uncompletedTasks.get(i);
                String taskDescriptionInListFormat = String.format("%d) %s\n", (i + 1), uncompletedTask.toString());
                allUncompletedTasksOutput.append((taskDescriptionInListFormat));
            }
        } else {
            allUncompletedTasksOutput.append(Messages.NO_UNCOMPLETED_TASKS_MESSAGE);
        }
        return allUncompletedTasksOutput.toString();
    }
}
