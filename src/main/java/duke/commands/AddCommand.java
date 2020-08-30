package duke.commands;

import duke.exceptions.StorageOperationException;

import duke.storage.Storage;

import duke.task.Task;
import duke.task.TaskManager;

import duke.utils.Colour;
import duke.utils.Ui;

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
     * @param formatter The formatter which will be used to format the output result
     * @param storage The storage that the task will be saved into.
     */
    public void executeCommand(TaskManager taskManager, Ui formatter, Storage storage) {
        taskManager.addTask(task);
        int totalNumberOfTasks = taskManager.getTotalNumberOfTasks();
        String output = "Got it. I've added this task:\n " + Colour.convertTextToGreen(task.toString()) + "\n";
        String numberOfTasks = totalNumberOfTasks < 2 ? " task in the list." : " tasks in the list.";
        formatter.print(output + "Now you have a total of " + String.valueOf(totalNumberOfTasks) + numberOfTasks);
        try {
            storage.save(taskManager);
        } catch (StorageOperationException e) {
            formatter.print(Colour.convertTextToRed(e.getMessage()));
        }
    }
}
