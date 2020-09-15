package command;

import duke.Storage;
import duke.TaskList;
import task.Task;

public class DelTaskCommand extends Command {
    public DelTaskCommand(String ...parameter) {
        super(parameter);
    }

    @Override
    public Result execute(TaskList taskList, Storage storage) {
        String message;
        try {
            int index = Integer.parseInt(parameters[0].strip()) - 1;
            Task deletedTask = taskList.deleteTask(index);
            message = "Yes master. I've deleted the task from the list: \n\t" + deletedTask.toString()
                    + "\nYou now have " + taskList.getNoTask() + " task in the list master.\n";
            return new Result(message, executedSuccessfully);
        } catch (IndexOutOfBoundsException e) {
            message = taskList.getNoTask() == 0
                    ? "Master task list is empty!\n"
                    : "Master please enter a valid index!\n";
            return new Result(message, executedUnsuccessfully);
        } catch (NumberFormatException e) {
            message = "Master please enter a task number so that I know which to handle.\n";
            return new Result(message, executedUnsuccessfully);
        }
    }
}
