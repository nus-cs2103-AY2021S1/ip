package duke.commands;

import duke.Storage;
import duke.lists.TaskList;
import duke.Ui;

/**
 * Command to list out all the tasks in the taskList
 */
public class ListCommand extends Command {
    
    @Override
    public void executeCommand (Ui ui, Storage storage, TaskList taskList) {
        ui.listMessage(taskList);
    }
    
    @Override
    public String toString() {
        return "ListCommand";
    }
}
