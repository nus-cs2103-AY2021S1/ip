package main.java.duke.command;

import main.java.duke.Storage;
import main.java.duke.task.Task;
import main.java.duke.TaskList;
import main.java.duke.Ui;

/**
 * Encapsulates a command to complete a task
 */
public class CompleteTaskCommand extends Command {

    /** Index of the task to complete in the current task list */
    final int taskIndex;

    /**
     * Constructor
     *
     * @param taskIndex Index of the task
     */
    public CompleteTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command to complete the task
     *
     * @param storage Storage
     * @param tasks Task list
     * @param ui Ui
     */
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        Task task = tasks.completeTaskAt(this.taskIndex);
        if (task != null) {
            ui.printCompleteTask(task);
        } else {
            ui.printInvalidTaskIndex();
        }
    }
}
