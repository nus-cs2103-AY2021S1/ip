package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Class that simulates the bye command of the user.
 */

public class ByeCommand extends Command {

    /**
     * Creates an ByeCommand object
     * 
     * @param inputArr Array of length 2 that contains information of the user input
     *                 At index 0, contains the type of command
     *                 At index 1, contains the message of the command.
     */
    public ByeCommand(String[] inputArr) {
        super(inputArr);
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.goodBye();
        storage.record(tasks);
        setExitStatus(true);
    }
}
