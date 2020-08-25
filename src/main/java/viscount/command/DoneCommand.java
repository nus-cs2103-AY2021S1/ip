package viscount.command;

import viscount.*;

import viscount.exception.ViscountIOException;
import viscount.exception.ViscountIndexOutOfBoundsException;

/**
 * Represents a done command.
 */
public class DoneCommand extends Command {
    private int taskIndex;
    
    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the done command.
     *
     * @param taskList Task list where tasks are stored.
     * @param ui Ui to display response.
     * @param storage Storage to save changes to disk.
     * @throws ViscountIndexOutOfBoundsException If taskIndex is < 0 or >= list size
     * @throws ViscountIOException If exception occurs with writing to disk.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws ViscountIndexOutOfBoundsException,
            ViscountIOException {
        taskList.markDone(taskIndex);
        storage.saveToDisk(taskList.getTasks());
        ui.showDone(taskList.getTask(taskIndex));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
