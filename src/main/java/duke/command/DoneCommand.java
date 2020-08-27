package duke.command;

import duke.CommonString;
import duke.exception.InvalidInstructionException;
import duke.logic.StorageManager;
import duke.logic.TaskList;
import duke.logic.UIManager;

public class DoneCommand extends Command {
    private final int index;

    public DoneCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, UIManager uiManager, StorageManager storageManager)
            throws InvalidInstructionException {
        if (index < 0 || index >= taskList.getSize()) {
            throw new InvalidInstructionException(CommonString.DONE + ": Invalid Task Number");
        } else if (taskList.getTaskList().get(index).getDoneStatus()) {
            throw new InvalidInstructionException(CommonString.DONE + ": Task is already done!");
        } else {
            taskList.markDone(index);
            uiManager.printMarkAsDone(taskList.getTaskList().get(index), taskList.getSize());
        }
    }
}
