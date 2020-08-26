package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    private String[] nextCommandArr;

    public FindCommand(String[] nextCommandArr) {
        this.nextCommandArr = nextCommandArr;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String keyword = nextCommandArr[1];
        ui.listRelevantTasks(tasks, keyword);
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
