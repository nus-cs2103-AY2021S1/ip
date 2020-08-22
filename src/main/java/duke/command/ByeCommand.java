package duke.command;

import duke.SaveManager;
import duke.TaskManager;
import duke.Ui;
import duke.exception.DukeSaveDataException;

public class ByeCommand extends Command {

    public void execute(Ui ui, TaskManager taskManager, SaveManager saveManager) {
        ui.displayGoodbye();
        try {
            saveManager.save(taskManager);
        } catch (DukeSaveDataException e) {
            ui.displayException(e);
        }
    }

    public boolean isByeCommand() {
        return true;
    }

}
