package duke.command;

import duke.util.TaskList;
import duke.util.Ui;
import duke.task.Task;

/**
 * Represents a command to add a task.
 */
public class AddCommand extends Command {

    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds a Task to the TaskList.
     * @param taskList TaskList which the Task is added to.
     * @param ui Ui which helps prints output.
     */
    public void execute(TaskList taskList, Ui ui) {
        taskList.addTask(task);
        ui.print("Got it. I've added this task:");
        ui.print(task.toString());
        ui.print(String.format("Now you have %d tasks in the list", taskList.getNumberOfTasks()));
    }
}
