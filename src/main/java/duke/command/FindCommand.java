package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {

    private final String keyWord;

    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException {
        taskList.findTasks(this.keyWord);
    }
}
