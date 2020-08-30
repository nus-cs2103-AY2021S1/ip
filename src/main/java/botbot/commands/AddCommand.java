package botbot.commands;

import botbot.Storage;
import botbot.tasks.Task;
import botbot.TaskList;
import botbot.Ui;

/**
 * Adds a task to the task list.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Creates an add command to add the specified task.
     *
     * @param task Task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the add command.
     *
     * @param storage Storage to save updated task list to.
     * @param tasks Task list to add task to.
     * @param ui Ui to print status of execution.
     */
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        tasks.add(task);
        storage.save(tasks);
        int numOfTasks = tasks.size();
        ui.printStatus(String.format("    ok! I've added this task:\n      %s\n    you now have %d task"
                + (numOfTasks > 1 ? "s" : "") + " in your list\n", task, numOfTasks));
    }
}