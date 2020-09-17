package command;

import duke.Parser;
import duke.TaskList;

public class FindTaskByKeywordCommand extends Command {
    public FindTaskByKeywordCommand (String ...parameter) {
        super(parameter);
    }

    @Override
    public Result execute(TaskList taskList, Parser parser) {
        String listOfTasks = taskList.getTaskWithKeyword(parameters[0]);
        String message = "Master here are the tasks with keyword " + parameters[0].strip() + " :\n" + listOfTasks;
        return new Result(message, executedSuccessfully);
    }
}
