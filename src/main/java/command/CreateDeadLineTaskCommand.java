package command;

import duke.Storage;
import duke.TaskList;

public class CreateDeadLineTaskCommand extends Command {
    public CreateDeadLineTaskCommand (String ...parameters) {
        super(parameters);
    }

    @Override
    public Result execute(TaskList taskList, Storage storage) {
        return null;
    }

}
