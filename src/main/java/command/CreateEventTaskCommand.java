package command;

import java.time.format.DateTimeParseException;

import duke.DukeExceptions;
import duke.Storage;
import duke.TaskList;
import task.Task;


public class CreateEventTaskCommand extends Command {
    public CreateEventTaskCommand (String ...parameters) {
        super(parameters);
    }

    @Override
    public Result execute(TaskList taskList, Storage storage) {
        String message;
        try {
            Task newTask = taskList.addEventTask(this.parameters);
            int noTask = taskList.getNoTask();
            message = "Master I have added the task : \n \t"
                    + newTask.toString() + "\nyou have " + noTask + " Tasks in the list.\n";
            return new Result(message, executedSuccessfully);
        } catch (DukeExceptions.IncompleteCommandException e) {
            return new Result(e.getMessage(), executedUnsuccessfully);
        } catch (ArrayIndexOutOfBoundsException e) {
            message = "Master the please input a date!";
            return new Result(message, executedUnsuccessfully);
        } catch (DateTimeParseException e) {
            message = "Master the input date should be dd-mm-yyyy hhmm!";
            return new Result(message, executedUnsuccessfully);
        }
    }

}
