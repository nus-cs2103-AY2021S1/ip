package duke.commands;

import duke.exception.UnknownCommandException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Class the simulates an unknown command.
 */

public class UnknownCommand extends Command{
    public UnknownCommand(String[] inputArr) {
        super(inputArr);
    }

    /**
     * To throw the unknown command exception.
     * @param tasks Object contains the task list.
     * @param ui Object that deals with interactions with the user.
     * @param storage Object that deals with loading tasks from the file and saving tasks in the file
     * @throws UnknownCommandException Throws an unknown command exception when user's input is not recognized.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws UnknownCommandException{
        throw new UnknownCommandException();
    }
}
