package raythx98.grandma.command;

import raythx98.grandma.exception.InvalidIndexException;
import raythx98.grandma.storage.Storage;
import raythx98.grandma.task.TaskList;
import raythx98.grandma.ui.Ui;

public class FindCommand extends Command {
    private String keyWord;

    /**
     * Something.
     * @param keyWord
     */
    public FindCommand (String keyWord) {
        this.keyWord = keyWord;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException {
        assert keyWord != null;
        ui.addShowOnScreen("Got it, here yur search results bij: \n" + tasks.findTask(keyWord));
    }
}
