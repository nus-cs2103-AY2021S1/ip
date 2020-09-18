package command;

import duke.DukeExceptions;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import task.Task;
import ui.Ui;

/**
 * Command to mark task as done
 *
 * @author Ryan Lim
 */
public class MarkTaskDoneCommand extends Command {
    public MarkTaskDoneCommand(String ...parameter) {
        super(parameter);
    }

    @Override
    public Result execute(TaskList taskList, Parser parser, Storage aliasStorage, Storage taskStorage, Ui ui) {
        String message;
        try {
            int index = Integer.parseInt(parameters[0].strip()) - 1;
            Task finishedTask = taskList.completeTask(index);
            return new Result(ui.markDoneMessage(finishedTask), executedSuccessfully);
        } catch (IndexOutOfBoundsException e) {
            message = taskList.getNoTask() == 0 ? ui.taskListEmptyMessage() : ui.invalidIndexMessage();
            return new Result(message, executedUnsuccessfully);
        } catch (NumberFormatException e) {
            return new Result(ui.noIndexGivenMessage(), executedUnsuccessfully);
        } catch (DukeExceptions.TaskIsDoneException e) {
            return new Result(ui.taskAlreadyDoneMessage(), executedUnsuccessfully);
        }
    }
}
