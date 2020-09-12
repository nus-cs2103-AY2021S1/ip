package command;

import duke.DukeExceptions;
import duke.Storage;
import duke.TaskList;
import task.Task;

import java.time.format.DateTimeParseException;

public class CreateTodoTaskCommand extends Command {

    public CreateTodoTaskCommand (String ...parameters) {
        super(parameters);
    }

    @Override
    public Result execute(TaskList taskList, Storage storage) {
        try {
            Task newTask = taskList.addTodoTask(this.parameters);
            int noTask = taskList.getNoTask();
            String message = "Master I have added the task : \n \t"
                    + newTask.toString() + "\nyou have " + noTask + " Tasks in the list.\n";
            return new Result(message, EXECUTED_SUCCESSFULLY);
        } catch (DukeExceptions.IncompleteCommandException e) {
            return new Result(e.getMessage(), EXECUTED_UNSUCCESSFULLY);
        }
    }
}
