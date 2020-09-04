package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    
    public ListCommand() {}
    
    @Override
    public void executeCommand (Ui ui, Storage storage, TaskList taskList) {
        ui.listMessage(taskList);
    }
}
