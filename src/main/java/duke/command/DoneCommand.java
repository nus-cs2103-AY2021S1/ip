package duke.command;

import java.util.List;

import duke.exception.InvalidArgumentException;
import duke.misc.Ui;
import duke.task.TaskList;


public class DoneCommand extends Command {
    public DoneCommand(List<String> input) {
        super(input);
    }

    @Override
    public String run(TaskList taskList) throws InvalidArgumentException {
        int index = Integer.parseInt(input.get(1));
        taskList.finishTask(index);
        return Ui.done(taskList.getTask(index).toString());
    }
}
