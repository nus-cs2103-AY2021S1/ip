package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class StartCommand extends Command {
    public static final String COMMAND = "hello";

    public StartCommand() {
        super();
    }

    @Override
    public boolean isStart() {
        return true;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showWelcome();
    }
}
