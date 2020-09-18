package command;

import duke.Parser;
import duke.Storage;
import duke.TaskList;
import ui.Ui;

public class FindTaskByKeywordCommand extends Command {
    public FindTaskByKeywordCommand (String ...parameter) {
        super(parameter);
    }

    @Override
    public Result execute(TaskList taskList, Parser parser, Storage aliasStorage, Storage taskStorage, Ui ui) {
        String listOfTasks = taskList.getTaskWithKeyword(parameters[0]);
        return new Result(ui.findTaskByKeyWordMessage(listOfTasks, parameters[0].strip()), executedSuccessfully);
    }
}
