package command;

import java.io.IOException;

import duke.Parser;
import duke.Storage;
import duke.TaskList;
import task.Task;
import ui.Ui;

/**
 * Command to delete a task.
 *
 * @author Ryan Lim
 */
public class DelTaskCommand extends Command {
    public DelTaskCommand(String ...parameter) {
        super(parameter);
    }

    @Override
    public Result execute(TaskList taskList, Parser parser, Storage aliasStorage, Storage taskStorage, Ui ui) {
        String message;
        try {
            int index = Integer.parseInt(parameters[0].strip()) - 1;
            Task deletedTask = taskList.deleteTask(index);
            taskStorage.save(taskList);
            return new Result(ui.deletedTaskMessage(deletedTask, taskList.getNoTask()), executedSuccessfully);
        } catch (IndexOutOfBoundsException e) {
            message = taskList.getNoTask() == 0
                    ? ui.taskListEmptyMessage()
                    : ui.invalidIndexMessage();
            return new Result(message, executedUnsuccessfully);
        } catch (NumberFormatException e) {
            return new Result(ui.noIndexGivenMessage(), executedUnsuccessfully);
        } catch (IOException e) {
            return new Result(ui.fileIssueMessage(), executedUnsuccessfully);
        }
    }

}
