package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ByeCommand extends Command {
    private final boolean HAS_FINISHED = true;
    public ByeCommand() {

    }

    @Override
    public CommandResult execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printBye();
        return new CommandResult("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return this.HAS_FINISHED;
    }
}
