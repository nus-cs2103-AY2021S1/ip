package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {
    
    private final String filter;
    
    public FindCommand(String filter) {
        this.filter = filter;
    }
    
    @Override
    public void executeCommand (Ui ui, Storage storage, TaskList taskList) {
        TaskList filteredList = taskList.filter(filter);
        ui.listMessage(filteredList);
    }
}
