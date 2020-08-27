package duke.command;

import duke.logic.StorageManager;
import duke.logic.TaskList;
import duke.logic.UIManager;

public class HelpCommand extends Command {

    public HelpCommand() {
        super(false);
    }

    @Override
    public void execute(TaskList taskList, UIManager uiManager, StorageManager storageManager) {
        uiManager.printDukeInstructions();
    }
}
