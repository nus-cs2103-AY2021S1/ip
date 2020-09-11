package command;

import duke.Storage;
import duke.TaskList;

public class FindTaskByKeywordCommand extends Command {
    public FindTaskByKeywordCommand (String ...parameter) {
        super(parameter);
    }

    @Override
    public Result execute(TaskList taskList, Storage storage) {
        return null;
    }
}
