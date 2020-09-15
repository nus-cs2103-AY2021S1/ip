package command;

import java.time.format.DateTimeParseException;

import duke.Storage;
import duke.TaskList;


public class FindTaskByDateCommand extends Command {
    public FindTaskByDateCommand(String ...parameter) {
        super(parameter);
    }

    @Override
    public Result execute(TaskList taskList, Storage storage) {
        String message;
        try {
            String dueDate = parameters[0];
            String listOfTasks = taskList.getTaskDueOn(dueDate);
            message = "Master here are the tasks due on " + dueDate.strip() + " :\n" + listOfTasks;
            return new Result(message, executedSuccessfully);
        } catch (DateTimeParseException e) {
            message = "Master the input date should be dd-mm-yyyy!";
            return new Result(message, executedUnsuccessfully);
        }
    }
}
