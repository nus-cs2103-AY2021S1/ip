package duke.commands;

import duke.exceptions.StorageOperationException;

import duke.storage.Storage;

import duke.task.Task;
import duke.task.TaskManager;

import duke.utils.Colour;
import duke.utils.ResourceHandler;
import duke.utils.Ui;

public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    public void executeCommand(TaskManager taskManager, Ui formatter, Storage storage) {
        try {
            Task deletedTask = taskManager.getTask(taskIndex - 1);
            taskManager.deleteTask(taskIndex);
            storage.save(taskManager);
            String output = "Noted. I have removed the task: \n";
            formatter.print(output + Colour.Red(deletedTask.toString()));
        } catch (StorageOperationException e) {
            formatter.print(Colour.Red(e.getMessage()));
        } catch (IndexOutOfBoundsException e) {
            formatter.print(Colour.Red(ResourceHandler.getMessage("commandline.invalidTaskIndexErrorMessage")));
        }
    }
}
