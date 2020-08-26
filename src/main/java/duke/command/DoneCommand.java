package duke.command;

import duke.task.Task;
import duke.Storage;
import duke.tool.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to mark element with certain index as done.
 */
public class DoneCommand implements Command {

    /** Index of task that will be marked as done */
    private final int targetIndex;

    /**
     * Creates a command to mark certain task as done.
     *
     * @param index Index of tasks that is going to be marked as done.
     */
    public DoneCommand(int index) {
        this.targetIndex = index;
    }

    @Override
    public void excute(TaskList tasks, Ui ui, Storage storage) {
        Task markedTask = tasks.markDone(targetIndex);
        ui.showDoneGreet(markedTask);
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
