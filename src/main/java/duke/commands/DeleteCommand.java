package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.StorageOperationException;

import duke.storage.Storage;

import duke.task.Task;
import duke.task.TaskManager;

import duke.utils.Colour;
import duke.utils.ResourceHandler;

/**
 * Represents the command which will delete a particular task when it is executed.
 */

public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    public CommandOutput executeCommand(TaskManager taskManager, Storage storage) throws DukeException {
        try {
            Task deletedTask = taskManager.getTask(taskIndex - 1);
            taskManager.deleteTask(taskIndex);
            storage.save(taskManager);
            String output = "Noted. I have removed the task: \n" + Colour.convertTextToRed(deletedTask.toString());
            return new CommandOutput(output, false);
        } catch (StorageOperationException e) {
            throw new DukeException(Colour.convertTextToRed(e.getMessage()));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(Colour.convertTextToRed(ResourceHandler.getMessage(
                    "commandline.invalidTaskIndexErrorMessage")));
        }
    }
}
