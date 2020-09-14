package duke.commands;

import duke.Storage;
import duke.lists.TaskList;
import duke.Ui;

/**
 * Command to find tasks with descriptions that contain the specified string
 */
public class FindCommand extends Command {
    
    private final String filter;

    /**
     * Constructor for the class
     * 
     * @param filter specified string to filter task descriptions
     */
    public FindCommand(String filter) {
        this.filter = filter;
    }
    
    @Override
    public void executeCommand (Ui ui, Storage storage, TaskList taskList) {
        TaskList filteredList = taskList.filterList(filter);
        ui.listMessage(filteredList);
    }
    
    @Override
    public String toString() {
        return "FindCommand";
    }
}
