package duke.command;

import duke.task.Task;
import duke.util.TaskList;
import duke.util.Ui;

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
    public String execute(TaskList taskList, Ui ui) {
        taskList.addTask(task);
        return ui.print(String.format("Got it. I've added this task:\n%s\n Now you have %d tasks in the list",
                task.toString(), taskList.getNumberOfTasks()));
    }
}
