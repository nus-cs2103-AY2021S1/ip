package command;

import duke.Storage;
import duke.TaskList;

public class MarkTaskDoneCommand extends Command {
    public MarkTaskDoneCommand(String ...parameter) {
        super(parameter);
    }

    @Override
    public Result execute(TaskList taskList, Storage storage) {
        return null;
    }

}
