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
    public String execute(TaskList list, Storage storage) {
        return Ui.findMessage(list.findTasks(this.matchString));
    }
}
