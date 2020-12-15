package duke.command;

import java.util.ArrayList;
import java.util.List;

import duke.exception.InvalidArgumentException;
import duke.misc.Checker;
import duke.misc.Ui;
import duke.task.TaskList;


public class DoneCommand extends Command {
    public DoneCommand(List<String> input) {
        super(input);
    }

    @Override
    public String run(TaskList taskList) throws InvalidArgumentException {
        assert input.size() >= 2: "DoneCommand.run(): input must have at least 2 words";
        for (int index = 1; index < input.size(); index++) {
            if (index <= 0 || index > taskList.count()) throw new InvalidArgumentException("Out of range index(s)");
        }
        List<String> listOfTask= new ArrayList<>();
        for (int index = 1; index < input.size(); index++) {
            int taskIndex = Integer.parseInt(input.get(index));
            taskList.finishTask(taskIndex);
            listOfTask.add(taskList.getTask(taskIndex).toString());
        }

        return Ui.answerDone(listOfTask);
    }
}
