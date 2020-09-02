package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public class ClearCommand extends Command {
    public ClearCommand(String fullCommand) {
        super(fullCommand);
        this.isExit = false;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.deleteAll();
        return getClearMessage();
    }

    public String getClearMessage() {
        return "all the tasks in your list have been cleared";
    }
}
