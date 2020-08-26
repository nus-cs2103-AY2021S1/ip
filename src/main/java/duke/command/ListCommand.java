package duke.command;

import duke.exception.InvalidInstructionException;
import duke.logic.StorageManager;
import duke.logic.TaskList;
import duke.logic.UIManager;

public class ListCommand extends Command {

    public ListCommand() {
        super(false);
    }
    @Override
    public void execute(TaskList taskList, UIManager uiManager, StorageManager storageManager) throws InvalidInstructionException {
        for (int i = 0; i < taskList.getSize(); i++) {
            uiManager.printNumberedTask(taskList.getTaskList().get(i), i);
        }
        uiManager.printTaskStatus(taskList.getSize());
    }
}
