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
        assert input.size() == 2: "DoneCommand.run(): input must have exactly 2 words";
        int index = Integer.parseInt(input.get(1));
        taskList.finishTask(index);
        return Ui.answerDone(taskList.getTask(index).toString());
    }
}
