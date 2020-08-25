package duke.command;

import duke.task.Task;
import duke.task.TaskList;

import duke.ui.Ui;

/**
 * Helps handle commands that include adding tasks.
 */
abstract class TaskCreationCommand extends Command {
    /** 
     * Adds the <code>Task</code> to the <code>TaskList</code>, then pass on to the ui to print them out.
     * @param task the task to be added
     * @param ui An Ui object that correspond to interacting with the user
     * @param tasks a list to which the <code>ask</code> will be added
     */
    public void execute(Task task, Ui ui, TaskList tasks) {
        tasks.add(task);
        ui.showAddSuccessful(task, tasks);
    }
}
