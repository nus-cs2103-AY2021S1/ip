package duke.command;

import duke.task.Task;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {

    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Removes a Task from the TaskList.
     * @param taskList TaskList which the Task is added to.
     * @param ui Ui which helps prints output.
     */
    public String execute(TaskList taskList, Ui ui) {
        Task task = taskList.deleteTask(taskNumber);
        return ui.print(String.format("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list",
                task.toString(), taskList.getNumberOfTasks()));
    }
}
