package duke.command;

import duke.*;

public class ByeCommand extends Command {

    public ByeCommand() {
        super("bye", true);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printGoodbye();
    }

}
