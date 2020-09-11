package command;

import duke.Storage;
import duke.TaskList;

public class FindTaskByDateCommand extends Command {
    public FindTaskByDateCommand(String ...parameter) {
        super(parameter);
    }

    @Override
    public Result execute(TaskList taskList, Storage storage) {
        return null;
    }
}
