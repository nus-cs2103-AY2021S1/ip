package duke.commands;

import duke.storage.Storage;

import duke.task.TaskManager;

import duke.utils.Ui;

/**
 * Represents the command which will list out all the tasks that are currently stored in the storage
 * upon execution.
 */

public class ListTasksCommand extends Command {
    public void executeCommand(TaskManager taskManager, Ui formatter, Storage storage){
        String taskList = taskManager.getAllTasks();
        formatter.print(taskList);
    }
}
