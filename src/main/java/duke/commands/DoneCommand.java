package duke.commands;

import duke.exceptions.StorageOperationException;

import duke.storage.Storage;

import duke.task.Task;
import duke.task.TaskManager;

import duke.utils.Colour;
import duke.utils.ResourceHandler;
import duke.utils.Ui;

/**
 * Represents the command which will mark a particular task as completed upon execution.
 */

public class DoneCommand extends Command {
    private int completedTaskIndex;

    public DoneCommand(int completedTaskIndex) {
        this.completedTaskIndex = completedTaskIndex;
    }

    @Override
    public void executeCommand(TaskManager taskManger, Ui formatter, Storage storage) {
        try {
            Task completedTask = taskManger.getTask(completedTaskIndex - 1);
            taskManger.markTaskAsDone(completedTaskIndex);
            storage.save(taskManger);
            String taskDoneMessage = ResourceHandler.getMessage("taskManager.taskDoneMessage");
            StringBuilder stringBuilder = new StringBuilder(taskDoneMessage);
            stringBuilder.append(Colour.Green(completedTask.toString()));
            formatter.print(stringBuilder.toString());
        } catch (StorageOperationException e) {
            formatter.print(Colour.Red(e.getMessage()));
        } catch (IndexOutOfBoundsException e) {
            formatter.print(Colour.Red(ResourceHandler.getMessage("commandline.invalidTaskIndexErrorMessage")));
        }
    }
}
