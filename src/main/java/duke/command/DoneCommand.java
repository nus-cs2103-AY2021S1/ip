package duke.command;

import duke.*;
import duke.exception.InvalidInstructionException;
import duke.logic.StorageManager;
import duke.logic.TaskList;
import duke.logic.UIManager;

/**
 * Represents a Done Command by the user.
 * Apart from the parent's implementation,
 * it contains a <code>Integer</code> index of the <code>DukeTask</code>
 * to be marked done from the <code>TaskList</code>.
 */
public class DoneCommand extends Command {
    private final int index;

    public DoneCommand(int index) {
        super(false);
        this.index = index;
    }

    /**
     * Marks user's <code>DukeTask</code> in the <code>TaskList</code> as done and prints feedback.
     * Location of the <code>DukeTask</code> is extracted from the given index,
     * and input validation is performed before the command is executed.
     *
     * @param taskList <code>TaskList</code> object containing the user's <code>DukeTask</code>.
     * @param uiManager <code>UIManager</code> object to handle printing feedback to user.
     * @param storageManager <code>StorageManager</code> object to saving/loading user data.
     * @throws InvalidInstructionException  If user input validation fails.
     */
    @Override
    public void execute(TaskList taskList, UIManager uiManager, StorageManager storageManager) throws InvalidInstructionException {
        if (index < 0 || index >= taskList.getSize()) { // check if loc is an existing duke.task.DukeTask inside the array inputList
            throw new InvalidInstructionException(CommonString.DONE + ": Invalid Task Number");
        } else if (taskList.getTaskList().get(index).getDoneStatus()) { // check if inputList[loc] is already completed
            throw new InvalidInstructionException(CommonString.DONE + ": Task is already done!");
        } else {
            taskList.markDone(index);
            uiManager.printMarkAsDone(taskList.getTaskList().get(index), taskList.getSize());
        }
    }
}
