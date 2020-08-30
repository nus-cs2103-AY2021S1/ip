package duke.command;

import java.util.List;

import duke.exception.InvalidArgumentException;
import duke.exception.UserException;
import duke.misc.Ui;
import duke.task.Task;
import duke.task.TaskList;


public class TaskCommand extends Command {
    public TaskCommand(List<String> input) {
        super(input);
    }

    @Override
    public String run(TaskList taskList) throws UserException {
        Task task;
        if (input.size() < 4) {
            throw new InvalidArgumentException("Missing argument(s)");
        }
        task = taskList.addTask(input);
        return Ui.task(task.toString(), taskList.count());
    }
}
