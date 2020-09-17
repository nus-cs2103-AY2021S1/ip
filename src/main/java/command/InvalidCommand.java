package command;

import duke.Parser;
import duke.TaskList;

public class InvalidCommand extends Command {
    @Override
    public Result execute(TaskList taskList, Parser parser) {
        return new Result("I am sorry, I do not understand that command.\n", executedSuccessfully);
    }
}
