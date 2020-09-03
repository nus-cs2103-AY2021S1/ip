package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class FindCommand extends Command {
    private String matchString;

    public FindCommand(String matchString) {
        this.matchString = matchString;
    }
    @Override
    public String execute(TaskList list, Storage storage) {
        return Ui.printFindMessage(list.findTasks(this.matchString));
    }
}
