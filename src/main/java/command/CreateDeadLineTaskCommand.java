package command;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import duke.DukeExceptions;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import task.Task;
import ui.Ui;

/**
 * Command to create deadline task.
 *
 * @author Ryan Lim
 */
public class CreateDeadLineTaskCommand extends Command {

    public CreateDeadLineTaskCommand (String ...parameters) {
        super(parameters);
    }

    @Override
    public Result execute(TaskList taskList, Parser parser, Storage aliasStorage, Storage taskStorage, Ui ui) {
        try {
            Task newTask = taskList.addDeadlineTask(this.parameters);
            int noTask = taskList.getNoTask();
            taskStorage.save(taskList);
            return new Result(ui.addTaskMessage(newTask, noTask), executedSuccessfully);
        } catch (DukeExceptions.InsufficientParametersException e) {
            return new Result(ui.inSufficientParamsMessage("deadline"), executedUnsuccessfully);
        } catch (ArrayIndexOutOfBoundsException e) {
            return new Result(ui.noDateMessage(), executedUnsuccessfully);
        } catch (DateTimeParseException e) {
            return new Result(ui.invalidDateMessage(), executedUnsuccessfully);
        } catch (IOException e) {
            return new Result(ui.fileIssueMessage(), executedUnsuccessfully);
        }
    }

}
