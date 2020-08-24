package duke.commands;

import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

/** Represents the command that adds a task to the taskList when executed. */
public class AddCommand extends Command {

    /** The task to be added. */
    private Task toAdd;

    /** Constructor.
     *
     * @param toAdd The task to be added to the taskList.
     */
    public AddCommand(Task toAdd) {
        this.toAdd = toAdd;
    }

    /** Adds the task specified in the constructor to the taskList and displays
     * the added task with a relevant message to the user.
     *
     * @param taskList The taskList that the task is being added to.
     * @param ui The ui that displays the message to the user.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.addTask(toAdd);
        ui.show(String.format(
                "\t Got it. I've added this task:\n\t\t%s\n\t %s",
                toAdd.toString(),
                taskList.tasksRemaining()
        ));
    }

}
