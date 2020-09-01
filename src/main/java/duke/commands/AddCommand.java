package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.StorageOperationException;

import duke.storage.Storage;

import duke.task.Task;
import duke.task.TaskManager;

import duke.utils.Colour;

/**
 * Represents the user command which will add a task to the taskManager
 * when the command is executed.
 */

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the specified task in the constructor to the taskManager and prints the respective message.
     * @param taskManager The taskManager that the task is being added to.
     * @param storage The storage that the task will be saved into.
     * @return The output of the command execution
     * @throws DukeException If the file path is invalid (does not end with ".txt").
     */
    public CommandOutput executeCommand(TaskManager taskManager, Storage storage) throws DukeException {
        taskManager.addTask(task);
        int totalNumberOfTasks = taskManager.getTotalNumberOfTasks();
        String output = "Got it. I've added this task:\n " + task.toString() + "\n";
        String numberOfTasks = totalNumberOfTasks < 2 ? " task in the list." : " tasks in the list.";
        String finalOutput = output + "Now you have a total of " + String.valueOf(totalNumberOfTasks) + numberOfTasks;
        try {
            storage.save(taskManager);
            return new CommandOutput(finalOutput, false);
        } catch (StorageOperationException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
