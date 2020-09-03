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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String keyword = nextCommandArr[1];
        return ui.listRelevantTasks(tasks, keyword);
    }

    @Override
    public boolean continueRunning() {
        return true;
    }
}
