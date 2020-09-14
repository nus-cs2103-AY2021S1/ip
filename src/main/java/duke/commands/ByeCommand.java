package duke.commands;

import duke.Storage;
import duke.lists.TaskList;
import duke.Ui;
import javafx.application.Platform;

/**
 * Command executed when user inputs "bye"
 */
public class ByeCommand extends Command {

    @Override
    public void executeCommand (Ui ui, Storage storage, TaskList taskList) {
        ui.byeMessage();
        Platform.exit();
    }
    
    @Override
    public String toString() {
        return "ByeCommand";
    }
    
}
