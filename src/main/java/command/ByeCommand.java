package command;

import duke.Parser;
import duke.Storage;
import duke.TaskList;
import ui.Ui;

public class ByeCommand extends Command {

    public ByeCommand() {
        super();
    }

    @Override
    public boolean isEndCommand() {
        return true;
    }

    @Override
    public Result execute(TaskList taskList, Parser parser, Storage aliasStorage, Storage taskStorage, Ui ui) {
        return new Result(ui.farewellMessage(), executedSuccessfully);
    }
}
