package duke.command;

import java.util.ArrayList;
import java.util.List;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents the Command to mark a specific
 * Task as done.
 */
public class DoneCommand implements Command {

    private final int taskNum;

    /**
     * Initializes a DoneCommand.
     * @param taskNum The number of the task in the
     *                taskList to be marked as done.
     */
    public DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Marks the Task at the specified taskNum
     * as Done.
     * @param ui The ui of Duke.
     * @param storage The storage object.
     * @param tasks The taskList.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) {
        Task doneTask = tasks.markTaskAsDone(taskNum);
        ui.printWithWrapper(new ArrayList<>(List.of(
                "OK! I have marked the following task as done:",
                doneTask.toString())), false, false);
    }
}
