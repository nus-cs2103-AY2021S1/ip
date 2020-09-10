package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.ui.DukeMessages;

public class FindCommand extends Command {
    private String matchString;

    public FindCommand(String matchString) {
        this.matchString = matchString;
    }

    @Override
    public String execute(TaskList list, Storage storage) {
        return DukeMessages.printFindMessage(list.findTasks(this.matchString));
    }
}
