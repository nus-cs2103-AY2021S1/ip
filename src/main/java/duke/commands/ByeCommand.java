package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ByeCommand extends Command {
    
    public ByeCommand() {};
    
    public void executeCommand (Ui ui, Storage storage, TaskList taskList) {
        ui.byeMessage();
    }
}
