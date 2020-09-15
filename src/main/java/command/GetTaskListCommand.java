package command;

import duke.Storage;
import duke.TaskList;

public class GetTaskListCommand extends Command {

    @Override
    public Result execute(TaskList taskList, Storage storage) {
        return new Result(taskList.toString(), executedSuccessfully);
    }
}
