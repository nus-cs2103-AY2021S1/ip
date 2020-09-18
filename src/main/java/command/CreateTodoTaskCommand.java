package command;

import java.io.IOException;

import duke.DukeExceptions;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import task.Task;
import ui.Ui;

public class CreateTodoTaskCommand extends Command {

    public CreateTodoTaskCommand (String ...parameters) {
        super(parameters);
    }

    @Override
    public Result execute(TaskList taskList, Parser parser, Storage aliasStorage, Storage taskStorage, Ui ui) {
        try {
            Task newTask = taskList.addTodoTask(this.parameters);
            int noTask = taskList.getNoTask();
            taskStorage.save(taskList);
            return new Result(ui.addTaskMessage(newTask, noTask), executedSuccessfully);
        } catch (DukeExceptions.InsufficientParametersException e) {
            return new Result(ui.inSufficientParamsMessage("Todo"), executedUnsuccessfully);
        } catch (IOException e) {
            return new Result(ui.fileIssueMessage(), executedUnsuccessfully);
        }
    }
}
