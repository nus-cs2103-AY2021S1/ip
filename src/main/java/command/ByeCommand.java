package command;

import duke.Parser;
import duke.TaskList;

public class ByeCommand extends Command {

    public ByeCommand() {
        super();
    }

    @Override
    public Result execute(TaskList taskList, Parser parser) {
        return new Result("Adios, pleasure to serve you!\n", executedSuccessfully);
    }
}
