package duke.commands;

import duke.storage.Storage;

import duke.task.Task;
import duke.task.TaskManager;

import java.util.List;

/**
 * Represents the command which will list out all the tasks that are currently stored in the storage
 * upon execution.
 */

public class ListTasksCommand extends Command {
    @Override
    public CommandOutput executeCommand(TaskManager taskManager, Storage storage) {
        List<Task> allTasks = taskManager.getAllTasks();
        StringBuilder allTasksOutput = new StringBuilder();
        allTasksOutput.append("Here are all your tasks: \n");
        for (int i = 0; i < allTasks.size(); i++) {
            String taskDescriptionInListFormat = String.format("%d) %s\n", i + 1, allTasks.get(i).toString());
            allTasksOutput.append(taskDescriptionInListFormat);
        }
        String completedTasks = taskManager.getCompletedTasks()
                + (taskManager.isPluralCompletedTasks() ? " tasks" : " task");
        String uncompletedTasks = taskManager.getUncompletedTasks()
                + (taskManager.isPluralUncompletedTasks() ? " tasks." : " task");
        allTasksOutput.append("You have completed " + completedTasks + " and have yet to complete "
                + uncompletedTasks);
        return new CommandOutput(allTasksOutput.toString(), false);
    }
}
