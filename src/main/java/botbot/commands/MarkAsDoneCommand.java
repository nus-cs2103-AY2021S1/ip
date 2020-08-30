package botbot.commands;

import botbot.Storage;
import botbot.tasks.Task;
import botbot.TaskList;
import botbot.Ui;

/**
 * Marks a task in the task list as done.
 */
public class MarkAsDoneCommand extends Command {
    private int id;

    /**
     * Creates a mark as done command to mark the specified task as done.
     *
     * @param id ID of task to be marked as done.
     */
    public MarkAsDoneCommand(int id) {
        this.id = id;
    }

    /**
     * Executes the mark as done command.
     *
     * @param storage Storage to save updated task list to.
     * @param tasks Task list to mark task as done from.
     * @param ui Ui to print status of execution.
     */
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        try {
            Task task = tasks.get(id);
            task.markAsDone();
            storage.save(tasks);
            ui.printStatus("    nice! I've marked this task as done:\n      " + task + "\n");
        } catch (IndexOutOfBoundsException e) {
            ui.printStatus("    oops! invalid task number!\n");
        }
    }
}
