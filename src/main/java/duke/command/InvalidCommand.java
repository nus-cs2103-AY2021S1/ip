package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public class InvalidCommand extends Command {
    public InvalidCommand(String fullCommand) {
        super(fullCommand);
        this.isExit = false;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "oops! im sorry, but i do not know what that means :-(";
    }
}
