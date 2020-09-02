package raythx98.duke.command;

import raythx98.duke.exception.DukeException;
import raythx98.duke.storage.Storage;
import raythx98.duke.task.TaskList;
import raythx98.duke.ui.Ui;

public class FindCommand extends Command {
    private final String keyWord;
    public FindCommand (String keyWord) {
        this.keyWord = keyWord;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.addShowOnScreen(tasks.findTask(keyWord));
    }
}
