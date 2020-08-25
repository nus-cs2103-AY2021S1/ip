package duke.commands;

import duke.storage.Storage;

import duke.task.TaskManager;

import duke.utils.Ui;

public class ListTasksCommand extends Command {
    public void executeCommand(TaskManager taskManager, Ui formatter, Storage storage){
        String taskList = taskManager.getAllTasks();
        formatter.print(taskList);
    }
}
