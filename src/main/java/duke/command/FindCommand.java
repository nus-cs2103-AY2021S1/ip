package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand implements Command {
    private String toFind;

    public FindCommand(String toFind) {
        this.toFind = toFind;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.listTasks(
                tasks.findTasks(toFind));
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean isMassCommand() {
        return false;
    }
}
