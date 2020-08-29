package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents the DoneCommand when users mark items as done in TaskList.
 */
public class DoneCommand extends Command {
    private final int index;

    /**
     * Creates a DoneCommand.
     *
     * @param index index of task
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Marks a task in the TaskList as done.
     *
     * @param taskList taskList that stores Task objects
     * @param ui Ui that handles input and output to User
     * @param storage storage that handles data storage
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.setDoneTask(index, storage);
    }

    /**
     * Returns true if and only if it is a command to exit the program.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
