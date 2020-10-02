package raythx.grandma.command;

import raythx.grandma.exception.InvalidIndexException;
import raythx.grandma.storage.Storage;
import raythx.grandma.task.TaskList;
import raythx.grandma.ui.Ui;

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
        assert keyWord != null;
        ui.appendMessage("Got it, here yur search results bij: \n" + tasks.findTask(keyWord));
    }
}
