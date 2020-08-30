package botbot.commands;

import botbot.Storage;
import botbot.tasks.Task;
import botbot.TaskList;
import botbot.Ui;

/**
 * Deletes a task from the task list.
 */
public class DeleteCommand extends Command {
    private int id;

    /**
     * Creates a delete command to delete the specified task.
     *
     * @param id ID of task to be deleted.
     */
    public DeleteCommand(int id) {
        this.id = id;
    }

    /**
     * Executes the delete command.
     *
     * @param storage Storage to save updated task list to.
     * @param tasks Task list to delete task from.
     * @param ui Ui to print status of execution.
     */
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        try {
            Task task = tasks.get(id);
            tasks.delete(id);
            storage.save(tasks);
            int numOfTasks = tasks.size();
            ui.printStatus(String.format("    ok! I've removed this task:\n      %s\n    you now have "
                    + "%d task" + (numOfTasks > 1 ? "s" : "") + " in your list\n", task, numOfTasks));
        } catch (IndexOutOfBoundsException e) {
            ui.printStatus("    oops! invalid task number!\n");
        }
    }
}
