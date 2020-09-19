package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class CommandException extends Command {
    private String errMessage;
    public CommandException(String errMessage) {
        this.errMessage = errMessage;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) {
        ui.showErrorMsg(errMessage);
    }

}