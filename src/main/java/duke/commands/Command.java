package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Parent class for all commands
 */
public class Command {
    
    /**
     * Performs the necessary tasks in order to carry out the command
     * 
     * @param ui ui which generates the appropriate response to the user
     * @param storage storage where data is stored
     * @param taskList taskList which stores information about tasks
     */
    public void executeCommand (Ui ui, Storage storage, TaskList taskList) {}

}
