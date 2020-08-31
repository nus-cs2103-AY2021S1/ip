package duke.commands;

import duke.storage.Storage;

import duke.task.TaskManager;

/**
 * Represents the command which will list out all the tasks that are currently stored in the storage
 * upon execution.
 */

public class ListTasksCommand extends Command {
    public CommandOutput executeCommand(TaskManager taskManager, Storage storage) {
        String taskList = taskManager.getAllTasks();
        return new CommandOutput(taskList, false);
    }
}
