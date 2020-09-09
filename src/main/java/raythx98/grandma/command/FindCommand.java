package raythx98.grandma.command;

import raythx98.grandma.exception.InvalidIndexException;
import raythx98.grandma.storage.Storage;
import raythx98.grandma.task.TaskList;
import raythx98.grandma.ui.Ui;

/**
 * Represents a Find Command.
 */
public class FindCommand extends Command {
    private String keyWord;
    public FindCommand (String keyWord) {
        this.keyWord = keyWord;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException {
        ui.appendMessage("Got it, here yur search results bij: \n" + tasks.findTask(keyWord));
    }
}
