package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class CommandException extends Command {
    private String message;
    public CommandException(String message) {
        this.message = message;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) {
        ui.showErrorMsg(message);
    }
}