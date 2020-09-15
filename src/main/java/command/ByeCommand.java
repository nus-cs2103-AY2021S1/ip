package command;

import duke.Storage;
import duke.TaskList;

public class ByeCommand extends Command {

    public ByeCommand() {
        super();
    }

    @Override
    public Result execute(TaskList taskList, Storage storage) {
        return new Result("Adios, pleasure to serve you!\n", executedSuccessfully);
    }


}
