package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class IncorrectCommand extends Command {
    private String errorMessage;

    public IncorrectCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showError(errorMessage);
    }
}
