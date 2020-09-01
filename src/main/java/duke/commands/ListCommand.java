package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    private final boolean HAS_FINISHED = false;
    public ListCommand() {

    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.list();
    }

    @Override
    public boolean isExit() {
        return this.HAS_FINISHED;
    }
}
