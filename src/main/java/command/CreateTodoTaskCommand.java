package command;

import duke.Storage;
import duke.TaskList;

public class CreateTodoTaskCommand extends Command {
    public CreateTodoTaskCommand (String ...parameter) {
        super(parameter);
    }

    @Override
    public Result execute(TaskList taskList, Storage storage) {
        return null;
    }
}
