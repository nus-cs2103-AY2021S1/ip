package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Tasks;

public class ByeCommand extends Command {
    private static final int EXIT_STATUS = 0;

    public ByeCommand() {
        this.commandType = CommandType.BYE;
    }

    public void execute(Tasks tasks, Ui ui, Storage storage) {
        ui.printExitMessage();
        System.exit(EXIT_STATUS);
    }
}
