package command;

import duke.Storage;
import duke.TaskList;

public class DelTaskCommand extends Command {
    public DelTaskCommand(String ...parameter) {
        super(parameter);
    }

    @Override
    public Result execute(TaskList taskList, Storage storage) {
        return null;
    }
}
