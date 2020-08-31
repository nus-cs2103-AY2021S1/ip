package duke.command;

import duke.task.Task;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a command to mark a task as done.
 */
public class DoneCommand extends Command {

    private int taskNumber;

    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Marks a Task in the TaskList as done.
     * @param taskList TaskList which the Task is added to.
     * @param ui Ui which helps prints output.
     */
    public String execute(TaskList taskList, Ui ui) {
        Task task = taskList.completeTask(taskNumber);
        return ui.print("Nice! I've marked this task as done:\n" + task.toString());
    }
}
