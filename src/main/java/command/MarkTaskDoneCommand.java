package command;

import duke.Storage;
import duke.TaskList;
import task.Task;

public class MarkTaskDoneCommand extends Command {
    public MarkTaskDoneCommand(String ...parameter) {
        super(parameter);
    }

    @Override
    public Result execute(TaskList taskList, Storage storage) {
        String message;
        try {
            int index = Integer.parseInt(parameters[0].strip()) - 1;
            Task finishedTask = taskList.completeTask(index);
            message = "Making great progress master.\n" + finishedTask.toString() + "\n";
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
