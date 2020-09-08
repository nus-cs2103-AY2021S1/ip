package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {

    private final String keyWord;

    /**
     * Constructs a FindCommand object.
     *
     * @param keyWord keyword to find tasks
     */
    public FindCommand(String keyWord) {
        assert !keyWord.isEmpty() : "Command description is missing.";
        this.keyWord = keyWord;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return taskList.findTasks(this.keyWord);
    }
}
