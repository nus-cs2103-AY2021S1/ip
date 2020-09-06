package duke.command;

import java.util.List;

import duke.exception.InvalidArgumentException;
import duke.misc.Ui;
import duke.task.Task;
import duke.task.TaskList;


public class DeleteCommand extends Command {
    public DeleteCommand(List<String> input) {
        super(input);
    }

    @Override
    public String run(TaskList taskList) throws InvalidArgumentException {
        assert input.size() == 2: "DeleteCommand.run(): input must have exactly 2 words";
        int index = Integer.parseInt(input.get(1));
        Task task = taskList.removeTask(index);
        return Ui.delete(task.toString(), taskList.count());
    }
}
