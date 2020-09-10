package duke.commands;

import duke.task.Task;
import duke.task.TaskManager;

/**
 * Represents the user command which will add a task to the taskManager
 * when the command is executed.
 */

public class AddCommand extends Command {
    private Task task;

    /**
     * Constructor
     *
     * @param task The task that will needs to be added to the list of tasks
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the specified task in the constructor to the taskManager
     *
     * @param taskManager The taskManager that the task is being added to.
     * @return The output of the command execution
     */
    public CommandOutput executeCommand(TaskManager taskManager) {
        taskManager.addTask(task);
        int totalNumberOfTasks = taskManager.getTotalNumberOfTasks();
        String addTaskOutput = outputResult(totalNumberOfTasks);
        return new CommandOutput(addTaskOutput, false);
    }

    private String outputResult(int totalNumberOfTasks) {
        StringBuilder addTaskOutput = new StringBuilder("Got it. I've added this task:\n");
        String taskDescription = task.toString();
        addTaskOutput.append(taskDescription + "\n");
        String lastOutputLine = "Now you have a total of " + String.valueOf(totalNumberOfTasks);
        addTaskOutput.append(lastOutputLine);
        boolean hasMoreThanOneTask = totalNumberOfTasks > 1;
        if (hasMoreThanOneTask) {
            addTaskOutput.append(" tasks in the list.");
        } else {
            addTaskOutput.append(" task in the list.");
        }
        return addTaskOutput.toString();
    }
}
