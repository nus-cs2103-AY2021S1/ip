package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.io.IOException;

public class FindCommand extends Command {
    private final boolean HAS_FINISHED = false;
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.find(keyword);
    }

    @Override
    public boolean isExit() {
        return this.HAS_FINISHED;
    }
}
