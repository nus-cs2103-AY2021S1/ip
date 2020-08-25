package main.java.duke.command;

import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.Ui;

import main.java.duke.task.Task;

/**
 * Encapsulates a command to delete a task
 */
public class DeleteTaskCommand extends Command {

    /** Index of the task to be deleted in the current task list */
    final int taskIndex;

    /**
     * Constructor
     *
     * @param taskIndex Index of the task
     */
    public DeleteTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command to delete the task
     *
     * @param storage Storage
     * @param tasks Task list
     * @param ui Ui
     */
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        Task task = tasks.deleteTaskAt(this.taskIndex);

        if (task != null) {
            ui.printDeleteTask(tasks, task);
        } else {
            ui.printInvalidTaskIndex();
        }
    }
}
