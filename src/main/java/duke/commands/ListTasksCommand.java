package duke.commands;

import duke.task.Task;
import duke.task.TaskManager;

import java.util.List;

/**
 * Represents the command which will list out all the tasks that are currently stored in the storage
 * upon execution.
 */

public class ListTasksCommand extends Command {
    @Override
    public CommandOutput executeCommand(TaskManager taskManager) {
        List<Task> allTasks = taskManager.getAllTasks();
        int numberOfCompletedTasks = taskManager.getNumberOfCompletedTasks();
        int numberOfUncompletedTasks = taskManager.getNumberOfUncompletedTasks();
        String listAllTasksOutput = outputResult(allTasks, numberOfCompletedTasks, numberOfUncompletedTasks);
        return new CommandOutput(listAllTasksOutput, false);
    }

    private String outputResult(List<Task> allTasks, int numberOfCompletedTasks, int numberOfUncompletedTasks) {
        StringBuilder allTasksOutput = new StringBuilder();
        allTasksOutput.append("Here are all your tasks: \n");
        for (int i = 0; i < allTasks.size(); i++) {
            String taskDescriptionInListFormat = String.format("%d) %s\n", i + 1, allTasks.get(i).toString());
            allTasksOutput.append(taskDescriptionInListFormat);
        }
        boolean hasMoreThanOneCompletedTask = numberOfCompletedTasks > 1;
        boolean hasMoreThanOneUncompletedTask = numberOfUncompletedTasks > 1;
        String completedTasks = numberOfCompletedTasks
                + (hasMoreThanOneCompletedTask ? " tasks" : " task");
        String uncompletedTasks = numberOfUncompletedTasks
                + (hasMoreThanOneUncompletedTask ? " tasks." : " task");
        allTasksOutput.append("You have completed " + completedTasks + " and have yet to complete "
                + uncompletedTasks);
        return allTasksOutput.toString();
    }
}
