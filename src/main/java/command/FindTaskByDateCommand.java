package command;

import java.time.format.DateTimeParseException;

import duke.Parser;
import duke.Storage;
import duke.TaskList;
import ui.Ui;

/**
 * Command to find all task due on a specified date.
 *
 * @author Ryan Lim
 */
public class FindTaskByDateCommand extends Command {
    public FindTaskByDateCommand(String ...parameter) {
        super(parameter);
    }

    @Override
    public Result execute(TaskList taskList, Parser parser, Storage aliasStorage, Storage taskStorage, Ui ui) {
        String message;
        try {
            String dueDate = parameters[0];
            String listOfTasks = taskList.getTaskDueOn(dueDate);
            return new Result(ui.findTaskDueMessage(listOfTasks, dueDate), executedSuccessfully);
        } catch (DateTimeParseException e) {
            return new Result(ui.invalidDateMessage(), executedUnsuccessfully);
        }
    }
}
