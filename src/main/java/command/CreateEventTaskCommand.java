package command;

import duke.Storage;
import duke.TaskList;

public class CreateEventTaskCommand extends Command {
    public CreateEventTaskCommand (String ...parameters) {
        super(parameters);
    }

    @Override
    public Result execute(TaskList taskList, Storage storage) {
        return null;
    }

}
