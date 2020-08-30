package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;

public class FindCommand extends Command {
    private String matchString;

    public FindCommand(String matchString) {
        this.matchString = matchString;
    }
    @Override
    public void execute(TaskList list, Storage storage) throws DukeException {
        Ui.findMessage(list.findTasks(this.matchString));
    }
}
