package duke.command;

import duke.task.Command;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command {
    public ExitCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        processBye(ui);
    }

    public void processBye(Ui ui) {
        ui.bye();
    }

}
