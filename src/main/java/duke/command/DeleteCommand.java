package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.task.Task;
import duke.Ui;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the Command to delete
 * existing Tasks from taskList.
 */
public class DeleteCommand implements Command {
    private final int taskNum;

    /**
     * Initializes DeleteCommand.
     * @param taskNum The number of the task in
     *                the taskList to be deleted.
     */
    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Deletes the task at the specified taskNum in
     * the taskList.
     * @param ui The ui of Duke.
     * @param storage The storage object.
     * @param tasks The taskList.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) {
        Task delTask = tasks.delete(taskNum);
        ui.printWithWrapper(new ArrayList<>(List.of(
                "OK! I have deleted the following task for your list:",
                delTask.toString(),
                tasks.getListStatus())), false, false);
    }
}
