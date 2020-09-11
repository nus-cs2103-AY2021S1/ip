package command;

import duke.Storage;
import duke.TaskList;
import task.Task;

public class CreateTodoTaskCommand extends Command {
    public CreateTodoTaskCommand (String ...parameter) {
        super(parameter);
    }

    @Override
    public Result execute(TaskList taskList, Storage storage) {
        Task newTask = taskList.addTodoTask(this.parameters);
        return new Result();
    }
}
