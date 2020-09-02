package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command {
    public ExitCommand(String fullCommand) {
        super(fullCommand);
        this.isExit = true;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.exit();
    }
}
