package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    private final String keyWords;

    public FindCommand(String keyWords) {
        this.keyWords = keyWords;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.findTasksMessage(tasks, keyWords);
    }
}
