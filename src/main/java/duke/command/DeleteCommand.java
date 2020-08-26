package duke.command;

import duke.*;
import duke.exception.InvalidInstructionException;
import duke.logic.StorageManager;
import duke.logic.TaskList;
import duke.logic.UIManager;
import duke.task.DukeTask;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, UIManager uiManager, StorageManager storageManager)
            throws InvalidInstructionException {

        if (index < 0 || index >= taskList.getSize()) { // check if loc is an existing duke.task.DukeTask inside the array inputList
            throw new InvalidInstructionException(CommonString.DELETE + ": Invalid Task Number");
        } else {
            DukeTask task = taskList.deleteFromList(index);
            uiManager.printDeleteTask(task, taskList.getSize());
        }
    }
}
