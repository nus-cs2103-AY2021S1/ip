package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class TagCommand extends Command {
    private final String tag;

    /**
     * Constructs a FindCommand object.
     *
     * @param tag to find tasks
     */
    public TagCommand(String tag) {
        assert !tag.isEmpty() : "Command description is missing.";
        this.tag = tag;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        return taskList.findTasksWithTag(this.tag);
    }
}
